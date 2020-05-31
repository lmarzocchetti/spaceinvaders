package Invaders;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JPanel implements KeyListener, ActionListener {
	private static final long serialVersionUID = 1L;
	GameOverPanel gop;
	WinPanel wp;
	Player pl;
	ArrayList<Enemy> en = new ArrayList<Enemy>();
	ArrayList<Projectile> p = new ArrayList<Projectile>();
	ArrayList<Projectile> AlP = new ArrayList<Projectile>();
	Timer fps, prTimer, enTimer, spaceTimer, alienShoot;
	String phase = "Right";
	String newPhase = "Left";
	int numofEnemies, score = 0;
	boolean canShoot = true, AlienHasShoot = false, hasShoot = false;
	
	//Simple Constructor for setting the Panel and call the initialization method
	public GamePanel() {
		super();
		setFocusable(true);
		setSize(800, 600);
		setBackground(Color.black);
		addKeyListener(this);
		this.init();
	}
	
	/*
	 * Things that init method do(in order):
	 * - Instance a new Player with a determinate png
	 * - Instance a new List of Enemies in determinate coordinates for form a rectangle of enemies
	 * - Start 4 different timers for:
	 * 		- FPS
	 * 		- Moving Projectile (player and enemies)
	 * 		- Enemies
	 * 		- Enemies shoot(random implementation)
	 */
	public void init() {
		pl = new Player("bho1.png", 375, 480);
		
		
		int enemyY = 30;
		for(int i = 0; i < 5; i++) {
			int enemyX = 135;
			for(int j = 0; j < 11; j++) {
				en.add(new Enemy("enemy2.png","enemyup5.png", enemyX, enemyY));
				enemyX += 50;
			}
			enemyY += 50;
		}
	
		fps = new Timer(4, this);
		fps.start();
		
		prTimer = new Timer(4, this);
		prTimer.start();
		
		enTimer =  new Timer(1000, this);
		enTimer.start();
		
		alienShoot = new Timer(2000, this);
		alienShoot.start();
	}

	/*
	 * This method stop all timers, set NotVisible the WinPanel, and visible the gameoverPanel,
	 * and call the Start method from gameoverPanel Class
	 */
	public void GameOver() {
		fps.stop();
		prTimer.stop();
		enTimer.stop();
		alienShoot.stop();
		wp.setVisible(false);
		gop.setVisible(true);
		gop.setFocusable(true);
		gop.Start();
	}
	
	/*
	 * This method stop all timers, set NotVisible the gameoverPanel, and visible the winPanel,
	 * and call the Start method from winPanel Class
	 */
	public void youWin() {
		fps.stop();
		prTimer.stop();
		enTimer.stop();
		alienShoot.stop();
		gop.setVisible(false);
		wp.setVisible(true);
		wp.setFocusable(true);
		wp.Start();
	}
	
	/*
	 * This method is needed for a newGame.
	 * Set NotVisible the winpanel and the gameover panel;
	 * Clear all Lists from existing Istance of Projectile and Enemies;
	 * Reset the boolean variables, the Strings for the phase of enemies, and set Score to 0
	 * Call the init method
	 */
	public void restart() {
		wp.setVisible(false);
		wp.setFocusable(false);
		gop.setVisible(false);
		gop.setFocusable(false);
		en.clear();
		AlP.clear();
		p.clear();
		this.canShoot = true;
		this.AlienHasShoot = false;
		this.hasShoot = false;
		phase = "Right";
		newPhase = "Left";
		score = 0;
		this.init();
	}
	
	/*
	 * This method is for Paint all component of this gamePanel(in order):
	 * 	- Paint the divisor from the game and the score/health section of the game;
	 * 	- Paint the player
	 * 	- Paint the health of the player
	 * 	- Paint the enemies
	 * 	- Paint the our Projectiles(if we had shoot)
	 * 	- Paint the Enemy's Projectiles(if they had shoot)
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.white);
		g.fillRect(1, 545, 800, 1);
		
		g.setFont(new Font("Algerian", Font.BOLD, 15));
		g.drawString("Score = " + score, 10, 565);
		
		pl.Paint(g);
		
		for(Health h: pl.Health) {
			h.Paint(g);
		}
		
		for(Enemy e : en) {
			e.Paint(g);
		}
		
		if(hasShoot) {
			for(Projectile pj : p) {
				pj.Paint(g);
			}
		}
		
		if(AlienHasShoot) {
			for(Projectile pj : AlP) {
				pj.Paint(g);
			}
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		return;
	}

	/*
	 * Method for set the speed of the player to 2 or -2(MoveRight and MoveLeft) to Right and Left Arrow key
	 * And let player shoot with the SpaceBar every 700millisec
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			pl.setSpeed(2);
		}
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			pl.setSpeed(-2);
		}
		
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			if(canShoot) {
				p.add(pl.Shoot());
				hasShoot = true;
				canShoot = false;
				
				spaceTimer = new Timer(700, this);
				spaceTimer.start();
		}
		
	}
}

	/*
	 * Reset the Speed of the player after we released the Right or Left Arrow key
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			pl.setSpeed(0);
		}
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			pl.setSpeed(0);
		}
	}
	
	/*
	 * Method for Handle all Timer we had(in order):
	 * 	- Timer for FPS: Control for 2 different gameover, first if we lose all health points,
	 * 		second when the aliens hit a certain y coordinate. Move the player synchronized with this timer and repaint all;
	 * 	- Timer for Projectile:	Control if one of ours projectiles hit one enemy, if this is true, remove the projectile,
	 * 		remove the enemy, increment the score by 10 and return. Also control if one of ours projectile hit the border;
	 * 		Move all ours projectiles;
	 * 		Control if one enemy's projectile hit us, if this is true, remove the projectile and lower by 1 the health of
	 * 		the player. And Control and remove the enemy projectile if hit the border;
	 * 		Move all enemy's projectiles;
	 * 	- Timer for enemies: Change the sprite to print, and do a phase based moving for enemies;
	 * 	- Timer for ourShoot: Simply set to true the boolean variable canShoot. This is for not let the player spam;
	 * 	- Timer for enemyShoot: Instance a new Random class and for 4 random alien, call the method alienShoot
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == fps) {
			if(pl.Health.size() == 0) {
				GameOver();
			}
			for(Enemy es : en) {
				if(es.getY() >= 450) {
					GameOver();
				}
			}
			
			if(en.isEmpty()) {
				youWin();
			}
			
			pl.move();
			repaint();
		}
		
		if(e.getSource() == prTimer) {
			for(Projectile pr : p) {
				for(Enemy es : en) {
					if(pr.getY() == es.getY() + 15 && ((pr.getX() + 10 > es.getX()) && (pr.getX() - 10 < es.getX() + 15))) {
						p.remove(pr);
						en.remove(es);
						score += 10;
						return;
					}
					if(pr.getY() <= 1) {
						p.remove(pr);
						return;
					}
				}
			}
			
			
			for(Projectile pr : p) {
				pr.moveUp();
			}
			
			for(Projectile pr : AlP) {
				if(((pr.getY() + 18 <= pl.getY() + 50) && (pr.getY() + 18 > pl.getY())) && ((pr.getX() + 10 >= pl.getX() + 3) && (pr.getX() - 10 <= pl.getX() + 30))) {
					if(pl.Health.size() <= 3 || pl.Health.size() >= 1) {
						AlP.remove(pr);
						if(pl.Health.size() != 0) {
							pl.HealthDown();
						}
						return;
					} 
				}
				if(pr.getY() >= 600) {
					AlP.remove(pr);
					return;
				}
			}
			
			for(Projectile pr : AlP) {
				pr.moveDown();
			}
		}
		
		if(e.getSource() == enTimer) {
			
			for(Enemy el : en) {
				el.invertAnimation();
			}
			
			//numofEnemies = en.size();
			
			if(phase.equals("Right")) {
				for(Enemy e1 : en) {
					if(e1.getX() == 725) {
						phase = "Down";
						return;
					}
				}
				
				for(Enemy e1 : en) {
					e1.moveRight();
				}
			}
			
			if(phase.equals("Left")) {
				for(Enemy e1 : en) {
					if(e1.getX() == 75) {
						phase = "Down";
						return;
					}
				}
				
				for(Enemy e1 : en) {
					e1.moveLeft();
				}
			}
			
			if(phase.equals("Down")) {
				if(newPhase.equals("Left")) {
					phase = "Left";
					newPhase = "Right";
				}
				else {
					phase = "Right";
					newPhase = "Left";
				}
				
				for(Enemy e1: en) {
					e1.moveDown();
				}
			}
			
		}
		
		if(e.getSource() == spaceTimer) {
			canShoot = true;
			spaceTimer.stop();
		}
		
		if(e.getSource() == alienShoot) {
			Random r = new Random();
			int b = en.size();
			
			
			for(int i = 0; i < 4; i++) {
				int rn = r.nextInt(b);
				
				AlP.add(en.get(rn).AlienShoot());
			}
			AlienHasShoot = true;
		}
	}
	//Set the gameover panel
	public void setGameOverPanel(GameOverPanel gop) {
		this.gop = gop;
	}
	
	//set the win panel
	public void setWinPanel(WinPanel wp) {
		this.wp = wp;
	}
}
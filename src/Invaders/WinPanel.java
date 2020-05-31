package Invaders;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class WinPanel extends JPanel implements MouseListener, MouseMotionListener, ActionListener {
	private static final long serialVersionUID = 1L;
	GamePanel gp;
	Color c;
	Timer fps;
	boolean onExit = false, onNewGame = false;
	
	/*
	 * Initialize the WinPanel, set the background color, add MouseListener and MouseMotionListener
	 * for Handle the mouse movement and click.
	 * Also Instance a new Timer for refresh this Panel
	 */
	public WinPanel() {
		super();
		setSize(800, 600);
		c = new Color(201, 201, 201, 2);
		this.setBackground(c);
		
		addMouseListener(this);
		addMouseMotionListener(this);
		
		fps = new Timer(1, this);
	}
	
	/*
	 * Paint all components in the WinPanel:
	 * 	- You Win string
	 * 	- Score obtained in the last game session
	 * 	- Draw the exit string, in 2 different color. When the mouse is on Exit print it in Blue
	 * 	- Same for NewGame string but in Magenta
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setFont(new Font("Algerian", Font.BOLD, 30));
		g.setColor(Color.BLACK);
		
		g.drawString("YOU WIN!", 270, 150);
		
		g.setFont(new Font("Algerian", Font.BOLD, 15));
		g.drawString("Score = " + gp.score, 270, 180);
		g.setFont(new Font("Algerian", Font.BOLD, 30));

		if(onExit) {
			g.setColor(Color.BLUE);
		}
		
		g.drawString("Exit", 100, 500);
		
		g.setColor(Color.BLACK);
		
		if(onNewGame) {
			g.setColor(Color.MAGENTA);
		}
		
		g.drawString("New Game", 530, 500);
		
		
	}
	
	/*
	 * Start the WinPanel
	 * this Method is called by the GamePanel
	 */
	public void Start() {
		this.setVisible(true);
		fps.start();
	}
	
	public void setGamePanel(GamePanel gp) {
		this.gp = gp;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		return;
	}

	/*
	 * When the mouse is on the exit or newgame string, set the 2 variables on True for
	 * paint it in the right color
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		if(e.getX() > 100 && e.getX() < 165 && e.getY() > 470 && e.getY() < 500) {
			onExit = true;
		}
		else {
			onExit = false;
		}
		
		if(e.getX() > 530 && e.getX() < 710 && e.getY() > 470 && e.getY() < 500) {
			onNewGame = true;
		}
		else {
			onNewGame = false;
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		return;
	}

	/*
	 * When the mouse click on the exit or new game string, close the game or start a new game
	 * calling the restart method of gamePanel
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {
			if(e.getX() > 100 && e.getX() < 165 && e.getY() > 470 && e.getY() < 500) {
				System.exit(0);
			}
		}
		
		if(e.getButton() == MouseEvent.BUTTON1) {
			if(e.getX() > 530 && e.getX() < 710 && e.getY() > 470 && e.getY() < 500) {
				fps.stop();
				gp.restart();
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		return;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		return;
	}

	@Override
	public void mouseExited(MouseEvent e) {
		return;
	}

	/*
	 * repaint this panel based on fps timer
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == fps) {
			repaint();
		}
	}
	
}

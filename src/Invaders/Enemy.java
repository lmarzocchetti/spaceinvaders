package Invaders;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Enemy extends AbstractGameEntity {
	int Speed = 10;
	int SpeedDown = 30;
	boolean isDead = false, isUp = false;
	Image imageUp;

	/*
	 * Call the super Constructor method with filename and open the second image(filenameUp)
	 * for animation of enemies
	 */
	public Enemy(String filename,String filenameUp, int x, int y) {
		super(filename, x, y);
		
		try {
			this.imageUp = new ImageIcon(filenameUp).getImage();
		}
		catch (IllegalArgumentException e) {
			this.imageUp = null;
		}
		catch(Exception e) {
			this.imageUp = null;
		}
	}
	
	/*
	 * If the enemy is not dead, paint it based of the isUp variable, for paint one time
	 * the first image and one time the second image.
	 * The else condictions are here only for handle errors in opening
	 */
	@Override
	public void Paint(Graphics g) {
		if(!isDead) {
			if(!isUp) {
				if(image != null) {
					g.drawImage(image, x, y, null);
				}
				else {
					g.setColor(Color.GREEN);
					g.fillRect(x, y, 50, 50);
				}
			}
			else {
				if(imageUp != null) {
					g.drawImage(imageUp, x, y, null);
				}
				else {
					g.setColor(Color.GREEN);
					g.fillRect(x, y, 50, 50);
				}
			}
		}
	}
	
	/*
	 * Instance a new Projectile centered with the alien
	 */
	public Projectile AlienShoot() {
		return new Projectile("bho5.png", this.x + 8, this.y + 15);
	}
	
	/*
	 * Invert the isUp variable for paint different aliens png
	 */
	public void invertAnimation() {
		isUp = !isUp;
	}

	/*
	 * Move left the alien
	 */
	@Override
	public void moveLeft() {
		if(getX() == 25) {
			return;
		}
		this.x -= this.Speed;
	}

	/*
	 * Move right the alien
	 */
	@Override
	public void moveRight() {
		if(getX() == 725) {
			return;
		}
		this.x += this.Speed;
	}
	
	/*
	 * Move down the alien
	 */
	public void moveDown() {
		this.y += this.SpeedDown;
	}
	
	/*
	 * Set The alien dead by this variable
	 */
	public void Death() {
		isDead = true;
		
	}

	public int getSpeed() {
		return Speed;
	}

	public void setSpeed(int speed) {
		Speed = speed;
	}	
	
}

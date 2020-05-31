package Invaders;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public abstract class AbstractGameEntity implements GameEntity {
	int x, y;
	Image image;
	
	/*
	 * Initialize the generic gameEntity with a x and y coordinates
	 * and with an image
	 */
	public AbstractGameEntity(String filename, int x, int y) {
		this.x = x;
		this.y = y;
		try {
			this.image = new ImageIcon(filename).getImage();
		}
		catch (IllegalArgumentException e) {
			this.image = null;
		}
		catch(Exception e) {
			this.image = null;
		}
		
	}
	
	/*
	 * Paint the generic gameEntity
	 */
	public void Paint(Graphics g) {
		if(image != null) {
			g.drawImage(image, x, y, null);
		}
		else {
			g.setColor(Color.GREEN);
			g.fillRect(x, y, 50, 50);
		}
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	
}

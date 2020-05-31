package Invaders;

import java.awt.Graphics;

public interface GameEntity {
	public void Paint(Graphics g);
	public void moveLeft();
	public void moveRight();
}

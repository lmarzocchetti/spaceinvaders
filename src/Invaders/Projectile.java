package Invaders;

public class Projectile extends AbstractGameEntity {
	int Speed = 1;
	
	/*
	 * see the AbstractGameEntity Constructor
	 */
	public Projectile(String filename, int x, int y) {
		super(filename, x, y);
	}
	
	public void moveUp() {
		y -= Speed;
	}
	
	public void moveDown() {
		y += Speed;
	}

	@Override
	public void moveLeft() {
		return;
	}

	@Override
	public void moveRight() {
		return;
	}

}

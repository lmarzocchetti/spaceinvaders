package Invaders;

import java.util.ArrayList;

public class Player extends AbstractGameEntity {
	int Speed = 0;
	ArrayList<Health> Health = new ArrayList<Health>();
	
	/*
	 * Call the AbstractGameEntity constructor method and initialize the Health container for the player
	 */
	public Player(String filename, int x, int y) {
		super(filename, x, y);
		
		int Healthx = 770;
		for(int i = 0; i < 3; i++) {
			this.Health.add(new Health("bho4.png",Healthx, 550));
			Healthx -= 30;
		}
	}

	/*
	 * Generic move Method for the player, and simple control for the border of the screen
	 */
	public void move() {
		if(getX() <= 25 && this.Speed < 0) {
			return;
		}
		if(getX() >= 725 && this.Speed > 0) {
			return;
		}
		this.x += this.Speed;
	}
	
	/*
	 * not in use anymore :c
	 */
	@Override
	public void moveLeft() {
		if(getX() <= 25) {
			return;
		}
		this.x -= this.Speed;
	}

	/*
	 * not in use anymore too :c
	 */
	@Override
	public void moveRight() {
		if(getX() >= 725) {
			return;
		}
		this.x += this.Speed;
	}
	
	/*
	 * Return a new istance of projectile based on our x and y coordinates
	 */
	public Projectile Shoot() {
		return new Projectile("bho3.png", this.getX() + 18, this.getY() - 18);
	}
	
	/*
	 * h e a l t h	d o w n
	 */
	public void HealthDown() {
		Health.remove(0);
	}

	public int getSpeed() {
		return Speed;
	}

	public void setSpeed(int speed) {
		Speed = speed;
	}
	
	
}

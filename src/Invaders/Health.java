package Invaders;

public class Health extends AbstractGameEntity {

	/*
	 * see the description of AbstractGameEntity
	 */
	public Health(String filename, int x, int y) {
		super(filename, x, y);
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

package engine;

public class LeverGuy extends GameObject {

	public LeverGuy () {
		this.setSprite(new Sprite ("resources/sprites/lever depressed.png"));
	}
	
	public void pressLever () {
		this.setSprite(new Sprite ("resources/sprites/lever pressed.png"));
	}
	public void depressLever () {
		this.setSprite(new Sprite ("resources/sprites/lever depressed.png"));
	}
}

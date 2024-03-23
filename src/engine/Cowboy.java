package engine;

public class Cowboy extends GameObject {
	public Cowboy (boolean color) {
		if (color) {
			this.setSprite(new Sprite ("resources/sprites/cowboy blue.png"));
		} else {
			this.setSprite(new Sprite ("resources/sprites/cowred.png"));
		}
	}
}

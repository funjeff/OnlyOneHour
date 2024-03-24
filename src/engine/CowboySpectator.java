package engine;

public class CowboySpectator extends GameObject {
	public CowboySpectator (boolean color) {
		if (color) {
			this.setSprite(new Sprite ("resources/sprites/cowboy blue.png"));
		} else {
			this.setSprite(new Sprite ("resources/sprites/cowred.png"));
		}
	}
}

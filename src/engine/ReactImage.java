package engine;

public class ReactImage extends GameObject {
	
	public ReactImage(String name) {
		this.setSprite(new Sprite("resources/sprites/" + name + ".png"));
	}

}

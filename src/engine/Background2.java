package engine;

public class Background2 extends GameObject{
	public Background2 (Sprite s) {
		this.setSprite(s);
		this.setRenderPriority(-10);
	}
}

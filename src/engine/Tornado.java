package engine;

public class Tornado extends GameObject {

	int frameTimer = 2;
	
	Sprite tord1 = new Sprite ("resources/sprites/tornado 1.png");
	Sprite tord2 = new Sprite ("resources/sprites/tornado 2.png");
	
	boolean direction = false;
	
	public Tornado () {
		this.setSprite(tord1);
		this.useSpriteHitbox();
	}
	
	@Override
	public void frameEvent () {
		if (frameTimer == 0) {
			if (this.getSprite().equals(tord1)) {
				this.setSprite(tord2);
			} else {
				this.setSprite(tord1);
			}
			frameTimer = 2;
		} else {
			frameTimer = frameTimer - 1;
		}
		if (direction) {
			this.setX(this.getX() - 3);
		} else{
			this.setX(this.getX() + 3);
		}
	}
	
	
}

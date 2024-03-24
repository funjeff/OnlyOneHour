package engine;

public class bit extends GameObject {
	
	public int val;
	public boolean selectedWrong = false;
	public bit(int value) {
		this.val = value;
		if (val == 0) {
			this.setSprite(new Sprite("resources/sprites/0 bit.png"));
		}
		else {
			this.setSprite(new Sprite("resources/sprites/1 bit.png"));
		}
		this.useSpriteHitbox();
	}
	
	@Override
	public void frameEvent() {
		if (getCursorX()/1284 >= this.getX() &&
				getCursorX()/1284 <= this.getX() + this.getSprite().getWidth() &&
				getCursorY()/721.333333333 >= this.getY() &&
				getCursorY()/721.333333333 <= this.getY() + this.getSprite().getHeight() &&
				this.mouseButtonReleased(0)) {
			if (val == 0) {
				this.setSprite(new Sprite("resources/sprites/1 bit.png"));
				val = 1;
			}
			else {
				selectedWrong = true;
				this.setSprite(new Sprite("resources/sprites/0 bit.png"));
				val = 0;
			}
		}
	}
	
}

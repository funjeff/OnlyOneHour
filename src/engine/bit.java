package engine;

public class bit extends GameObject {
	
	public int val;
	public boolean selectedWrong = false;
	public bit(int value) {
		this.val = value;
		if (val == 0) {
			this.setSprite(new Sprite("resources/sprites/zero.png"));
		}
		else {
			this.setSprite(new Sprite("resources/sprites/one.png"));
		}
		this.useSpriteHitbox();
		this.adjustHitboxBorders();
	}
	
	@Override
	public void frameEvent() {
		System.out.print(getCursorY());
		if (getCursorX()/1284 >= this.getX() &&
				getCursorX()/1284 <= this.getX() + this.getSprite().getWidth() &&
				getCursorY()/721.333333333 >= this.getY() &&
				getCursorY()/721.333333333 <= this.getY() + this.getSprite().getHeight() &&
				this.mouseButtonClicked(0)) {
			System.out.print("OW THAT HURTS");
			if (val == 0) {
				this.setSprite(new Sprite("resources/sprites/one.png"));
				val = 1;
			}
			else {
				selectedWrong = true;
				this.setSprite(new Sprite("resources/sprites/zero.png"));
				val = 0;
			}
		}
		else if (this.mouseButtonClicked(0)){
//			System.out.print("ARE YOU WORKING");
		}
	}
	
}

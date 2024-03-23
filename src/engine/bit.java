package engine;

public class bit extends GameObject {
	
	public int val;
	
	public bit(int value) {
		this.val = value;
		if (val == 0) {
			this.setSprite(new Sprite("D:/workspace/OnlyOneHour/resources/sprites/one.png"));
		}
		else {
			this.setSprite(new Sprite("D:/workspace/OnlyOneHour/resources/sprites/zero.png"));
		}
		this.useSpriteHitbox();
	}
	
	@Override
	public void frameEvent() {
		
		if (this.hitbox().contains(getCursorX(), getCursorY()) && this.mouseButtonDown(0)) {
			if (val == 0) {
				this.setSprite(new Sprite("D:/workspace/OnlyOneHour/resources/sprites/one.png"));
				val = 1;
			}
			else {
				this.setSprite(new Sprite("D:/workspace/OnlyOneHour/resources/sprites/zero.png"));
				val = 0;
			}
		}
		else if (this.mouseButtonClicked(0)){
			System.out.print("ARE YOU WORKING");
		}
	}
	
}

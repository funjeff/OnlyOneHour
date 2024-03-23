package engine;

import java.util.Random;

public class monster extends GameObject{
	
	public int val;
	
	public monster(int direction) {
		this.setSprite(new Sprite("resources/sprites/monster.png"));
		if (direction == 0) { //left
			
		}
		else if (direction == 1) { //right 
			
		}
		else if (direction == 2) { //up
			
		}
		else { //down
			
		}
		this.val = value;
		if (val == 0) {
			this.setSprite(new Sprite("resources/sprites/one.png"));
		}
		else {
			this.setSprite(new Sprite("resources/sprites/zero.png"));
		}
		this.useSpriteHitbox();
	}
	
	@Override
	public void frameEvent() {
		
		if (this.hitbox().contains(getCursorX(), getCursorY()) && this.mouseButtonDown(0)) {
			if (val == 0) {
				this.setSprite(new Sprite("resources/sprites/one.png"));
				val = 1;
			}
			else {
				this.setSprite(new Sprite("resources/sprites/zero.png"));
				val = 0;
			}
		}
		else if (this.mouseButtonClicked(0)){
			System.out.print("ARE YOU WORKING");
		}
	}
	
}


}

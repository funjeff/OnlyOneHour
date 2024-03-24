package engine;

import java.awt.event.KeyEvent;

public class dodger extends GameObject{
	
	public boolean hasMoved = false;
	
	public dodger() {
		this.setSprite(new Sprite("resources/sprites/dodger.png"));
		this.declare(480-120, 270-160);
		this.useSpriteHitbox();
	}
	
	@Override
	public void frameEvent() {
		if (!hasMoved) {
			if (this.keyPressed(KeyEvent.VK_UP)) { // if up arrow is pressed
				this.setY(0);
				hasMoved = true;
			}
			else if (this.keyPressed(KeyEvent.VK_DOWN)) { // if down arrow is pressed
				this.setY(540-240);
				hasMoved = true;
			}
			else if (this.keyPressed(KeyEvent.VK_LEFT)) { // if left arrow is pressed
				this.setX(0);
				hasMoved = true;
			}
			else if (this.keyPressed(KeyEvent.VK_RIGHT)) { // if right arrow is pressed
				this.setX(960-320);
				hasMoved = true;
			}
		}
	}
}

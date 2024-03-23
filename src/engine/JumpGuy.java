package engine;

import java.awt.event.KeyEvent;

public class JumpGuy extends GameObject {
	
	public JumpGuy() {
		this.setSprite(new Sprite("resources/sprites/reporter.png"));
	}
	double vy = -15;
	boolean isLoner = false;
	Pipe spawner;
	double speed = .5;
	
	@Override
	public void frameEvent () {
		this.setY(this.getY() + vy);
		vy = vy + speed;
		if (this.getY() > spawner.getY()) {
			if (this.isLoner) {
				spawner.lost = true;
			}
			spawner.clear = true;
			this.forget();
		}
		if (getCursorX()/1284 >= this.getX() &&
				getCursorX()/1284 <= this.getX() + this.getSprite().getWidth()*2 &&
				getCursorY()/721.333333333 >= this.getY() &&
				getCursorY()/721.333333333 <= this.getY() + this.getSprite().getHeight()*2 &&
				this.mouseButtonReleased(0)) {
			if (isLoner) {
				spawner.won = true;
			} else {
				spawner.lost = true;
			}
		}
	}

	public void makeLoner () {
		isLoner = true;
	}
	
	public void setSpeed (double newSpeed) {
		speed = newSpeed;
	}
	
}

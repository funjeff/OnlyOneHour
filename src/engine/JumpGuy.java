package engine;

import java.awt.event.KeyEvent;
import java.util.Random;

public class JumpGuy extends GameObject {
	
	public JumpGuy() {
	}
	double vy = -22;
	boolean isLoner = false;
	Pipe spawner;
	double speed = .5;
	
	@Override
	public void onDeclare() {
		Random rand = new Random ();
		switch (rand.nextInt(7)) {
		case 0:
			this.setSprite(new Sprite("resources/sprites/sus.png"));
			break;
		case 1:
			this.setSprite(new Sprite("resources/sprites/person2.png"));
			break;
		case 2:
			this.setSprite(new Sprite("resources/sprites/person3.png"));
			break;
		case 3:
			this.setSprite(new Sprite("resources/sprites/person4.png"));
			break;
		case 4:
			this.setSprite(new Sprite("resources/sprites/stick guy bad.png"));
			break;
		case 5:
			this.setSprite(new Sprite("resources/sprites/pipe guy.png"));
			break;
		case 6:
			this.setSprite(new Sprite("resources/sprites/Chris pratt stomping.png"));
			break;
		}
	}
	
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
				getCursorX()/1284 <= this.getX() + this.getSprite().getWidth() &&
				getCursorY()/721.333333333 >= this.getY() &&
				getCursorY()/721.333333333 <= this.getY() + this.getSprite().getHeight() &&
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

package engine;

import java.awt.event.KeyEvent;

public class dodger extends GameObject{
	
	public int dodgeingDir = -1;
	public double speed = 1.0;
	
	DodgeArrow up;
	DodgeArrow down;
	DodgeArrow left;
	DodgeArrow right;
	
	public dodger() {
		this.setSprite(new Sprite("resources/sprites/dodger1.png"));
		this.useSpriteHitbox();
		
		up = new DodgeArrow(new Sprite("resources/sprites/up arrow.png"));
		down = new DodgeArrow(new Sprite("resources/sprites/down arrow.png"));
		left = new DodgeArrow(new Sprite("resources/sprites/left arrow.png"));
		right = new DodgeArrow(new Sprite("resources/sprites/right arrow.png"));
		
		this.declare(480 - 60, 270 - 30);
	}
	
	@Override
	public void onDeclare () {
		up.setX(this.getX() + 50);
		up.setY(this.getY() - 100);
		
		down.setX(this.getX() + 50);
		down.setY(this.getY() + 100);
		
		left.setX(this.getX() - 120);
		left.setY(this.getY() + 40);
		
		right.setX(this.getX() + 130);
		right.setY(this.getY() + 40);
		
	}
	
	@Override
	public void frameEvent() {
		if (dodgeingDir == -1) {
			up.frameEvent();
			down.frameEvent();
			left.frameEvent();
			right.frameEvent();
			if (up.clickedOn) { // if up arrow is pressed
				dodgeingDir = 0;
			}
			else if (down.clickedOn) { // if down arrow is pressed
				dodgeingDir = 1;
			}
			else if (left.clickedOn) { // if left arrow is pressed
				dodgeingDir = 2;
			}
			else if (right.clickedOn) { // if right arrow is pressed
				dodgeingDir = 3;
			}
		}
		
		if (dodgeingDir == 0) {
			this.setY(this.getY() - (16*speed));
			if (this.getY() < 30) {
				dodgeingDir = 5;
			}
		}
		
		if (dodgeingDir == 5) {
			this.setY(this.getY() + (16*speed));
			if (this.getY() > 240) {
				dodgeingDir = 10;
			}
		}
		
		if (dodgeingDir == 1) {
			this.setY(this.getY() + (16*speed));
			if (this.getY() > 450) {
				dodgeingDir = 6;
			}
		}
		
		if (dodgeingDir == 6) {
			this.setY(this.getY() - (16*speed));
			if (this.getY() < 240) {
				dodgeingDir = 10;
			}
		}
		
		if (dodgeingDir == 1) {
			this.setY(this.getY() + (16*speed));
			if (this.getY() > 450) {
				dodgeingDir = 6;
			}
		}
		
		if (dodgeingDir == 6) {
			this.setY(this.getY() - (16*speed));
			if (this.getY() < 240) {
				dodgeingDir = 10;
			}
		}
		
	}
	
	@Override
	public void draw () {
		super.draw();
		if (dodgeingDir == -1) {
			up.draw();
			down.draw();
			left.draw();
			right.draw();
		}
	}
	
	public class DodgeArrow extends GameObject {
		
		public boolean clickedOn = false;
		public DodgeArrow (Sprite spr) {
			this.setSprite(spr);
		}
		
		@Override
		public void frameEvent () {
			if (getCursorX()/1284 >= this.getX() &&
					getCursorX()/1284 <= this.getX() + this.getSprite().getWidth() &&
					getCursorY()/721.333333333 >= this.getY() &&
					getCursorY()/721.333333333 <= this.getY() + this.getSprite().getHeight() &&
					this.mouseButtonReleased(0)) {
					clickedOn = true;
				}
			}
		}
	}

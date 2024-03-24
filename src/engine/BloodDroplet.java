package engine;

import java.util.Random;

public class BloodDroplet extends GameObject {
	double xSpeed = 0;
	double ySpeed = 0;
	double spinSpeed = 0;
	
	public BloodDroplet() {
		this.setSprite(new Sprite("resources/sprites/Blood_drop.png"));
		Random rand = new Random ();
		xSpeed = (6*rand.nextDouble()) - 3;
		ySpeed = (6*rand.nextDouble()) - 3;
		spinSpeed = (rand.nextDouble()*.8) - .4;
	}
	@Override
	public void frameEvent () {
		this.setX(this.getX() + xSpeed);
		this.setY(this.getY() + ySpeed);
		this.setDrawRotation(this.drawRotation + spinSpeed);
		if (xSpeed != 0) {
			if (xSpeed > 0) {
				xSpeed = xSpeed - .03;
				if (xSpeed < 0) {
					xSpeed = 0;
				}
			} else {
				xSpeed = xSpeed + .03;
				if (xSpeed > 0) {
					xSpeed = 0;
				}
			}
		}
		if (ySpeed != 0) {
			if (ySpeed > 0) {
				ySpeed = ySpeed - .03;
				if (ySpeed < 0) {
					ySpeed = 0;
				}
			} else {
				ySpeed = ySpeed + .03;
				if (ySpeed > 0) {
					ySpeed = 0;
				}
			}
		}
		if (spinSpeed != 0) {
			if (spinSpeed > 0) {
				spinSpeed = spinSpeed - .01;
				if (spinSpeed < 0) {
					spinSpeed = 0;
				}
			} else {
				spinSpeed = spinSpeed + .01;
				if (spinSpeed > 0) {
					spinSpeed = 0;
				}
			}
		}
	}
}

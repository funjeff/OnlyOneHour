package gameObjects;

import java.awt.Point;
import java.awt.geom.Line2D;

import engine.GameObject;
import engine.Sprite;

public class Maro extends GameObject {
	
	int[] floors = new int[] {0,   427, 192, 427,
							  287, 375, 506, 378,
							  582, 303, 791, 303,
							  816, 159, 932, 152};
	int[] wallsLeft = new int[] {287, 375, 288, 480,
							     582, 303, 583, 480,     
							     816, 159, 877, 317,
							     877, 317, 924, 539};
	int[] wallsRight = new int[] {192, 427, 193, 480,
			                      506, 378, 507, 480,
			                      791, 303, 792, 480};
	
	double velocityX;
	double velocityY;
	boolean isOnFloor;
	
	public Maro () {
		this.declare (106, 365);
		this.setSprite (new Sprite ("resources/sprites/super maro.png"));
		this.useSpriteHitbox ();
	}
	
	boolean checkCollision (int[] lines) {
		double[] hboxPts = new double[] {
				getX (), getY (),
				getX () + hitbox ().getWidth (), getY (),
				getX (), getY () + hitbox ().getHeight (),
				getX () + hitbox ().getWidth (), getY () + hitbox ().getHeight ()
		};
		for (int i = 0; i < lines.length; i += 4) {
			boolean parity = false;
			for (int j = 0; j < 4; j++) {
				double wx = hboxPts[j * 2];
				double wy = hboxPts[j * 2 + 1];
				double m = (double)(lines[i + 3] - lines[i + 1]) / (lines[i + 2] - lines[i]);
				double y = m * (wx - lines[i]) + lines[i + 1];
				if (j == 0) {
					parity = y > wy;
				} else {
					if (y > wy != parity) {
						return true;
					}
					parity = y > wy;
				}
			}
		}
		return false;
	}
	
	@Override
	public void frameEvent () {
		if (keyDown('W')) {
			if (!isOnFloor) {
				velocityY = 16;
				setY (getY () - velocityY);
				isOnFloor = false;
			}
		}
		if (keyDown('A')) {
			setX (getX () - 3);
			if (checkCollision(wallsRight)) {
				setX (getX () + 3);
			}
		} else if (keyDown('D')) {
			setX (getX () + 3);
			if (checkCollision(wallsLeft)) {
				setX (getX () - 3);
			}
		}
	}

}

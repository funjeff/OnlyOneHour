package gameObjects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.util.Arrays;

import engine.GameObject;
import engine.RenderLoop;
import engine.Sprite;

public class Maro extends GameObject {
	
	int[] floors = new int[] {0,   427, 192, 427,
							  287, 378, 506, 378,
							  582, 303, 791, 303,
							  816, 159, 932, 159};
	int[] wallsLeft = new int[] {287, 378, 288, 540,
							     582, 306, 583, 540,     
							     816, 162, 877, 317,
							     877, 320, 924, 539,
							     363, 278, 364, 370,
							     900, 0,   900, 540};
	int[] wallsRight = new int[] {192, 430, 193, 540,
			                      506, 381, 507, 540,
			                      791, 306, 792, 540,
			                      363, 278, 364, 370};
	
	double velocityX;
	double velocityY;
	boolean isOnFloor;
	boolean moved;
	public boolean died;
	public boolean dead;
	public boolean won;
	
	double savedX;
	double savedY;
	int frameNum = 0;
	int frameToDraw = 3;
	
	int framenum = 0;
	Sprite maroA = new Sprite ("resources/sprites/super maro.png");
	Sprite maroB = new Sprite ("resources/sprites/supest maro.png");
	
	public Maro (int difficulty) {
		this.declare (100, 100);
		this.setSprite (new Sprite ("resources/sprites/super maro.png"));
		this.setHitboxAttributes (14, 0, 45, 146);
		frameToDraw = (difficulty + 1) * 3;
	}
	
	private boolean isBetween (double val, double boundA, double boundB) {
		return val > Math.min (boundA, boundB) && val < Math.max (boundA, boundB);
	}
	
	private double[] isectLines(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {
		double m1 = (y2 - y1) / (x2 - x1);
		double m2 = (y4 - y3) / (x4 - x3);
		double b1 = y1 - m1 * x1;
		double b2 = y2 - m2 * x2;
		double x = (b2 - b1) / (m1 - m2);
		double y = m1 * x + b1;
		return new double[] {x, y3}; // I gave up
	}
	
	double[] checkCollision (int[] lines) {
		double[] hboxPts = new double[] {
			getX (), getY (),
			getX () + hitbox ().getWidth (), getY (),
			getX () + hitbox ().getWidth (), getY () + hitbox ().getHeight (),
			getX (), getY () + hitbox ().getHeight ()
		};
		for (int i = 0; i < 4; i++) {
			int hbox_idx_1 = i * 2;
			int hbox_idx_2 = (hbox_idx_1 + 2) % 8;
			double hx1 = hboxPts[hbox_idx_1];
			double hy1 = hboxPts[hbox_idx_1 + 1];
			double hx2 = hboxPts[hbox_idx_2];
			double hy2 = hboxPts[hbox_idx_2 + 1];
			for (int j = 0; j < lines.length; j += 4) {
				double lx1 = lines[j];
				double ly1 = lines[j + 1];
				double lx2 = lines[j + 2];
				double ly2 = lines[j + 3];
				if (Line2D.linesIntersect (hx1, hy1, hx2, hy2,
									       lx1, ly1, lx2, ly2)) {
					return isectLines (hx1, hy1, hx2, hy2,
									   lx1, ly1, lx2, ly2);
				}
			}
		}
		return null;
	}
	
	@Override
	public void frameEvent () {
		
		double speed = 12;
		
		if (velocityY < 32) {
			velocityY += 8;
		}
		setY (getY () + velocityY);
		if (checkCollision(floors) != null) {
			setY (checkCollision(floors)[1] - hitbox().getHeight ());
			isOnFloor = true;
		}
		
		
		if (keyPressed('W')) {
			if (isOnFloor) {
				velocityY = -48;
				setY (getY () + velocityY);
				isOnFloor = false;
			}
		}
		if (keyDown('A')) {
			setX (getX () - speed);
			if (checkCollision(wallsRight)  != null) {
				setX (getX () + speed);
			}
			moved = true;
		} else if (keyDown('D')) {
			setX (getX () + speed);
			if (checkCollision(wallsLeft) != null) {
				setX (getX () - speed);
			}
			moved = true;
		}
		
		// Falling
		if (getY () > 540 && !dead) {
			died = true;
		}
	}
	
	@Override
	public void draw () {
		if (frameNum % frameToDraw == 0) {
			savedX = getX ();
			savedY = getY ();
			if (died == true) {
				System.out.println("ded");
				died = false;
				dead = true;
			}
			if (this.isColliding ("Star")) {
				if (!won) {
					won = true;
				}
			}
			if (moved == true) {
				framenum = (framenum + 1) % 2;
				Sprite[] maros = new Sprite[] {maroA, maroB};
				setSprite (maros[framenum]);
				moved = false;
			} else {
				setSprite (maroA);
				framenum = 0;
			}
		}
		getSprite ().draw ((int)savedX, (int)savedY);
		frameNum ++;
	}

}

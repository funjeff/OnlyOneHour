package engine;

import java.util.Random;

public class monster extends GameObject{
	
	public int dir;
	public int xPos;
	public int yPos;
	
	public monster(int direction) {
		this.dir = direction;
		this.setSprite(new Sprite("resources/sprites/monster.png"));
		if (direction == 0) { //left
			xPos = 0;
			yPos = 280;
		}
		else if (direction == 1) { //right 
			xPos = 960;
			yPos = 280;
		}
		else if (direction == 2) { //up
			xPos = 480;
			yPos = 0;
		}
		else { //down
			xPos = 480;
			yPos = 540;
		}
		this.declare(xPos, yPos);
		this.useSpriteHitbox();
	}
	
	@Override
	public void frameEvent() {
		if (dir == 0) { //left
			if (xPos > 30) {
				xPos += 30;
			}
			else {
				xPos += 1;
			}
		}
		else if (dir == 1) { //right 
			if (xPos < 930) {
				xPos -= 30;
			}
			else {
				xPos -= 1;
			}
		}
		else if (dir == 2) { //up
			if (yPos > 30) {
				yPos += 30;
			}
			else {
				yPos += 1;
			}
		}
		else if (dir == 3){ //down
			if (yPos < 510) {
				yPos -= 30;
			}
			else {
				yPos -= 1;
			}
		}
		this.setX(xPos);
		this.setY(yPos);
	}
}

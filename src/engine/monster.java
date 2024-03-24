package engine;

import java.util.Random;

public class monster extends GameObject{
	
	public int dir;
	public int xPos;
	public int yPos;
	
	public double speed = 1.0;
	
	public monster(int direction) {
		this.dir = direction;
		this.setRenderPriority(7);
		if (direction == 0) { //left
			xPos = 0;
			yPos = 220;
			this.setSprite(new Sprite("resources/sprites/monster.png"));
			this.getAnimationHandler().setFlipHorizontal(true);
		}
		else if (direction == 1) { //right 
			xPos = 960;
			yPos = 220;
			this.setSprite(new Sprite("resources/sprites/monster.png"));
		}
		else if (direction == 2) { //up
			xPos = 410;
			yPos = -120;
			this.setSprite(new Sprite("resources/sprites/monsteru.png"));
			this.getAnimationHandler().setFlipVertical(true);
		}
		else { //down
			xPos = 410;
			yPos = 550;
			this.setSprite(new Sprite("resources/sprites/monsteru.png"));
		}
		
		this.declare(xPos, yPos);
		this.useSpriteHitbox();
	}
	
	@Override
	public void frameEvent() {
		if (dir == 0) { //left
			if (xPos > 30) {
				xPos += 30*speed;
			}
			else {
				xPos += 1*speed;
			}
		}
		else if (dir == 1) { //right 
			if (xPos < 930) {
				xPos -= 30*speed;
			}
			else {
				xPos -= 1*speed;
			}
		}
		else if (dir == 2) { //up
			if (yPos > -90) {
				yPos += 30*speed;
			}
			else {
				yPos += 1*speed;
			}
		}
		else if (dir == 3){ //down
			if (yPos < 510) {
				yPos -= 30*speed;
			}
			else {
				yPos -= 1*speed;
			}
		}
		this.setX(xPos);
		this.setY(yPos);
	}
}

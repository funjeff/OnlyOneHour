package engine;

import java.util.Random;

public class monster extends GameObject{
	
	public int dir;
	public int xPos;
	public int yPos;
	static Random rand = new Random();
	
	public double speed = 1.0;
	boolean charge = false;
	boolean usesU = false;
	int flipTime =2;
	boolean hasScreamed = false;
	
	Sprite u1 = new Sprite("resources/sprites/monsteru.png");
	Sprite u2 = new Sprite("resources/sprites/monster2u.png");
	
	Sprite nu1 = new Sprite("resources/sprites/monster.png");
	Sprite nu2 = new Sprite("resources/sprites/monster2.png");
	
	
	public monster(int direction) {
		this.dir = direction;
		this.setRenderPriority(7);
		if (direction == 0) { //left
			xPos = -140;
			yPos = 220;
			this.setSprite(nu1);
			this.getAnimationHandler().setFlipHorizontal(true);
		}
		else if (direction == 1) { //right 
			xPos = 960;
			yPos = 220;
			this.setSprite(nu1);
		}
		else if (direction == 2) { //up
			xPos = 410;
			yPos = -120;
			this.setSprite(u1);
			this.getAnimationHandler().setFlipVertical(true);
			usesU = true;
		}
		else { //down
			xPos = 410;
			yPos = 550;
			this.setSprite(u1);
			usesU = true;
		}
		
		this.declare(xPos, yPos);
		this.useSpriteHitbox();
	}
	
	@Override
	public void frameEvent() {
		if (dir == 0) { //left
			if (xPos > -110) {
				xPos += 30*speed;
				charge = true;
			}
			else {
				xPos += 1*speed;
			}
		}
		else if (dir == 1) { //right 
			if (xPos < 930) {
				xPos -= 30*speed;
				charge = true;
			}
			else {
				xPos -= 1*speed;
			}
		}
		else if (dir == 2) { //up
			if (yPos > -90) {
				yPos += 30*speed;
				charge = true;
			}
			else {
				yPos += 1*speed;
			}
		}
		else if (dir == 3){ //down
			if (yPos < 510) {
				yPos -= 30*speed;
				charge = true;
			}
			else {
				yPos -= 1*speed;
			}
		}
		if (charge && !hasScreamed) {
			int index = rand.nextInt(5);
			AudioClip ac = new AudioClip("file:resources/sounds/comingThrough1.wav");
			if (index == 0) {
				ac = new AudioClip("file:resources/sounds/comingThrough2.wav");
			}
			else if (index == 1) {
				ac = new AudioClip("file:resources/sounds/haiyah.wav");
			}
			else if (index == 2) {
				ac = new AudioClip("file:resources/sounds/heuh.wav");
			}
			else if (index == 3) {
				ac = new AudioClip("file:resources/sounds/waaowaaowaaaaah.wav");
			}
			ac.play();
			hasScreamed = true;
		}
		this.setX(xPos);
		this.setY(yPos);
		
		if (charge) {
			if (flipTime != 0) {
				flipTime = flipTime - 1;
			} else {
				flipTime = 2;
				if (usesU) {
					if (this.getSprite().equals(u1)) {
						this.setSprite(u2);
					} else {
						this.setSprite(u1);
					}
				} else {
					if (this.getSprite().equals(nu1)) {
						this.setSprite(nu2);
					} else {
						this.setSprite(nu1);
					}
				}
			}
		}
	}
}

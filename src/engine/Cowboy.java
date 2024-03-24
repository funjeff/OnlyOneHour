package engine;

public class Cowboy extends GameObject{
	
	int shot = 0;
	int shootTimer = -1;
	boolean requriesClick = false;
	boolean shotOther = false;
	
	public Cowboy (boolean color) {
		if (color) {
			this.setSprite(new Sprite ("resources/sprites/cowboy1.png"));
		} else {
			this.setSprite(new Sprite ("resources/sprites/cowboy2.png"));
		}
	}
	
	@Override
	public void frameEvent() {
		if (shot == 1) {
			this.setX(this.getX() + 13);
			this.setY(this.getY() - 1);
			this.setDrawRotation(this.drawRotation - .3);
		}
		if (shot == 2) {
			this.setX(this.getX() - 13);
			this.setY(this.getY() - 1);
			this.setDrawRotation(this.drawRotation + .3);
		}
		
		if (requriesClick && mouseButtonReleased(0) && shootTimer > 0 && shot == 0) {
			shotOther = true;
		}
		
		if (requriesClick && mouseButtonReleased(0) && shootTimer == -1) {
			shot = 2;
		}
		
		if (!requriesClick && shootTimer == 0) {
			shotOther = true;
		}
	}
	
	public void getShot (boolean dir) {
		if (dir) {
			shot = 1;
		} else {
			shot = 2;
		}
	}
}

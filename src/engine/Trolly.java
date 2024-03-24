package engine;

public class Trolly extends GameObject{
	
	boolean path = false;
	Arrow indicatior;
	boolean reachedChoice = false;
	boolean reachedStrateTracks = false;
	
	LeverGuy guy;
	
	double speed = 1.0;
	
	public Trolly () {
		this.setSprite(new Sprite ("resources/sprites/train.png"));
		indicatior = new Arrow();
		indicatior.setDir(path);
		this.setRenderPriority(5);
		guy = new LeverGuy ();
		guy.setX(100);
		guy.setY(300);
	}
	
	@Override
	public void frameEvent() {
		if (!reachedChoice) {
			this.setX(this.getX() + (2*speed));
			if (this.getX() >= 140) {
				reachedChoice = true;
			}
		} else {
			if (path) {
				if (!reachedStrateTracks) {
					this.setX(this.getX() + (1.6*speed));
					this.setY(this.getY() - (1.2*speed));
					if (this.drawRotation > -.5 && this.getX() < 230) {
						this.setDrawRotation(this.drawRotation - (0.02*speed));
						this.setY(this.getY() + 2*speed);
					}
					
					if (this.getX() > 230) {
						if (this.drawRotation < -.1) {
							this.setDrawRotation(this.drawRotation+ (0.0063*speed));
							this.setY(this.getY() - 1.3*speed);
							this.setX(this.getX() + .5 * speed);
						}
					}
					if (this.getX()>258) {
						reachedStrateTracks = true;
					}
				} else {
					if (this.drawRotation < -.1) {
						this.setDrawRotation(this.drawRotation+ (0.0063* speed));
						this.setY(this.getY() - 1.3*speed);
						this.setX(this.getX() + .5 * speed);
					}
					this.setX(this.getX() + (2*speed));
				}
			} else {
				if (!reachedStrateTracks) {
					this.setX(this.getX() + (1.6*speed));
					this.setY(this.getY() + (.8*speed));
					if (this.drawRotation < .26) {
						this.setDrawRotation(this.drawRotation + (0.005*speed));
						this.setY(this.getY() - 1.5*speed);
						this.setX(this.getX() + .65*speed);
					}
					if (this.getX() >450) {
						reachedStrateTracks = true;
					}
				} else {
					this.setX(this.getX() + (1.6*speed));
					this.setY(this.getY() + (.6*speed));
				}
			}
		}
		
		if (this.mouseButtonReleased(0)) {
			if (!reachedChoice) {
				path = !path;
				indicatior.setDir(path);
			}
			guy.flipLever();
		}
		indicatior.setX(this.getX() + 290);
		indicatior.setY(this.getY() - 5);
		
	}
	
	@Override
	public void draw() {
		super.draw();
		if (!reachedChoice) {
			indicatior.draw();
		}
		guy.draw();
	}
		
	public void setSpeed (double speed) {
		this.speed = speed;
	}
	
	public class Arrow extends GameObject {
		public Arrow () {
			this.setSprite(new Sprite ("resources/sprites/arrow up.png"));
		}
		
		public void setDir(boolean upOrDown) {
			if (upOrDown) {
				this.setSprite(new Sprite("resources/sprites/arrow up.png"));
			} else {
				this.setSprite(new Sprite("resources/sprites/arrow down.png"));
			}
		}
	}
	public class LeverGuy extends GameObject {

		Sprite pressed = new Sprite ("resources/sprites/lever pressed.png");
		Sprite depressed = new Sprite ("resources/sprites/lever depressed.png");
		
		public LeverGuy () {
			this.setSprite(depressed);
		}
		
		public void flipLever () {
			if (this.getSprite().equals(depressed)) {
				this.setSprite(pressed);
			} else {
				this.setSprite(depressed);
			}
		}
	}

}

package engine;

public class Trolly extends GameObject{
	
	boolean path = false;
	Arrow indicatior;
	boolean reachedChoice = false;
	boolean reachedStrateTracks = false;
	
	double speed = 1.0;
	
	public Trolly () {
		this.setSprite(new Sprite ("resources/sprites/train.png"));
		indicatior = new Arrow();
		indicatior.setDir(path);
		this.setRenderPriority(5);
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
				System.out.println(this.getX());
				this.setX(this.getX() + (1.6*speed));
				this.setY(this.getY() + (1*speed));
				if (this.drawRotation < .4 && this.getX() < 230) {
					this.setDrawRotation(this.drawRotation + (0.01*speed));
					this.setY(this.getY() - 4*speed);
					this.setX(this.getX() + 1.3*speed);
				}
			}
		}
		
		if (this.mouseButtonReleased(0)) {
			if (!reachedChoice) {
				path = !path;
				indicatior.setDir(path);
			}
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
}

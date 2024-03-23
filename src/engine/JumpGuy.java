package engine;

public class JumpGuy extends GameObject {
	
	public JumpGuy() {
		this.setSprite(new Sprite("resources/sprites/reporter.png"));
	}
	double vy = -15;
	boolean isLoner = false;
	Pipe spawner;
	double speed = .5;
	
	@Override
	public void frameEvent () {
		this.setY(this.getY() + vy);
		vy = vy + speed;
		if (this.getY() > spawner.getY()) {
			if (this.isLoner) {
				spawner.lost = true;
			}
			spawner.clear = true;
			this.forget();
		}
	}

	public void makeLoner () {
		isLoner = true;
	}
	
	public void setSpeed (double newSpeed) {
		speed = newSpeed;
	}
	
}

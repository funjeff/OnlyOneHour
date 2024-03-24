package engine;

public class Clock extends GameObject {
	public Clock () {
		this.setSprite(new Sprite ("resources/sprites/clock1.png"));
	}

	
	public void setClockHour (int hour) {
		this.setSprite(new Sprite("resources/sprites/clock" + hour + ".png"));
	}
}

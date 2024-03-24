package gameObjects;

import engine.AudioClip;
import engine.GameObject;
import engine.Sprite;

public class StartButton extends GameObject {
	
	boolean pushed = false;
	
	public int timeToNextStart;
	public boolean weGoin;
	
	public long timeToStart = 0;
	
	public double ax = 0;
	public double vx = 0;
	
	AudioClip clip = new AudioClip ("file:resources/sounds/revvingCar.wav");
	
	public StartButton () {
		setSprite (new Sprite ("resources/sprites/go.png"));
		this.useSpriteHitbox ();
	}
	
	public boolean wasPushed () {
		return pushed;
	}
	
	@Override
	public void frameEvent () {
		if (this.mouseButtonClicked (0)) {
			if (!pushed) {
				timeToStart = System.currentTimeMillis () + timeToNextStart;
			}
			pushed = true;
			if (timeToNextStart > 3000) {
				clip.play ();
			}
			ax = 0.4 / ((double)timeToNextStart / 1000);
		}
		if (pushed) {
			if (vx < 32) {
				vx += ax;
			}
			this.setX (this.getX () + vx);
		}
	}

}

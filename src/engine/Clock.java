package engine;

import java.util.Random;

public class Clock extends GameObject {
	
	public boolean startTime = true;
	public boolean tikOrTok = true;
	static Random rand = new Random();
	
	public Clock () {
		this.setSprite(new Sprite ("resources/sprites/clock1.png"));
	}

	
	public void setClockHour (int hour) {
		if (startTime) {
			AudioClip ac = new AudioClip("file:resources/sounds/its" + hour + "OClock.wav");
			ac.play();
			startTime = false;
		}
		if (tikOrTok) {
			AudioClip tik = new AudioClip("file:resources/sounds/tik" + rand.nextInt(3) + ".wav");
			tik.play();
			tikOrTok = false;
		}
		else {
			AudioClip tok = new AudioClip("file:resources/sounds/tok" + rand.nextInt(3) + ".wav");
			tok.play();
			tikOrTok = true;
		}
		this.setSprite(new Sprite("resources/sprites/clock" + hour + ".png"));
	}
}

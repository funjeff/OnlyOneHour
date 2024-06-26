package engine;

import java.util.Random;

public class Cowboy extends GameObject{
	
	int shot = 0;
	int shootTimer = -1;
	boolean requriesClick = false;
	boolean shotOther = false;
	boolean soundEff = true;

	static Random rand = new Random();
	
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
			if (soundEff) {
				int index = rand.nextInt(5);
				AudioClip gun = new AudioClip("file:resources/sounds/gun" + rand.nextInt(3) + ".wav");
				gun.play();
				AudioClip ac = new AudioClip("file:resources/sounds/shouldntaChallengedMe.wav");
				if (index == 0) {
					ac = new AudioClip("file:resources/sounds/jeffreyFemurCrusher.wav");
				}
				else if (index == 1) {
					ac = new AudioClip("file:resources/sounds/goodHeavensIMightDieSoon.wav");
				}
				else if (index == 2) {
					ac = new AudioClip("file:resources/sounds/inDyingGetMeADoctor.wav");
				}
				else if (index == 3) {
					ac = new AudioClip("file:resources/sounds/looksLikeImTheLastOneStanding.wav");
				}
				ac.play();
				soundEff = false;
			}
		}
		
		if (!requriesClick && shootTimer == 0) {
			shotOther = true;
		}
	}
	
	public void getShot (boolean dir) {
		if (soundEff) {
			int index = rand.nextInt(6);
			AudioClip gun = new AudioClip("file:resources/sounds/gun" + rand.nextInt(3) + ".wav");
			gun.play();
			AudioClip ac = new AudioClip("file:resources/sounds/shouldntaChallengedMe.wav");
			if (index == 0) {
				ac = new AudioClip("file:resources/sounds/jeffreyFemurCrusher.wav");
			}
			else if (index == 1) {
				ac = new AudioClip("file:resources/sounds/goodHeavensIMightDieSoon.wav");
			}
			else if (index == 2) {
				ac = new AudioClip("file:resources/sounds/inDyingGetMeADoctor.wav");
			}
			else if (index == 3) {
				ac = new AudioClip("file:resources/sounds/looksLikeImTheLastOneStanding.wav");
			}
			ac.play();
			soundEff = false;
		}
		if (dir) {
			shot = 1;
		} else {
			shot = 2;
		}
	}
}

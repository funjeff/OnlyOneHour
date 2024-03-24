package gameObjects;

import java.awt.Color;

import engine.GameObject;
import engine.IntroAnimation;
import engine.Sprite;

public class TitleScreen extends GameObject {
	
	GameBackground bg = new GameBackground (new Sprite ("resources/sprites/only1hour.png"));
	StartButton sb = new StartButton ();
	public boolean weGoin = false;
	public int currLoopTime;
	
	public TitleScreen () {
		bg.declare ();
		sb.declare (600, 300);
	}
	
	public boolean wasPushed () {
		return sb.wasPushed ();
	}
	
	public void beginStartAnimation () {
		IntroAnimation anim = new IntroAnimation("HOUR", IntroAnimation.EFFECT_ID_WORDS_LEFT_TO_RIGHT);
		anim.firstWordText = "3";
		anim.secondWordText = "2";
		anim.thirdWordText = "1";
		anim.setWordColor (Color.black);
		anim.declare ();
	}
	
	public void delete () {
		bg.forget ();
		bg.hide ();
		sb.forget ();
		this.forget ();
	}
	
	@Override
	public void frameEvent () {
		int timeToNext = 0;
		if (currLoopTime > 6000) {
			timeToNext = (8348 - currLoopTime) + 6261;
		} else {
			timeToNext = 6261 - currLoopTime;
		}
		sb.timeToNextStart = timeToNext;
		
		if (sb.pushed) {
			if (sb.timeToStart != 0) {
				if (System.currentTimeMillis () >= sb.timeToStart) {
					if (weGoin == false) {
						weGoin = true;
						beginStartAnimation();
					}
				}
			}
		}
	}

}

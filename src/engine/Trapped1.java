package engine;

import java.util.Random;

public class Trapped1 extends GameObject {
	boolean exploded = false;
	public Trapped1 () {
		this.setSprite(new Sprite("resources/sprites/captured 1.png"));
	}
	
		
	public void makeExplode () {
		exploded = true;
		
		Random rand = new Random ();
		int bloodNum = rand.nextInt(30)+ 30;
		
		for (int i = 0; i < bloodNum; i++) {
			BloodDroplet b = new BloodDroplet();
			b.declare(this.getX() + 130, this.getY() + 100);
		}
		
		FunnyEmoticon emote = new FunnyEmoticon (new Sprite ("resources/sprites/emoji dying.png"));
		emote.declare(this.getX() + 90, this.getY() + 90);
		
		this.hide();
	}
	public boolean hasExploded () {
		return exploded;
	}
	
}

package engine;

import java.util.Random;

public class Trapped5 extends GameObject {
	boolean exploded = false;
	public Trapped5 () {
		this.setSprite(new Sprite("resources/sprites/captured 5.png"));
	}
	
	public void makeExplode (int splodeX, int splodeY) {
		exploded = true;
		
		Random rand = new Random ();
		int bloodNum = rand.nextInt(10)+ 20;
		
		for (int i = 0; i < bloodNum; i++) {
			BloodDroplet b = new BloodDroplet();
			b.declare(this.getX() + splodeX, this.getY() + splodeY);
		}
		
		if (splodeX == 50) {
			FunnyEmoticon emote = new FunnyEmoticon (new Sprite ("resources/sprites/emoji dying - Copy.png"));
			emote.declare(this.getX() + splodeX, this.getY() + splodeY);
		}
		if (splodeX == 100) {
			FunnyEmoticon emote = new FunnyEmoticon (new Sprite ("resources/sprites/emoji dying - Copy (2).png"));
			emote.declare(this.getX() + splodeX, this.getY() + splodeY);			
		}
		if (splodeX == 130) {
			FunnyEmoticon emote = new FunnyEmoticon (new Sprite ("resources/sprites/emoji dying - Copy (3).png"));
			emote.declare(this.getX() + splodeX, this.getY() + splodeY);			
		}
		if (splodeX == 160) {
			FunnyEmoticon emote = new FunnyEmoticon (new Sprite ("resources/sprites/emoji dying - Copy (4).png"));
			emote.declare(this.getX() + splodeX, this.getY() + splodeY);			
		}
		if (splodeX == 210) {
			FunnyEmoticon emote = new FunnyEmoticon (new Sprite ("resources/sprites/emoji dying - Copy (5).png"));
			emote.declare(this.getX() + splodeX, this.getY() + splodeY);			
		}
		//this.hide();
	}
	public boolean hasExploded () {
		return exploded;
	}
}

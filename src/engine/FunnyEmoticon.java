package engine;

import java.awt.image.BufferedImage;

public class FunnyEmoticon extends GameObject {
	int timer = 80;
	float opacity = .6f;
	static BufferedImage copy;
	
	public FunnyEmoticon (Sprite s) {
		if (copy == null) {
			this.setSprite(s);
			copy = this.getSprite().getFrame(0);
		} else {
			this.setSprite(new Sprite (copy));
		}
		this.getSprite().setOpacity(.6f, 0);
		this.setRenderPriority(6);
	}
	
	public void frameEvent () {
		this.setY(this.getY() - 1);
		if (timer == 0) {
			this.getSprite().setOpacity(opacity, 0);
			opacity = opacity - .01f;
			if (opacity <= 0) {
				this.forget();
			}
		} else {
			timer = timer - 1;
		}
	}
	
}

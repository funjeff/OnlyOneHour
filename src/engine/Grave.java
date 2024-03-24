package engine;

import java.util.Random;

public class Grave extends GameObject{
	int num;
	public Grave (int grave) {
		num = grave;
	}
	
	@Override
	public void onDeclare () {
		this.setRenderPriority(-3);
		
		switch (num) {
		case 0:
			this.setSprite(new Sprite ("resources/sprites/cowboyjesus.png"));
			this.setY(this.getY() - 40);
			break;
		case 1:
			this.setSprite(new Sprite ("resources/sprites/chipmcdalegrave.png"));
			break;
		case 2:
			this.setSprite(new Sprite ("resources/sprites/leftlarrygrave.png"));
			break;
		}
	}
}

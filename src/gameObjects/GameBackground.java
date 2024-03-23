package gameObjects;

import engine.GameObject;
import engine.Sprite;

public class GameBackground extends GameObject {
	
	public GameBackground (Sprite backgroundImg) {
		this.setSprite (backgroundImg);
		this.declare ();
		System.out.println("Hello!");
	}
	
}

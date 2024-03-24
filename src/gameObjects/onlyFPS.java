package gameObjects;

import engine.GameObject;
import engine.Sprite;

public class onlyFPS extends GameObject implements Game {

	Maro maro;
	GameBackground bg;
	Star star;
	
	@Override
	public void startGame (int difficulty) {
		bg = new GameBackground(new Sprite("resources/sprites/maro.png"));
		maro = new Maro (difficulty);
		star = new Star ();
	}

	@Override
	public void endGame () {
		bg.forget();
		maro.forget ();
		star.forget ();
	}

	@Override
	public boolean isGameOver () {
		return maro.dead || maro.won;
	}

	@Override
	public boolean wasGameWon () {
		return maro.won;
	}
	
	

}

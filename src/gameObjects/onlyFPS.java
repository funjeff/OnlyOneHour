package gameObjects;

import engine.ConditionDisplay;
import engine.GameObject;
import engine.Sprite;

public class onlyFPS extends GameObject implements Game {

	Maro maro;
	GameBackground bg;
	Star star;
	ConditionDisplay cd;
	
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
		if (cd != null) {
			cd.forget();
		}
	}

	@Override
	public boolean isGameOver () {
		if (maro.won) {
			if (cd == null) {
				cd = new ConditionDisplay(true);
				cd.declare();
			}
		}
		return maro.dead || maro.won;
	}

	@Override
	public boolean wasGameWon () {
		return maro.won;
	}
	
	

}

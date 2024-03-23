package engine;

import java.util.Random;

public class onlyCowboy {
	
	int difficulty = 0;
	Cowboy boy1;
	Cowboy boy2;
	Clock clock;

	public onlyCowboy() {
	}
	
	public void setTheScene() {
		boy1 = new Cowboy (true);
		boy2 = new Cowboy (false);
		
		clock = new Clock ();
		
		boy1.declare(100, 250);
		boy2.declare(800, 250);
		boy2.getAnimationHandler().setFlipHorizontal(true);
		
		clock.declare(430, 0);
		
	}
	
	public void startGame(int difficulty) {
		this.difficulty = difficulty;
		setTheScene();
	}
	
	public void endGame() {
		
	}
	
	public boolean isGameOver() {
		return false;
	}
	
	public boolean wasGameWon() {
		return isGameWon;
	}
}

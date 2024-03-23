package engine;

import java.util.Random;

public class onlyTimer {
	
	public boolean isGameWon = false;
	Timer t = new Timer (60000);
	
	
	public onlyTimer() {
		
	}
	
	public void setTheScene() {
		t.declare(450, 250);
		t.startTimer();
	}
	
	public void startGame(int difficulty) {

		setTheScene();
	}
	
	public void endGame() {

		t.forget();
	}
	
	public boolean isGameOver() {
		if (t.hasExpired()) {
			isGameWon = true;
			return true;
		}
		return false;
	}
	
	public boolean wasGameWon() {
		return isGameWon;
	}
}

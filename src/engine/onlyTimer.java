package engine;

import java.awt.Color;
import java.util.Random;

import gameObjects.BigText;
import gameObjects.Game;

public class onlyTimer extends GameObject implements Game {
	
	public boolean isGameWon = false;
	Timer t = new Timer (60000);
	BigText sikeText = null;
	
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
		sikeText.forget ();
	}
	
	public boolean isGameOver() {
		if (t.getElapsed () > 4000) {
			if (!isGameWon) {
				sikeText = new BigText("SIKE!", Color.RED, 120);
				sikeText.declare(300, 270);
				t.forget ();
			}
			isGameWon = true;
			return true;
		}
		return false;
	}
	
	public boolean wasGameWon() {
		return isGameWon;
	}
}

package engine;

import java.util.Random;

public class onlyPipe {
	
	public int difficulty;
	public boolean isGameOver = false;
	public boolean isGameWon = false;

	public onlyPipe() {
	}
	
	public void setTheScene() {
		Random rand = new Random();
		direction = rand.nextInt(4);
		monster m = new monster(direction);
	}
	
	public void startGame(int difficulty) {
		this.difficulty = difficulty;
		setTheScene();
	}
	
	public void endGame() {
		
	}
	
	public boolean isGameOver() {
		return isGameOver;
	}
	
	public boolean wasGameWon() {
		return isGameWon;
	}
}

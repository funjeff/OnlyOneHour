package engine;

import java.util.Random;

public class onlyDodge {
	public static void main(String[] args) {
		onlyBinary ob = new onlyBinary();
		ob.startGame(0);
	}
	
	public int difficulty;
	public boolean isGameOver = false;
	public boolean isGameWon = false;
	public int direction;

	public onlyDodge() {
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

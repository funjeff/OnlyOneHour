package engine;

import java.util.Random;

import gameObjects.Game;


public class onlyBinary extends GameObject implements Game {
	
	public int difficulty;
	public int[] binaryNumbers;
	public boolean isGameOver = false;
	public boolean isGameWon = false;
	public bit[] allBits;
	public Timer t = new Timer (5000);

	public Background2 code = new Background2 (new Sprite ("resources/sprites/code backgroud.png"));
	
	public onlyBinary() {
		
	}
	
	public void addNumbers() {
		
		t.declare(460, 100);
		t.startTimer();
		binaryNumbers = new int[10 + difficulty];
		allBits = new bit[10 + difficulty];
		Random rand = new Random();
		for (int i = 0; i < binaryNumbers.length; i++) {
			binaryNumbers[i] = rand.nextInt(2);
			bit b = new bit(binaryNumbers[i]);
			b.declare(((960/binaryNumbers.length) * (i)) + 10, 200);
			allBits[i] = b;
		}
		
	}
	
	public void startGame(int difficulty) {
		this.difficulty = difficulty;
		code.declare();
		addNumbers();
	}
	
	public void endGame() {
		for (int j = 0; j < allBits.length; j++) {
			allBits[j].forget();
		}
		code.forget ();
	}
	
	public boolean isGameOver() {
		if (t.hasExpired()) {
			isGameWon = false;
			return true;
		}
		for (int i = 0; i < allBits.length; i++) {
			if (allBits[i].selectedWrong) {
				for (int j = 0; j < allBits.length; j++) {
					allBits[j].setSprite(new Sprite("resources/sprites/0 bit.png"));
				}
				isGameWon = false;
				return true;
			}
		}
		for (int i = 0; i < allBits.length; i++) {
			if (allBits[i].val == 0) {
				return false;
			}
		}
		return true;
	}
	
	public boolean wasGameWon() {
		return isGameWon;
	}
}
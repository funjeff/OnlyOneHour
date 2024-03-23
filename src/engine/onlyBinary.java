package engine;

import java.util.Random;


public class onlyBinary {
	public static void main(String[] args) {
		onlyBinary ob = new onlyBinary();
		ob.startGame(0);
	}
	
	public int difficulty;
	public int[] binaryNumbers;
	public boolean isGameOver = false;
	public boolean isGameWon = false;
	public bit[] allBits;

	public onlyBinary() {
	}
	
	public void addNumbers() {
		binaryNumbers = new int[10 + difficulty];
		allBits = new bit[10 + difficulty];
		Random rand = new Random();
		for (int i = 0; i < binaryNumbers.length; i++) {
			binaryNumbers[i] = rand.nextInt(2);
			bit b = new bit(binaryNumbers[i]);
			b.declare(0 + 960/(i+1), 0);
			allBits[i] = b;
			System.out.print(binaryNumbers[i]);
		}
	}
	
	public void startGame(int difficulty) {
		this.difficulty = difficulty;
		addNumbers();
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
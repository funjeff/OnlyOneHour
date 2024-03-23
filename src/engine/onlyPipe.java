package engine;

import java.util.Random;

public class onlyPipe {
	
	public int difficulty;
	public boolean isGameWon = false;

	Pipe p1;
	Pipe p2;
	Pipe p3;
	
	int spawnTimer = 0;
	
	public onlyPipe() {
	}
	
	public void setTheScene() {
		p1 = new Pipe(difficulty);
		p2 = new Pipe(difficulty);
		p3 = new Pipe(difficulty);
		
		p1.declare(100, 480);
		p2.declare(400, 480);
		p3.declare(700, 480);
	}
	
	public void startGame(int difficulty) {
		this.difficulty = difficulty;
		Random r = new Random();
		spawnTimer = (r.nextInt(100)+ 30) - (10*difficulty);
		if (spawnTimer < 0) {
			spawnTimer = 30;
		}
		setTheScene();
	}
	
	public void endGame() {
		if (p1.clear) {
			p1.forget();
		} else {
			p1.curGuy.forget();
			p1.forget();
		}
		if (p2.clear) {
			p2.forget();
		} else {
			p2.curGuy.forget();
			p2.forget();
		}
		if (p3.clear) {
			p3.forget();
		} else {
			p3.curGuy.forget();
			p3.forget();
		}
		
	}
	
	public boolean isGameOver() {
		
		if (p1.clear && p2.clear && p3.clear) {
			spawnTimer = spawnTimer - 1;
			if (spawnTimer == 0) {
				Random rand = new Random ();
				int amount = rand.nextInt(3) + 1;
				if (amount == 3) {
					p1.spawnGuy();
					p2.spawnGuy();
					p3.spawnGuy();
				}
				if (amount == 1) {
					switch (rand.nextInt(3) + 1) {
					case 1:
						p1.spawnLoner();
						break;
					case 2:
						p2.spawnLoner();
						break;
					case 3:
						p3.spawnLoner();
						break;
					}
				}
				if (amount == 2) {
					switch (rand.nextInt(3) + 1) {
					case 1:
						p2.spawnGuy();
						p3.spawnGuy();
						break;
					case 2:
						p1.spawnGuy();
						p3.spawnGuy();
						break;
					case 3:
						p1.spawnGuy();
						p2.spawnGuy();
						break;
					}
				}
				spawnTimer = (rand.nextInt(100)+ 30) - (10*difficulty);
				if (spawnTimer < 0) {
					spawnTimer = 30;
				}
			}
		}		
		if (p1.lost || p2.lost || p3.lost) {
			isGameWon = false;
			return true;
		} else {
			return false;
		}
	}
	
	public boolean wasGameWon() {
		return isGameWon;
	}
}

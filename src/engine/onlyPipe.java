package engine;

import java.util.Random;

import gameObjects.Game;

public class onlyPipe extends GameObject implements Game {
	
	public int difficulty;
	public boolean isGameWon = false;

	Pipe p1;
	Pipe p2;
	Pipe p3;
	
	int correctIteration = -1;
	int currentIteration = 1;
	
	Background2 sewerBackground = new Background2 (new Sprite ("resources/sprites/sewerbackground.png"));
	
	ConditionDisplay cd;
	
	public onlyPipe() {
	}
	
	public void setTheScene() {
		p1 = new Pipe(difficulty);
		p2 = new Pipe(difficulty);
		p3 = new Pipe(difficulty);
		
		p1.declare(100, 480);
		p2.declare(400, 480);
		p3.declare(700, 480);
		Random rand = new Random ();

		switch (rand.nextInt(3)) {
		case 0:
			p1.setSprite(new Sprite ("resources/sprites/pipe.png"));
			p1.setY(p1.getY() - 140);
			break;
		case 1:
			p1.setSprite(new Sprite ("resources/sprites/pipe transparent.png"));
			p1.setY(p1.getY() - 170);
			break;
		case 2:
			p1.setSprite(new Sprite ("resources/sprites/mariopipe.png"));
			p1.setY(p1.getY() - 55);
			break;
		}
		
		switch (rand.nextInt(3)) {
		case 0:
			p2.setSprite(new Sprite ("resources/sprites/pipe.png"));
			p2.setY(p2.getY() - 140);
			break;
		case 1:
			p2.setSprite(new Sprite ("resources/sprites/pipe transparent.png"));
			p2.setY(p2.getY() - 170);
			break;
		case 2:
			p2.setSprite(new Sprite ("resources/sprites/mariopipe.png"));
			p2.setY(p2.getY() - 55);
			break;
		}
		
		switch (rand.nextInt(3)) {
		case 0:
			p3.setSprite(new Sprite ("resources/sprites/pipe.png"));
			p3.setY(p3.getY() - 140);
			break;
		case 1:
			p3.setSprite(new Sprite ("resources/sprites/pipe transparent.png"));
			p3.setY(p3.getY() - 170);
			break;
		case 2:
			p3.setSprite(new Sprite ("resources/sprites/mariopipe.png"));
			p3.setY(p3.getY() - 55);
			break;
		}
		
		
		sewerBackground.declare();
		
		switch (difficulty) {
		case 0:
			correctIteration = rand.nextInt(2) + 1;
			break;
		case 1:
			correctIteration = rand.nextInt(3) + 1;
			break;
		case 2:
			correctIteration = rand.nextInt(3) + 1;
			break;
		case 3:
			correctIteration = rand.nextInt(4) + 1;
			break;
		case 4:
			correctIteration = rand.nextInt(4) + 1;
			break;
		case 5:
			correctIteration = rand.nextInt(5) + 1;
			break;
		case 6:
			correctIteration = rand.nextInt(5) + 1;
			break;
		case 7:
			correctIteration = rand.nextInt(5) + 1;
			break;
		case 8:
			correctIteration = rand.nextInt(5) + 1;
			break;
		case 9:
			correctIteration = rand.nextInt(6) + 1;
			break;
			
		}
	}
	
	public void startGame(int difficulty) {
		this.difficulty = difficulty;
		Random r = new Random();
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
		
		if (cd != null) {
			cd.forget();
		}
		sewerBackground.forget();
	}
	
	public boolean isGameOver() {
		
		if (p1.clear && p2.clear && p3.clear) {
				Random rand = new Random ();
				if (currentIteration != correctIteration) {
					int amount = rand.nextInt(2) + 1;
					if (amount == 2) {
						p1.spawnGuy();
						p2.spawnGuy();
						p3.spawnGuy();
					}
					if (amount == 1) {
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
				} else {
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
				currentIteration = currentIteration + 1;
			}		
		if (p1.lost || p2.lost || p3.lost) {
			isGameWon = false;
			if (cd == null) {
				cd = new ConditionDisplay(isGameWon);
				cd.declare();
			}
			return true;
		}
		
		if (p1.won || p2.won || p3.won) {
			isGameWon = true;
			if (cd == null) {
				cd = new ConditionDisplay(isGameWon);
				cd.declare();
			}
			return true;
		} else {
			return false;
		}
		
	}
	
	public boolean wasGameWon() {
		return isGameWon;
	}
}

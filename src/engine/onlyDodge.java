package engine;

import java.util.Random;

import gameObjects.Game;

public class onlyDodge extends GameObject implements Game {
	
	static Random rand = new Random();
	
	public static void main(String[] args) {
		onlyBinary ob = new onlyBinary();
		ob.startGame(0);
	}
	
	public int difficulty;
	public boolean isGameOver = false;
	public boolean isGameWon = false;
	public int direction;
	dodger d;
	monster m;
	ConditionDisplay cd;
	
	int chargeTimer = -1;
	
	Sprite dodger2 = new Sprite ("resources/sprites/dodger2.png");
	
	Background2 dodgeBackground = new Background2(new Sprite ("resources/sprites/ocean_png.png"));
	
	public onlyDodge() {
	}
	
	public void setTheScene() {
		int index = rand.nextInt(3);
		AudioClip ac = new AudioClip("file:resources/sounds/ImHungry.wav");
		if (index == 0) {
			ac = new AudioClip("file:resources/sounds/ImGonnaEatYou.wav");
		}
		else if (index == 1) {
			ac = new AudioClip("file:resources/sounds/NOMNOMNOM.wav");
		}
		ac.play();
		Random rand = new Random();
		direction = rand.nextInt(4);
		m = new monster(direction);
		d = new dodger();
		
		m.speed = 1.0 + (.1*difficulty);
		d.speed = 1.0 + (.1*difficulty);
		dodgeBackground.declare();
	}
	
	public void startGame(int difficulty) {
		this.difficulty = difficulty;
		setTheScene();
	}
	
	public void endGame() {
		d.forget();
		m.forget();
		dodgeBackground.forget();
		if (cd != null) {
			cd.forget();
		}
	}
	
	public boolean isGameOver() {
		if (m.charge) {
			if (!d.getSprite().equals(dodger2)) {
				d.setSprite(dodger2);
			}
			if (chargeTimer == -1) {
				chargeTimer = 25;
			} else {
				chargeTimer = chargeTimer -1;
				if (chargeTimer == 0 && d.visible) {
					d.drawSweatDrip();
					isGameWon = true;
					isGameOver = true;
					if (cd == null) {
						cd = new ConditionDisplay(isGameWon);
						cd.declare();
					}
					return true;
				}
			}
			if (d.isColliding(m)) {
				d.hide();
				isGameWon = false;
				isGameOver = true;
				if (cd == null) {
					cd = new ConditionDisplay(isGameWon);
					cd.declare();
				}
				return true;
			}
		}
		return isGameOver;
	}
	
	public boolean wasGameWon() {
		return isGameWon;
	}
}

package engine;

import java.util.Random;

import gameObjects.Game;
import map.Background;

public class onlyCowboy extends GameObject implements Game {
	
	int difficulty = 0;
	CowboySpectator boy1;
	CowboySpectator boy2;
	Clock clock;
	
	Background2 cowboyBack;
	boolean setting = false;
	
	boolean isGameWon;

	int hour = 12;
	int hourtime = 50;
	
	int pattern = 0;
	int pattern1stophour = -1; 
	int shootTimer = -1;
	
	Cowboy realboy1;
	Cowboy realboy2;
	
	Tornado t;
	Grave grave1;
	Grave grave2;
	
	ConditionDisplay cd;
	
	public onlyCowboy() {
	}
	
	public void setTheScene() {
		Random rand = new Random ();
		if (rand.nextBoolean()) {
			setting = true;
			cowboyBack = new Background2(new Sprite("resources/sprites/cowboybackground.png"));
		} else {
			setting = false;
			cowboyBack = new Background2(new Sprite("resources/sprites/thistown.png"));
		}
		
		boy1 = new CowboySpectator (true);
		boy2 = new CowboySpectator (false);
		
		realboy1 = new Cowboy(true);
		realboy2 = new Cowboy(false);
		
		clock = new Clock ();
		
		boy1.declare(300, 250);
		boy2.declare(600, 250);
		boy2.getAnimationHandler().setFlipHorizontal(true);
		
		realboy1.declare(230,390);
		realboy2.declare(610,390);
		
		if (setting) {
			clock.declare(430, 0);
			t = new Tornado();
			
			int missingInt = rand.nextInt(3);
			
			switch (missingInt) {
				case 0:
					if (rand.nextBoolean()) {
						grave1 = new Grave(1);
						grave1.declare(160,250);
						
						grave2 = new Grave(2);
						grave2.declare(650,250);
					} else {
						grave1 = new Grave(2);
						grave1.declare(160,250);
						
						grave2 = new Grave(1);
						grave2.declare(650,250);
					}
					break;
				case 1:
					if (rand.nextBoolean()) {
						grave1 = new Grave(0);
						grave1.declare(160,250);
						
						grave2 = new Grave(2);
						grave2.declare(650,250);
					} else {
						grave1 = new Grave(2);
						grave1.declare(160,250);
						
						grave2 = new Grave(0);
						grave2.declare(650,250);
					}
					break;
				case 2:
					if (rand.nextBoolean()) {
						grave1 = new Grave(0);
						grave1.declare(160,250);
						
						grave2 = new Grave(1);
						grave2.declare(650,250);
					} else {
						grave1 = new Grave(1);
						grave1.declare(160,250);
						
						grave2 = new Grave(0);
						grave2.declare(650,250);
					}
					break;

			}
			
			if (rand.nextBoolean()) {
				t.declare(100,220);
				t.direction = false;
			} else {
				t.declare(700,220);
				t.direction = true;
			}
		} else {
			clock.declare(710, 140);
			clock.setDrawRotation(3.14/3);
		}
		
		cowboyBack.declare();
		
		realboy1.requriesClick = true;
		
		pattern = rand.nextInt(3);//rand.nextInt(4);
		if (pattern == 0) {
			hour = rand.nextInt(3) + 8;
			clock.setClockHour(hour);
			hourtime = rand.nextInt(2) + 10;
		}
		if (pattern == 1) {
			hour = rand.nextInt(3) + 2;
			clock.setClockHour(hour);
			hourtime = rand.nextInt(3) + 10;
			pattern1stophour = rand.nextInt(3) + 6;
		}
		if (pattern == 2) {
			hour = rand.nextInt(3) + 3;
			clock.setClockHour(hour);
			hourtime = rand.nextInt(28) + 2;
		}
	}
	
	public void startGame(int difficulty) {
		this.difficulty = difficulty;
		setTheScene();
	}
	
	public void endGame() {
		boy1.forget();
		boy2.forget();
		clock.forget();
		realboy1.forget();
		realboy2.forget();
		cowboyBack.forget();
		if (t != null) {
			t.forget();
		}
		if (grave1 != null) {
			grave1.forget();
		}
		if (grave2 != null) {
			grave2.forget();
		}
		if (cd != null) {
			cd.forget();
		}
	}
	
	public boolean isGameOver() {
		if (t != null) {
			if (boy1.isColliding(t)) {
				boy1.setX(t.getX());
				boy1.setY(t.getY());
				boy1.setDrawRotation(boy1.drawRotation + .3);
			}
			
			if (boy2.isColliding(t)) {
				boy2.setX(t.getX());
				boy2.setY(t.getY());
				boy2.setDrawRotation(boy2.drawRotation + .3);
			}
		}
		hourtime = hourtime - 1;
		if (hourtime == 0 && shootTimer == -1) {
			hour = hour + 1;
			if (hour == 13) {
				hour = 1;
				shootTimer = 15 - (difficulty);
			}
			clock.setClockHour(hour);
			if (pattern == 0) {
				Random rand = new Random ();
				hourtime = rand.nextInt(3) + 10;
			}
			if (pattern == 1) {
				Random rand = new Random ();
				
				if (hour == pattern1stophour) {
					hourtime = rand.nextInt(30) + 20;
				} else {
					if (hour > pattern1stophour) {
						hourtime = rand.nextInt(2) + 2;
					} else {
						hourtime = rand.nextInt(3) + 10;
					}
				}
			}
			if (pattern == 2) {
				Random rand = new Random ();
				hourtime = rand.nextInt(28) + 2;
			}
		}
		if (shootTimer > 0) {
			shootTimer = shootTimer - 1;
			realboy1.shootTimer = shootTimer;
			realboy2.shootTimer = shootTimer;
			
			realboy2.frameEvent();
			
			if (realboy1.shotOther) {
				realboy2.getShot(true);
				isGameWon = true;
				if (cd == null) {
					cd = new ConditionDisplay(isGameWon);
					cd.declare();
				}
				return true;
			}
			
			if (realboy2.shotOther) {
				realboy1.getShot(false);
				isGameWon = false;
				if (cd == null) {
					cd = new ConditionDisplay(isGameWon);
					cd.declare();
				}
				return true;
			}
			
		}
		
		if (realboy1.shot != 0) {
			isGameWon = false;
			return true;
		}
		
		return false;
	}
	
	public boolean wasGameWon() {
		return isGameWon;
	}
}

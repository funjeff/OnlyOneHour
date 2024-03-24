package engine;

import java.util.ArrayList;
import java.util.Random;

import map.Background;

public class onlyTrolly {
	
	int difficulty = 0;
	boolean isGameWon;
	boolean path=true;
	int nextEplosion = 393;
	
	Background2 trollyBackground = new Background2(new Sprite ("resources/sprites/trolly background.png"));
	Trolly troll = new Trolly ();
	
	Trapped5 trappedGroup = new Trapped5();
	Trapped1 trappedSolo = new Trapped1();
	
	
	public onlyTrolly() {
	}
	
	public void setTheScene() {
		trollyBackground.declare();
		troll.declare(-160, 50);
		trappedGroup.declare(580, 30);
		trappedSolo.declare(490,190);
		troll.setDrawRotation(-.1);
		
		troll.speed = 2.0 + (difficulty * .4);
	}
	
	public void startGame(int difficulty) {
		this.difficulty = difficulty;
		setTheScene();
	}
	
	public void endGame() {
		troll.forget();
		trappedGroup.forget();
		trappedSolo.forget();
		trollyBackground.forget();
		difficulty = 0;
		isGameWon = false;
		path = true;
		nextEplosion = 393;
		
		
		
		ArrayList <GameObject> blood = ObjectHandler.getObjectsByName("BloodDroplet");
		for (int i = 0; i < blood.size(); i = i) {
			blood.get(i).forget();
		}
	}
	
	public boolean isGameOver() {
		
		if (troll.getX() > nextEplosion && troll.path && !trappedSolo.hasExploded()) {
			
			if (nextEplosion == 559) {
				trappedGroup.makeExplode(210,30);
				nextEplosion = 100000000;
				trappedGroup.hide();
				return true;
			}
			if (nextEplosion == 521) {
				trappedGroup.makeExplode(160,30);
				nextEplosion = 559;
			}
			if (nextEplosion == 475) {
				trappedGroup.makeExplode(130,30);
				nextEplosion = 521;
			}
			if (nextEplosion == 443) {
				trappedGroup.makeExplode(100,30);
				nextEplosion = 475;
			}
			if (nextEplosion == 393) {
				trappedGroup.makeExplode(50,30);
				nextEplosion = 443;
			}
			//TODO was game won?

		}
		
		if (troll.getX() > 400 && !troll.path && !trappedSolo.hasExploded()) {
			trappedSolo.makeExplode();
			//TODO was game won?
			return true;
		}
		return false;
	}
	
	public boolean wasGameWon() {
		return isGameWon;
	}
}

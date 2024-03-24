package engine;

import java.util.Random;

import map.Background;

public class onlyTrolly {
	
	int difficulty = 0;
	boolean isGameWon;
	boolean path=true;
	
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
	}
	
	public void startGame(int difficulty) {
		this.difficulty = difficulty;
		setTheScene();
	}
	
	public void endGame() {

	}
	
	public boolean isGameOver() {
		
		return false;
	}
	
	public boolean wasGameWon() {
		return isGameWon;
	}
}

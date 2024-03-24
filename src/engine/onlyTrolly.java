package engine;

import java.util.ArrayList;
import java.util.Random;

import gameObjects.Game;
import map.Background;

public class onlyTrolly extends GameObject implements Game {
	
	static Random rand = new Random();
	
	int difficulty = 0;
	boolean isGameWon;
	boolean path=true;
	int nextEplosion = 393;
	
	Background2 trollyBackground = new Background2(new Sprite ("resources/sprites/trolly background.png"));
	Trolly troll = new Trolly ();
	
	Trapped5 trappedGroup = new Trapped5();
	Trapped1 trappedSolo = new Trapped1();
	
	int correctChoice = 0;
	
	
	public onlyTrolly(int correctChoice) {
		this.correctChoice = 0;
	}
	
	public void setTheScene() {
		AudioClip chugachuga = new AudioClip("file:resources/sounds/chugachuga.wav");
		chugachuga.play();
		int index = rand.nextInt(14);
		AudioClip ac = new AudioClip("file:resources/sounds/iHaveAFamilyOfOne.wav");
		if (index == 0) {
			ac = new AudioClip("file:resources/sounds/ImSureTheGuyOnTheOtherTrackIsAnImposter.wav");
		}
		else if (index == 1) {
			ac = new AudioClip("file:resources/sounds/iveGotAFamilyOf62.wav");
		}
		else if (index == 2) {
			ac = new AudioClip("file:resources/sounds/nobodyWouldCareIfYouKilledHim.wav");
		}
		else if (index == 3) {
			ac = new AudioClip("file:resources/sounds/noDontChooseMe.wav");
		}
		else if (index == 4) {
			ac = new AudioClip("file:resources/sounds/noDontSaveHim.wav");
		}
		else if (index == 5) {
			ac = new AudioClip("file:resources/sounds/noSaveUsInstead.wav");
		}
		else if (index == 6) {
			ac = new AudioClip("file:resources/sounds/saveMe.wav");
		}
		else if (index == 7) {
			ac = new AudioClip("file:resources/sounds/switchTheTrainMotherfucker.wav");
		}
		else if (index == 8) {
			ac = new AudioClip("file:resources/sounds/theCartsAfterMePleaseNo.wav");
		}
		else if (index == 9) {
			ac = new AudioClip("file:resources/sounds/wellIveGotAFamilyOf63.wav");
		}
		else if (index == 10) {
			ac = new AudioClip("file:resources/sounds/whatAreYouWaitingForThatGuysLeftLarry.wav");
		}
		else if (index == 11) {
			ac = new AudioClip("file:resources/sounds/yeahYouCanKillMeIDontHaveAFamily.wav");
		}
		else if (index == 12) {
			ac = new AudioClip("file:resources/sounds/youABitchBroYouAintGonnaHitMe.wav");
		}
		ac.play();
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
				int index = rand.nextInt(7);
				AudioClip ac = new AudioClip("file:resources/sounds/death" + rand.nextInt(5) + ".wav");
				ac.play();
				AudioClip end = new AudioClip("file:resources/sounds/jeffreyFemurCrusher.wav");
				ac.play();
				trappedGroup.makeExplode(210,30);
				nextEplosion = 100000000;
				trappedGroup.hide();
				return true;
			}
			if (nextEplosion == 521) {
				AudioClip ac = new AudioClip("file:resources/sounds/death" + rand.nextInt(5) + ".wav");
				ac.play();
				trappedGroup.makeExplode(160,30);
				nextEplosion = 559;
			}
			if (nextEplosion == 475) {
				AudioClip ac = new AudioClip("file:resources/sounds/death" + rand.nextInt(5) + ".wav");
				ac.play();
				trappedGroup.makeExplode(130,30);
				nextEplosion = 521;
			}
			if (nextEplosion == 443) {
				AudioClip ac = new AudioClip("file:resources/sounds/death" + rand.nextInt(5) + ".wav");
				ac.play();
				trappedGroup.makeExplode(100,30);
				nextEplosion = 475;
			}
			if (nextEplosion == 393) {
				AudioClip ac = new AudioClip("file:resources/sounds/death" + rand.nextInt(5) + ".wav");
				ac.play();
				trappedGroup.makeExplode(50,30);
				nextEplosion = 443;
			}
			//TODO was game won?

		}
		
		if (troll.getX() > 400 && !troll.path && !trappedSolo.hasExploded()) {
			AudioClip ac = new AudioClip("file:resources/sounds/jeffreyFemurCrusher.wav");
			ac.play();
			
			int index = rand.nextInt(4);
			AudioClip end = new AudioClip("file:resources/sounds/youCantMakeAnOmeletteWithoutBreakingAFewEggsRaiden.wav");
			if (index == 0) {
				end = new AudioClip("file:resources/sounds/whoEvenWasThat.wav");
			}
			else if (index == 1) {
				end = new AudioClip("file:resources/sounds/hesDeadHesNotComingBackHesDead.wav");
			}
			else if (index == 2) {
				end = new AudioClip("file:resources/sounds/thatWasACloseTwoWaitAMinuteIMeanOne.wav");
			}
			else if (index == 3) {
				end = new AudioClip("file:resources/sounds/alrightSonne.wav");
			}
			end.play();
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

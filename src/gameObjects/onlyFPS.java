package gameObjects;

import java.util.Random;

import engine.AudioClip;
import engine.GameObject;
import engine.Sprite;

public class onlyFPS extends GameObject implements Game {

	Maro maro;
	GameBackground bg;
	Star star;
	Random rand = new Random();
	
	@Override
	public void startGame (int difficulty) {
		int index = rand.nextInt(11);
		AudioClip ac = new AudioClip("file:resources/sounds/ahYesAnotherPowerStarToMyCollection.wav");
		if (index == 0) {
			ac = new AudioClip("file:resources/sounds/dudeThisGuyIsLiterallyStompingGoombas.wav");
		}
		else if (index == 1) {
			ac = new AudioClip("file:resources/sounds/iAmSoCloseToTheObjective.wav");
		}
		else if (index == 2) {
			ac = new AudioClip("file:resources/sounds/iLikeMario.wav");
		}
		else if (index == 3) {
			ac = new AudioClip("file:resources/sounds/iLikeChiliDogs.wav");
		}
		else if (index == 4) {
			ac = new AudioClip("file:resources/sounds/imGonnaBeatLevelAndGetTheYoshEgg.wav");
		}
		else if (index == 5) {
			ac = new AudioClip("file:resources/sounds/marioWearsOverallsRight.wav");
		}
		else if (index == 6) {
			ac = new AudioClip("file:resources/sounds/stompAllOfTheGoombas.wav");
		}
		else if (index == 7) {
			ac = new AudioClip("file:resources/sounds/superMaro.wav");
		}
		else if (index == 8) {
			ac = new AudioClip("file:resources/sounds/yipperoni.wav");
		}
		else if (index == 9) {
			ac = new AudioClip("file:resources/sounds/yipperoni2.wav");
		}
		ac.play();
		bg = new GameBackground(new Sprite("resources/sprites/maro.png"));
		maro = new Maro (difficulty);
		star = new Star ();
	}

	@Override
	public void endGame () {
		bg.forget();
		maro.forget ();
		star.forget ();
	}

	@Override
	public boolean isGameOver () {
		return maro.dead || maro.won;
	}

	@Override
	public boolean wasGameWon () {
		return maro.won;
	}
	
	

}

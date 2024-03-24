package engine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

import gameObjects.onlyKey;
import gameObjects.Game;
import gameObjects.onlyFPS;
import map.Room;



public class GameCode {
	
	static int veiwX;
	static int veiwY;
	//static Timer t = new Timer (10000);
	//static IntroAnimation in = new IntroAnimation("HOUR");
	//static onlyTimer ot = new onlyTimer ();
//	static KeyGame kg = new KeyGame();
	
	static long lastGameStartTime = 0;
	static int currentGameID = 0;
	static int nextGameID = 0;
	static boolean transitionSpawned = false;
	
	static AudioClip currentMusic;
	static Game currGame;
	
	static boolean gottenOnlyOneMinute = false;
	
	// Only one death is 0, only one left is 1
	static int trolleyChoice = 0;
	
	static String[] gameNames = {"COWBOY", "DODGE", "FPS", "KEY", "DEATH", "PERSON", "11111111111111", "MINUTE"};
	static Color[] gameTransitionColors = {Color.BLACK, Color.WHITE, Color.WHITE, Color.BLACK, Color.BLACK, Color.WHITE, Color.WHITE, Color.WHITE};
	static AudioClip[] musicClips = {
			new AudioClip("file:resources/music/1_cowboy.wav"),
			new AudioClip("file:resources/music/1_dodge.wav"),
			new AudioClip("file:resources/music/1_dollar.wav"),
			new AudioClip("file:resources/music/1_key.wav"),
			new AudioClip("file:resources/music/1_life.wav"),
			new AudioClip("file:resources/music/1_person.wav"),
			new AudioClip("file:resources/music/only_1s.wav"),
			new AudioClip("file:resources/music/1_dollar.wav")
	};
	
	public static void testBitch () {
		
		
	}
	
	public static void beforeGameLogic () {
		
	}

	public static void afterGameLogic () {
		
	}

	public static void init () {
		//in.declare(100, 100);
		//t.declare(100, 100);'
		//op.startGame(0);
		//ot.startGame(4);

		// IntroAnimation("LEFT", (int)(Math.random() * 5)).declare();
		currentMusic = musicClips[0];
		currentMusic.play ();
		startNewGame (2);
		//ot.startGame(0);
	}
		
	public static void startNewGame (int gameId) {
		
		switch (gameId) {
			case 0:
				currGame = new onlyCowboy();
				break;
			case 1:
				currGame = new onlyDodge();
				break;
			case 2:
				currGame = new onlyFPS();
				break;
			case 3:
				currGame = new onlyKey();
				break;
			case 4:
				int correctChoice = trolleyChoice;
				currGame = new onlyTrolly(correctChoice);
				break;
			case 5:
				currGame = new onlyPipe();
				break;
			case 6:
				currGame = new onlyBinary();
				break;
			case 7:
				currGame = new onlyTimer();
				break;
		}
		((GameObject)currGame).declare ();
		currGame.startGame (0);
		
	}
	
	public static void endCurrentGame () {
		
		currGame.endGame ();
		((GameObject)currGame).forget ();
		
	}
	
	public static void gameLoopFunc () {

		ObjectHandler.callAll();
		
		// Wait to sync with the music
		if (lastGameStartTime == 0) {
			if (!currentMusic.isPlaying()) {
				return;
			} else {
				lastGameStartTime = System.currentTimeMillis ();
			}
		}
		
		long elapsedTime = System.currentTimeMillis () - lastGameStartTime;
		if (elapsedTime >= 6261 && !transitionSpawned) {
			do {
				nextGameID = (int)(Math.random() * (gottenOnlyOneMinute ? gameNames.length - 1 : gameNames.length));
			} while (nextGameID == currentGameID);
			if (nextGameID == 7) {
				gottenOnlyOneMinute = true;
			}
			String introAnimationStr = gameNames[nextGameID];
			if (nextGameID == 4) {
				trolleyChoice = (int)(Math.random() * 2);
				if (trolleyChoice == 1) {
					introAnimationStr = "LEFT";
				}
			}
			IntroAnimation introAnimation = new IntroAnimation(introAnimationStr, (int)(Math.random () * 5));
			introAnimation.setWordColor (gameTransitionColors[currentGameID]);
			introAnimation.declare();
			transitionSpawned = true;
		}
		if (elapsedTime >= 8348) {
			currentGameID = nextGameID;
			currentMusic.stop ();
			currentMusic = musicClips[currentGameID];
			currentMusic.play ();
			lastGameStartTime = System.currentTimeMillis ();
			transitionSpawned = false;
			endCurrentGame();
			startNewGame(currentGameID);
		}
		
		currGame.isGameOver ();
//		if (!t.isStarted()) {
//		
//		// Wait to sync with the music
//		if (lastGameStartTime == 0) {
//			if (!currentMusic.isPlaying()) {
//				return;
//			} else {
//				lastGameStartTime = System.currentTimeMillis ();
//			}
//		}
//		
//		long elapsedTime = System.currentTimeMillis () - lastGameStartTime;
//		if (elapsedTime >= 6261 && !transitionSpawned) {
//			do {
//				nextGameID = (int)(Math.random() * gameNames.length);
//			} while (nextGameID == currentGameID);
//			IntroAnimation introAnimation = new IntroAnimation(gameNames[nextGameID], (int)(Math.random () * 4));
//			introAnimation.setWordColor (gameTransitionColors[nextGameID]);
//			introAnimation.declare();
//			transitionSpawned = true;
//		}
//		if (elapsedTime >= 8348) {
//			currentGameID = nextGameID;
//			currentMusic.stop ();
//			currentMusic = musicClips[currentGameID];
//			currentMusic.play ();
//			lastGameStartTime = System.currentTimeMillis ();
//			transitionSpawned = false;
//			kg.endGame ();
//		}
////		if (!t.isStarted()) {
//			t.startTimer();
//		}
//		if (op.isGameOver()) {
//			op.endGame();
//		}
//		oc.isGameOver();
		//ot.isGameOver();
	}

	public static void renderFunc () {
		Graphics g = RenderLoop.wind.getBufferGraphics ();
		g.setColor (Color.DARK_GRAY);
		g.fillRect (0, 0, 960, 540);
		ObjectHandler.renderAll();
	}
	
	public static void beforeRender() {
		
	}
	
	public static void afterRender()
	{
		
	}
		
	
	public static int getViewX() {
		return veiwX;
	}



	public static void setViewX(int newVeiwX) {
		veiwX = newVeiwX;
	}



	public static int getViewY() {
		return veiwY;
	}



	public static void setViewY(int newVeiwY) {
		veiwY = newVeiwY;
	}



	
}

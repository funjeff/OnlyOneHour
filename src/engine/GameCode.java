package engine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
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
	
	
	static onlyCowboy oc = new onlyCowboy ();
	static long lastGameStartTime = 0;
	static int currentGameID = 0;
	static int nextGameID = 0;
	static boolean transitionSpawned = false;
	static int difficulty = 0;
	static int levelUpNum = 1;
	static int didWin = -1;
	static int fadeoutOpacity = 100;
	static boolean fadeoutDir = false; 
	static int wins = 0;
	static int losses = 0;
	
	
	static AudioClip currentMusic;
	static Game currGame;
	
	static boolean gottenOnlyOneMinute = false;
	
	// Only one death is 0, only one left is 1
	static int trolleyChoice = 0;
	
	static String[] gameNames = {"O CLOCK", "DODGE", "FPS", "KEY", "DEATH", "PERSON", "11111111111111", "MINUTE"};
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
		currGame.startGame (difficulty);
		
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
			fadeoutOpacity = 100;
			endCurrentGame();
			didWin = -1;
			fadeoutDir = false; 
			startNewGame(currentGameID);
		}
		
		if (didWin == -1) {
			if (currGame.isGameOver()) {
				if (currGame.wasGameWon()) {
					didWin = 1;
					wins = wins + 1;
					levelUpNum = levelUpNum - 1;
					if (levelUpNum == 0 && difficulty != 9) {
						levelUpNum = 1;
						difficulty = difficulty + 1;
						didWin = 2;
					}
				} else {
					losses = losses + 1;
					if (levelUpNum != 3) {
						levelUpNum = levelUpNum + 1;
					}
					didWin = 0;
				}
			}
		} else {
			currGame.isGameOver();
		}
		if (didWin == 2) {
			if (!fadeoutDir) {
				fadeoutOpacity = fadeoutOpacity + 5;
				if (fadeoutOpacity >= 255) {
					fadeoutOpacity = 255;
					fadeoutDir = true; 
				}
			} else {
				fadeoutOpacity = fadeoutOpacity - 5;
			}
			
		}
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
		//ot.isGameOver();
	}

	public static void renderFunc () {
		Graphics g = RenderLoop.wind.getBufferGraphics ();
		g.setColor (Color.DARK_GRAY);
		g.fillRect (0, 0, 960, 540);
		ObjectHandler.renderAll();
		
		if (didWin == 2 && fadeoutOpacity > 0) {
			drawTextWithTransform("LEVEL UP!", new Color (0,200,0,fadeoutOpacity),new Font ("Comic Sans MS",Font.PLAIN,40),280,100,2,2,0);
		}
		drawTextWithTransform("WINS " + wins, new Color (0,255,0,255),new Font ("Comic Sans MS",Font.PLAIN,40),100,20,1,1,0);
		drawTextWithTransform("LOSSES " + losses, new Color (255,0,0,255),new Font ("Comic Sans MS",Font.PLAIN,40),350,20,1,1,0);
		drawTextWithTransform("DIFFICULTY " + difficulty, new Color (0,0,255,255),new Font ("Comic Sans MS",Font.PLAIN,40),650,20,1,1,0);
		
	}
	
	public static int[] getTextCenterOffset (String text, Font f) {
		Graphics2D metricsGraphics = (Graphics2D)IntroAnimation.dummyImg.getGraphics ();
		metricsGraphics.setFont (f);
		FontMetrics fm = metricsGraphics.getFontMetrics ();
		int textWidth = fm.stringWidth (text) + 4;
		int textHeight = fm.getHeight () + 4;
		int fontAscent = fm.getAscent();
		int tlX = 0;
		int tlY = fontAscent;
		return new int[] {tlX + textWidth / 2, -tlY + textHeight / 2};
	}
	
	public static void drawTextWithTransform (String text, Color c, Font f, double x, double y, double sclX, double sclY, double theta) {
		
		if (sclX != 0 && sclY != 0) {
			int[] centerOffset = getTextCenterOffset (text, f);
			
			AffineTransform tf = new AffineTransform ();
			tf.translate (x, y);
			tf.rotate (theta);
			tf.scale (sclX, sclY);
			tf.translate (-centerOffset[0], -centerOffset[1]);
			Graphics2D g = (Graphics2D)RenderLoop.wind.getBufferGraphics();
			
			g.setColor(c);
			g.setTransform (tf);
			g.setFont (f);
			g.drawString (text, 0, 0);
			g.setTransform (new AffineTransform ());
		}
		
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

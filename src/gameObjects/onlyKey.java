package gameObjects;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import engine.AudioClip;
import engine.ConditionDisplay;

import java.awt.event.KeyEvent;
import engine.GameObject;
import engine.IntroAnimation;
import engine.Sprite;
import engine.Timer;

public class onlyKey extends GameObject implements Game {
	
	static Random rand = new Random();

	GameBackground keyBackground;
	int correctKey;
	String correctKeyStr;
	String possibleKeys = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789[]\\;,./";
	int[] bonusKeys = {KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_CAPS_LOCK, KeyEvent.VK_SHIFT, KeyEvent.VK_CONTROL, KeyEvent.VK_SPACE, KeyEvent.VK_ALT,
			           KeyEvent.VK_INSERT, KeyEvent.VK_DELETE, KeyEvent.VK_ESCAPE, KeyEvent.VK_HOME, KeyEvent.VK_END, KeyEvent.VK_PAGE_UP, KeyEvent.VK_PAGE_DOWN};
	String[] bonusNames = {"UP ARROW", "DOWN ARROW", "LEFT ARROW", "RIGHT ARROW", "CAPS LOCK", "SHIFT", "CTRL", "SPACE", "ALT",
						   "INSERT", "DELETE", "ESC", "HOME", "END", "PAGE UP", "PAGE DOWN"};
	
	int difficulty = 0;
	int[] allKeys;
	String[] allNames;
	
	long gameTime;
	Timer gameTimer;
	
	boolean won;
	
	BigText currText = null;
	BigText failText = null;
	boolean guessedCorrect = false;
	
	ArrayList<BigText> displayTexts;
	
	ConditionDisplay cd;
	
	public onlyKey () {
		allKeys = new int[possibleKeys.length() + bonusKeys.length];
		allNames = new String[possibleKeys.length() + bonusKeys.length];
		for (int i = 0; i < possibleKeys.length(); i++) {
			allKeys[i] = (int)possibleKeys.charAt (i);
			allNames[i] = possibleKeys.charAt (i) + "";
		}
		for (int i = 0; i < bonusKeys.length; i++) {
			allKeys[possibleKeys.length() + i] = bonusKeys[i];
			allNames[possibleKeys.length() + i] = bonusNames[i];
		}
	}
	
	void generateKey () {
		int sampleCount = (int)(((double)allKeys.length / 10) * (difficulty + 1));
		int randomKeyIndex = (int)(Math.random() * sampleCount);
		correctKey = allKeys[randomKeyIndex];
		correctKeyStr = allNames[randomKeyIndex];
	}
	
	@Override
	public void startGame (int difficulty) {
		this.difficulty = difficulty;
		keyBackground = new GameBackground(new Sprite("resources/sprites/Keyboard.png"));
		generateKey();
		gameTimer = new Timer(5500, Color.BLACK);
		gameTimer.declare (460, 40);
		gameTimer.startTimer ();
		gameTime = difficulty * 1000;
		displayTexts = new ArrayList<BigText> ();
	}

	@Override
	public void endGame () {
		for (int i = 0; i < displayTexts.size (); i++) {
			displayTexts.get (i).forget ();
		}
		if (currText != null) {
			currText.forget ();
		}
		keyBackground.forget ();
		
		if (cd != null) {
			cd.forget();
		}
	}

	@Override
	public boolean isGameOver () {
		if (cd == null && won) {
			cd = new ConditionDisplay(true);
			cd.declare();
		}
		return won || gameTimer.hasExpired();
	}

	@Override
	public boolean wasGameWon () {
		return won;
	}
	
	@Override
	public void frameEvent () {
		if (!won && gameTimer.hasExpired() && failText == null) {
			failText = new BigText("The key was:", Color.RED, 80);
			failText.declare(250, 220);
			displayTexts.add (failText);
			failText = new BigText(correctKeyStr, Color.RED, 80);
			failText.declare(250, 300);
			displayTexts.add (failText);
		}
		if (!guessedCorrect) {
			for (int i = 0; i < allKeys.length; i++) {
				int currKey = allKeys[i];
				if (this.keyPressed (currKey)) {
					if (currKey == correctKey) {
						if (!gameTimer.hasExpired()) {
							won = true;
							BigText newText = new BigText("The key was:", Color.MAGENTA, 80);
							newText.declare(250, 220);
							displayTexts.add (newText);
							currText = new BigText(correctKeyStr, Color.MAGENTA, 80);
							currText.declare(250, 300);
							displayTexts.add (currText);
							guessedCorrect = true;
						}
					} else {
						if (!guessedCorrect) {
							int pos = rand.nextInt(19);
							AudioClip ac = new AudioClip("file:resources/sounds/no" + pos + ".wav");
							ac.play();
						}
						currText = new BigText("Nope", Color.BLACK, 80);
						currText.declare(Math.random() * 800, Math.random() * 500);
						displayTexts.add (currText);
					}
				}
			}
		}
		
		
	}
	
	@Override
	public void draw () {
		
	}

}

package gameObjects;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;


import engine.GameObject;
import engine.IntroAnimation;
import engine.Sprite;
import engine.Timer;

public class KeyGame extends GameObject implements Game {

	GameBackground keyBackground;
	int correctKey;
	String correctKeyStr;
	String possibleKeys = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789[]\\;,./";
	int[] bonusKeys = {KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_CAPS_LOCK, KeyEvent.VK_SHIFT, KeyEvent.VK_CONTROL, KeyEvent.VK_SPACE, KeyEvent.VK_ALT,
			           KeyEvent.VK_INSERT, KeyEvent.VK_DELETE, KeyEvent.VK_ESCAPE, KeyEvent.VK_HOME, KeyEvent.VK_END, KeyEvent.VK_PAGE_UP, KeyEvent.VK_PAGE_DOWN};
	String[] bonusNames = {"UP ARROW", "DOWN ARROW", "LEFT ARROW", "RIGHT ARROW", "CAPS LOCK", "SHIFT", "CTRL", "SPACE", "ALT",
						   "INSERT", "DELETE", "ESC", "HOME", "END", "PAGE UP", "PAGE DOWN"};
	
	int[] allKeys;
	String[] allNames;
	
	long gameTime;
	Timer gameTimer;
	
	BigText currText = null;
	boolean guessedCorrect = false;
	
	ArrayList<BigText> displayTexts;
	
	public KeyGame () {
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
		int randomKeyIndex = (int)(Math.random() * allKeys.length);
		correctKey = allKeys[randomKeyIndex];
		correctKeyStr = allNames[randomKeyIndex];
		System.out.println(correctKeyStr);
	}
	
	@Override
	public void startGame (int difficulty) {
		keyBackground = new GameBackground(new Sprite("resources/sprites/Keyboard.png"));
		generateKey();
		gameTimer = new Timer(difficulty * 1000);
		gameTimer.declare (460, 40);
		gameTimer.startTimer ();
		gameTime = difficulty * 1000;
		IntroAnimation anim = new IntroAnimation("LEFT", IntroAnimation.EFFECT_ID_WORDS_STAR_WARS);
		anim.declare (300, 300);
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
	}

	@Override
	public boolean isGameOver () {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean wasGameWon () {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void frameEvent () {
		if (!guessedCorrect) {
			for (int i = 0; i < allKeys.length; i++) {
				int currKey = allKeys[i];
				if (this.keyPressed (currKey)) {
					if (currKey == correctKey) {
						BigText newText = new BigText("The key was:", Color.MAGENTA, 80);
						newText.declare(250, 220);
						displayTexts.add (newText);
						currText = new BigText(correctKeyStr, Color.MAGENTA, 80);
						currText.declare(250, 300);
						displayTexts.add (currText);
						guessedCorrect = true;
					} else {
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

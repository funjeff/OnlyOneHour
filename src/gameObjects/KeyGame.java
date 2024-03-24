package gameObjects;

import java.awt.Color;

import com.sun.glass.events.KeyEvent;

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
	BigText timerText = null;
	boolean guessedCorrect = false;
	
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
		keyBackground = new GameBackground(new Sprite("resources/sprites/keygamebg.png"));
		generateKey();
		gameTimer = new Timer(difficulty * 1000);
		gameTimer.declare (200, 200);
		gameTimer.startTimer ();
		gameTime = difficulty * 1000;
		IntroAnimation anim = new IntroAnimation("LEFT", IntroAnimation.EFFECT_ID_WORDS_STAR_WARS);
		anim.declare (300, 300);
		
	}

	@Override
	public void endGame () {
		// TODO Auto-generated method stub
		
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
						new BigText("The key was:", Color.MAGENTA, 80).declare(250, 220);
						currText = new BigText(correctKeyStr, Color.MAGENTA, 80);
						currText.declare(250, 300);
						guessedCorrect = true;
					} else {
						currText = new BigText("Nope", Color.WHITE, 80);
						currText.declare(Math.random() * 800, Math.random() * 500);
					}
				}
			}
		}
		
		
	}
	
	@Override
	public void draw () {
		
	}

}

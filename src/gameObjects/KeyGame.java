package gameObjects;

import com.sun.glass.events.KeyEvent;

import engine.GameObject;
import engine.Sprite;

public class KeyGame extends GameObject implements Game {

	GameBackground keyBackground;
	int correctKey;
	String correctKeyStr;
	String possibleKeys = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789[]\\;',./`";
	int[] bonusKeys = {KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_CAPS_LOCK, KeyEvent.VK_SHIFT, KeyEvent.VK_CONTROL, KeyEvent.VK_SPACE, KeyEvent.VK_TAB, KeyEvent.VK_ALT,
			           KeyEvent.VK_F1, KeyEvent.VK_F2, KeyEvent.VK_F3, KeyEvent.VK_F4, KeyEvent.VK_F5, KeyEvent.VK_F6, KeyEvent.VK_F7, KeyEvent.VK_F8, KeyEvent.VK_F9, KeyEvent.VK_F10, KeyEvent.VK_F11, KeyEvent.VK_F12,
			           KeyEvent.VK_INSERT, KeyEvent.VK_PRINTSCREEN, KeyEvent.VK_DELETE, KeyEvent.VK_ESCAPE, KeyEvent.VK_HOME, KeyEvent.VK_END, KeyEvent.VK_PAGE_UP, KeyEvent.VK_PAGE_DOWN};
	String[] bonusNames = {"UP ARROW", "DOWN ARROW", "LEFT ARROW", "RIGHT ARROW", "CAPS LOCK", "SHIFT", "CTRL", "SPACE", "TAB", "ALT",
						   "F1", "F2", "F3", "F4", "F5", "F6", "F7", "F8", "F9", "F10", "F11", "F12",
						   "INSERT", "PRINT SCR", "DELETE", "ESC", "HOME", "END", "PAGE UP", "PAGE DOWN"};
	
	int[] allKeys;
	String[] allNames;
	
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
	}
	
	@Override
	public void startGame (int difficulty) {
		keyBackground = new GameBackground(new Sprite("resources/sprites/keygamebg.png"));
		generateKey();
		System.out.println(correctKeyStr);
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
		
	}
	
	@Override
	public void draw () {
		
	}

}

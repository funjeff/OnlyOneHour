package gameObjects;

public interface Game {

	public void startGame(int difficulty);
	public void endGame();
	public boolean isGameOver();
	public boolean wasGameWon();
	
}

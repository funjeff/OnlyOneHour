package engine;

public class Pipe extends GameObject{
	
	boolean clear = true;	
	boolean lost = false;
	int difficulty;
	public JumpGuy curGuy;
	
	public Pipe (int difficulty) {
		this.setSprite(new Sprite ("resources/sprites/pipe.png"));
		this.setRenderPriority(10);
		this.difficulty = difficulty; 
	}
	
	
	
	public boolean isClear () {
		return clear;
	}
	
	public void spawnGuy () {
		clear = false;
		JumpGuy guy = new JumpGuy ();
		guy.declare(this.getX() + 40,this.getY());
		guy.setSpeed(.3 + (difficulty*.1));
		guy.spawner = this;
		curGuy = guy;
	}
	
	public void spawnLoner () {
		clear = false;
		JumpGuy guy = new JumpGuy ();
		guy.declare(this.getX() + 40,this.getY());
		guy.makeLoner();
		guy.spawner = this;
		guy.setSpeed(.3 + (difficulty*.1));
		curGuy = guy;
	}
	
	public boolean hasLost () {
		return lost;
	}
	
}

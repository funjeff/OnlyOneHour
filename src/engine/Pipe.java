package engine;

import java.util.Random;

public class Pipe extends GameObject{
	
	boolean clear = true;	
	boolean lost = false;
	boolean won = false;
	int difficulty;
	public JumpGuy curGuy;
	
	public Pipe (int difficulty) {
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
		guy.setSpeed(.5 + (difficulty*.1));
		guy.spawner = this;
		curGuy = guy;
	}
	
	public void spawnLoner () {
		clear = false;
		JumpGuy guy = new JumpGuy ();
		guy.declare(this.getX() + 40,this.getY());
		guy.makeLoner();
		guy.spawner = this;
		guy.setSpeed(.7 + (difficulty*.1));
		curGuy = guy;
	}
	
	public boolean hasLost () {
		return lost;
	}
	
	public boolean hasWon () {
		return won;
	}
	
}

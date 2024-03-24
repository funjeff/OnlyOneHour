package engine;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Timer extends GameObject {

	long timeStartMillis = 0;
	int length = 0;
	boolean expired = false;
	Color c = Color.WHITE;

	public Timer (int lenth) {
		
		length = lenth;
		this.setRenderPriority(15);
	}
	
	public Timer (int length, Color c) {
		this(length);
		this.c = c;
	}
	
	@Override
	public void frameEvent () {
		if (timeStartMillis != 0 && System.currentTimeMillis() > timeStartMillis + length) {
			expired = true;
			timeStartMillis = 0;
		}
	}
	
	@Override
	public void draw () {
		if (timeStartMillis != 0) {
			Graphics g = RenderLoop.wind.getBufferGraphics();
			g.setFont(new Font ("Comic Sans MS",Font.PLAIN,40));
			
			int timeSpent = (int) (System.currentTimeMillis() - timeStartMillis);
			int timeLeft = length - timeSpent;
			int timeLeftSecs = (int) Math.floor(timeLeft/1000.0);
			int timeLeftMills = timeLeft%1000;
			
			g.setColor(c);
			g.drawString(timeLeftSecs + ":" + timeLeftMills, (int)this.getX(), (int)this.getY());
		}
	}
	
	public boolean isStarted () {
		return timeStartMillis != 0;
	}
	
	public boolean hasExpired () {
		return expired;
	}
	
	public void setLength(int timeMills) {
		length = timeMills;
	}
	
	public void startTimer() {
		expired = false;
		timeStartMillis = System.currentTimeMillis();
	}
	
}

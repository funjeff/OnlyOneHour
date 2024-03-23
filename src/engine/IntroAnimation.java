package engine;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class IntroAnimation extends GameObject {
	String firstWordText = "ONLY";
	String secondWordText = "1";
	String thirdWordText = "HOUR";
	
	double word1Opacity = 0;
	double word2Opacity = 0;
	double word3Opacity = 0;
	
	boolean donePlaying = false;
	
	public IntroAnimation(String thirdWord) {
		thirdWordText = thirdWord;
	}
	
	@Override
	public void frameEvent() {
		if (word1Opacity == 1) {
			if (word2Opacity == 1) {
				if (word3Opacity == 1) {
					donePlaying = true;
				} else {
					word3Opacity = word3Opacity + 0.05;
					if (word3Opacity > 1) {
						word3Opacity = 1;
					}
				}
			} else {
				word2Opacity = word2Opacity + 0.04;
				if (word2Opacity > 1) {
					word2Opacity = 1;
				}
			}
		} else {
			word1Opacity = word1Opacity + 0.03;
			if (word1Opacity > 1) {
				word1Opacity = 1;
			}
		}
	}
	
	@Override
	public void draw () {
		Graphics g = RenderLoop.wind.getBufferGraphics();
		g.setColor(new Color(255,255,255,(int)(255*word1Opacity)));
		g.setFont(new Font ("Comic Sans MS",Font.PLAIN,40));
		g.drawString(firstWordText, (int)this.getX(), (int)this.getY());
		
		if (word1Opacity == 1) {
			g.setColor(new Color(255,255,255,(int)(255*word2Opacity)));
			g.drawString(secondWordText, (int)this.getX() + 125, (int)this.getY());
			if (word2Opacity == 1) {
				g.setColor(new Color(255,255,255,(int)(255*word3Opacity)));
				g.drawString(thirdWordText, (int)this.getX() + 160, (int)this.getY());
			}
		}
	}
	
	public void restartAnimation () {
		word1Opacity = 0;
		word2Opacity = 0;
		word3Opacity = 0;
		donePlaying = false;
	}
	public void setWord (String thirdWord) {
		thirdWordText = thirdWord;
	}
	
	public boolean isDone () {
		return donePlaying;
	}
}

package gameObjects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import engine.GameObject;
import engine.RenderLoop;

public class BigText extends GameObject {
	
	String text;
	Color color;
	int size;
	
	public BigText (String text, Color color, int size) {
		this.text = text;
		this.color = color;
		this.size = size;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	@Override
	public void draw () {
		Graphics2D g = (Graphics2D)RenderLoop.wind.getBufferGraphics();
		g.setColor(color);
		g.setFont (new Font("Arial", Font.ITALIC, size));
		g.drawString (text, (float)getX(), (float)getY());
	}

}

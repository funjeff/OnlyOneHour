package engine;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Random;

import gameObjects.GameBackground;

public class ConditionDisplay extends GameObject {
	
	static boolean success;
	static Random rand = new Random();
	
	public String[] successSounds = {"fantastic", "great", "perfecg", "remarkable", "spectacular", "spectacular2", "super", "wonderful"};
	public String[] failureSounds = {"awful", "failure", "loss", "loss2", "terrible", "whatABlunder", "boo"};
	public String[] successImages = {"wonderful", "youdidit", "goodjob", "goodjob2"};
	public String[] failureImages = {"failure", "tryAgain"};
	public String[] successReactions = {"goldfish", "chipmcdale", "theRock", "sampog"};
	public String[] failureReactions = {"yiiking", "stickGuyBad", "pikman", "emojidying"};

	public ConditionImage condImage;
	public ReactImage reactImage;
	public double scaleX = 1;
	public double scaleY = 1;
	public ConditionDisplay(boolean succOrFuck) {
		
		this.success = succOrFuck;
		if (success) {		
			condImage = new ConditionImage(successImages[rand.nextInt(successImages.length)]);
			condImage.setX(300);
			condImage.setY(100);
			reactImage = new ReactImage(successReactions[rand.nextInt(successReactions.length)]);
			reactImage.setX(300);
			reactImage.setY(200);
			AudioClip ac = new AudioClip("file:resources/sounds/" + successSounds[rand.nextInt(successSounds.length)] + ".wav");
			ac.play();
		}
		else { //failure
			condImage = new ConditionImage(failureImages[rand.nextInt(failureImages.length)]);
			condImage.setX(300);
			condImage.setY(100);
			reactImage = new ReactImage(failureReactions[rand.nextInt(failureReactions.length)]);
			reactImage.setX(300);
			reactImage.setY(200);
			AudioClip ac = new AudioClip("file:resources/sounds/" + failureSounds[rand.nextInt(failureSounds.length)] + ".wav");
			ac.play();
		}
	}
	
	@Override
	public void frameEvent() {
		if (scaleX <= 2 && scaleY <= 2) {
			scaleX += .01;
			scaleY += .01;
		}
	}
	
	public void drawWithTransform (BufferedImage img, double x, double y, double sclX, double sclY, double theta) {
		
		if (sclX != 0 && sclY != 0) {
			AffineTransform tf = new AffineTransform ();
			tf.translate (x, y);
			tf.translate (img.getWidth () / 2, img.getHeight () / 2);
			tf.rotate (theta);
			tf.scale (sclX, sclY);
			tf.translate (-img.getWidth () / 2, -img.getHeight () / 2);
			Graphics2D g = (Graphics2D)RenderLoop.wind.getBufferGraphics();
			AffineTransform tf2 = AffineTransform.getTranslateInstance (x, y);
			
			g.setTransform (tf);
			g.drawImage (img, 0, 0, null);
			g.setTransform (new AffineTransform ());
		}
		
	}
	
	@Override
	public void draw() {
		super.draw();
		if (reactImage != null) {
			drawWithTransform(reactImage.getSprite().getFrame(0), reactImage.getX(), reactImage.getY(), scaleX, scaleY, 0);
		}
		if (condImage != null) {
			drawWithTransform(condImage.getSprite().getFrame(0), condImage.getX(), condImage.getY(), scaleX, scaleY, 0);
		}
	}
}

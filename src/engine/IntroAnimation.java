package engine;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class IntroAnimation extends GameObject {
	
	public static final int EFFECT_ID_WORDS_FADE = 0;
	public static final int EFFECT_ID_WORDS_LEFT_TO_RIGHT = 1;
	public static final int EFFECT_ID_WORDS_TOP_TO_BOTTOM = 2;
	public static final int EFFECT_ID_WORDS_STAR_WARS = 3;
	public static final int EFFECT_ID_WORDS_CROSS = 4;
	
	public static final int[] MS_OFFSETS = {-500, 0, 0, 0, -250};
	
	private static final BufferedImage dummyImg = new BufferedImage(32, 32, BufferedImage.TYPE_4BYTE_ABGR);
	
	int effectId;
	
	Color wordColor = Color.WHITE;
	
	public String firstWordText = "ONLY";
	public String secondWordText = "ONE";
	public String thirdWordText = "HOUR";
	
	BufferedImage word1;
	BufferedImage word2;
	BufferedImage word3;
	
	long startTime;
	
	double word1Progress = 0;
	double word2Progress = 0;
	double word3Progress = 0;
	double currentFade = 0;
	
	boolean donePlaying = false;
	
	int[] opacityIv1 = {0, 522};
	int[] opacityIv2 = {522, 1000};
	int[] opacityIv3 = {1043, 1565};
	int[] fadeoutTime = {1565, 1878};
	int[] fadeinTime = {2191, 2220};
	
	int funny;
	
	public IntroAnimation(String thirdWord, int effectType) {
		thirdWordText = thirdWord;
		this.effectId = effectType;
		startTime = System.currentTimeMillis ();
		
		Font f = new Font("Arial", Font.ITALIC, 80);
		word1 = genText(firstWordText, f);
		word2 = genText(secondWordText, f);
		word3 = genText(thirdWordText, f);
		
		funny = (int)(Math.random () * 100);
		
		setRenderPriority(20);
	}
	
	public double platFunc (double val) {
		double x = val * 2 - 1;
		return clamp (Math.pow (x, 5) / 2 + 0.5);
	}
	
	public double rotFunc (double val) {
		double x = 4 * (val - 0.5);
		return Math.sin (Math.PI * x) / (Math.pow (x, 4) + 1);
	}
	
	public double sclFunc (double val) {
		x = val - 0.5;
		return 1 + Math.pow (Math.E, -Math.pow (4 * x, 2));
	}
	
	public double clamp (double val) {
		if (val < 0) {
			return 0;
		} else if (val > 1) {
			return 1;
		}
		return val;
	}
	
	public double ivToDouble (int elapsed, int[] iv) {
		return clamp((double)(elapsed - iv[0]) / (iv[1] - iv[0]));
	}
	
	public int[] getTextCenterOffset (String text, Font f) {
		Graphics2D metricsGraphics = (Graphics2D)dummyImg.getGraphics ();
		metricsGraphics.setFont (f);
		FontMetrics fm = metricsGraphics.getFontMetrics ();
		int textWidth = fm.stringWidth (text) + 4;
		int textHeight = fm.getHeight () + 4;
		int fontAscent = fm.getAscent();
		int tlX = 0;
		int tlY = fontAscent;
		return new int[] {tlX + textWidth / 2, -tlY + textHeight / 2};
	}
	
	public BufferedImage genText (String text, Font f) {
		Graphics2D metricsGraphics = (Graphics2D)dummyImg.getGraphics ();
		metricsGraphics.setFont (f);
		FontMetrics fm = metricsGraphics.getFontMetrics ();
		int imgWidth = fm.stringWidth (text) + 4;
		int imgHeight = fm.getHeight () + 4;
		int fontAscent = fm.getAscent();
		BufferedImage img = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g = (Graphics2D)img.getGraphics ();
		g.setColor(wordColor);
		g.setFont (f);
		g.drawString (text, 0, fontAscent);
		return img;
	}
	
	public void drawTextWithTransform (String text, Font f, double x, double y, double sclX, double sclY, double theta) {
		
		if (sclX != 0 && sclY != 0) {
			int[] centerOffset = getTextCenterOffset (text, f);
			
			AffineTransform tf = new AffineTransform ();
			tf.translate (x, y);
			tf.rotate (theta);
			tf.scale (sclX, sclY);
			tf.translate (-centerOffset[0], -centerOffset[1]);
			Graphics2D g = (Graphics2D)RenderLoop.wind.getBufferGraphics();
			
			g.setColor(wordColor);
			g.setTransform (tf);
			g.setFont (f);
			g.drawString (text, 0, 0);
			g.setTransform (new AffineTransform ());
		}
		
	}
	
	public void drawTextWithTransform (String text, Font f, AffineTransform tf) {
		
		Graphics2D g = (Graphics2D)RenderLoop.wind.getBufferGraphics();

		g.setColor(wordColor);
		g.setTransform (tf);
		g.setFont (f);
		g.drawString (text, 0, 0);
		g.setTransform (new AffineTransform ());
		
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
			
			g.setColor(wordColor);
			g.setTransform (tf);
			g.drawImage (img, 0, 0, null);
			g.setTransform (new AffineTransform ());
		}
		
	}
	
	@Override
	public void frameEvent() {
		int timeElapsed = (int)(System.currentTimeMillis () - startTime) / 1;
		int timeElapsedModified = timeElapsed + MS_OFFSETS[effectId];
		if (timeElapsed > fadeoutTime[0]) {
			if (timeElapsed < fadeinTime[0]) {
				currentFade = ivToDouble(timeElapsed, fadeoutTime);
			} else {
				currentFade = 1.0 - ivToDouble(timeElapsed, fadeinTime);
			}
		}
		if (timeElapsed < fadeinTime[0]) {
			word1Progress = ivToDouble(timeElapsedModified, opacityIv1);
			word2Progress = ivToDouble(timeElapsedModified, opacityIv2);
			word3Progress = ivToDouble(timeElapsedModified, opacityIv3);
		} else {
			word1Progress = 0;
			word2Progress = 0;
			word3Progress = 0;
		}
		if (timeElapsed > fadeinTime[1]) {
			donePlaying = true;
		}
	}
	
	@Override
	public void draw () {
		
		Graphics2D g = (Graphics2D)RenderLoop.wind.getBufferGraphics();
		
		if (effectId == EFFECT_ID_WORDS_FADE) {
			g.setColor(new Color(wordColor.getRed (), wordColor.getGreen (), wordColor.getBlue (), (int)(255*word1Progress)));
			g.setFont(new Font ("Comic Sans MS",Font.PLAIN,40));
			g.drawString(firstWordText, 300, 275);
			
			if (word1Progress == 1) {
				g.setColor(new Color(wordColor.getRed (), wordColor.getGreen (), wordColor.getBlue (), (int)(255*word2Progress)));
				g.drawString(secondWordText, 300 + 125, 275);
				if (word2Progress == 1) {
					g.setColor(new Color(wordColor.getRed (), wordColor.getGreen (), wordColor.getBlue (), (int)(255*clamp(word3Progress * 4))));
					g.drawString(thirdWordText, 300 + 233, 275);
				}
			}
		}
		
		if (effectId == EFFECT_ID_WORDS_LEFT_TO_RIGHT) {
			Font f = new Font ("Comic Sans MS",Font.PLAIN,60);
			drawTextWithTransform (firstWordText, f, -200 + 1360 * platFunc(word1Progress), 270, 1, 1, rotFunc(word1Progress) * 0.1);
			drawTextWithTransform (secondWordText, f, -200 + 1360 * platFunc(word2Progress), 270, 1, 1, rotFunc(word2Progress) * 0.1);
			drawTextWithTransform (thirdWordText, f, -200 + 1360 * platFunc(word3Progress), 270, 1, 1, rotFunc(word3Progress) * 0.1);
		}
		
		if (effectId == EFFECT_ID_WORDS_TOP_TO_BOTTOM) {
			Font f = new Font ("Comic Sans MS",Font.PLAIN,60);
			drawTextWithTransform (firstWordText, f, 480, -100 + 740 * platFunc(word1Progress), sclFunc(word1Progress), sclFunc(word1Progress), 0);
			drawTextWithTransform (secondWordText, f, 480, -100 + 740 * platFunc(word2Progress), sclFunc(word2Progress), sclFunc(word2Progress), 0);
			drawTextWithTransform (thirdWordText, f, 480, -100 + 740 * platFunc(word3Progress), sclFunc(word3Progress), sclFunc(word3Progress), 0);
		}
		
//		if (effectId == EFFECT_ID_WORDS_SPIRAL) {
//			Font f = new Font ("Comic Sans MS",Font.PLAIN,60);
//			String[] texts = new String[] {firstWordText, secondWordText, thirdWordText};
//			double[] progresses = new double[] {word1Progress, word2Progress, word3Progress};
//			for (int i = 0; i < texts.length; i++) {
//				String text = texts[i];
//				double prog = progresses[i];
//				if (prog < 1) {
//					int[] centerOffset = getTextCenterOffset (text, f);
//					AffineTransform tf = new AffineTransform ();
//					tf.translate (480, 270);
//					tf.rotate (prog * 2 * Math.PI);
//					tf.translate (0, -270 * (1 - prog) * 3);
//					double x = clamp(Math.sqrt (1 - prog * prog));
//					tf.scale (x, x);
//					tf.translate (-centerOffset[0], -centerOffset[1]);
//					drawTextWithTransform (text, f, tf);
//				}
//			}
//		}
		
		if (effectId == EFFECT_ID_WORDS_STAR_WARS) {
			Font f = new Font ("Comic Sans MS",Font.PLAIN,60);
			drawTextWithTransform (firstWordText, f, 480, 740 - word1Progress * 540, 6 * (1 - word1Progress), 2 * (1 - word1Progress), 0);
			drawTextWithTransform (secondWordText, f, 480, 740 - word2Progress * 540, 6 * (1 - word2Progress), 2 * (1 - word2Progress), 0);
			drawTextWithTransform (thirdWordText, f, 480, 740 - word3Progress * 540, 6 * (1 - word3Progress), 2 * (1 - word3Progress), 0);
		}
		
		if (effectId == EFFECT_ID_WORDS_CROSS) {
			Font f = new Font ("Comic Sans MS",Font.PLAIN,60);
			Color prevColor = wordColor;
			String[] texts = new String[] {firstWordText, secondWordText, thirdWordText};
			double[] progresses = new double[] {word1Progress, word2Progress, word3Progress};
			for (int i = 0; i < texts.length; i++) {
				String text = texts[i];
				double prog = progresses[i];
				wordColor = new Color(wordColor.getRed (), wordColor.getGreen (), wordColor.getBlue (), (int)((0.5 - Math.abs(platFunc(prog) - 0.5)) * 510));
				drawTextWithTransform (text, f, -200 + 1360 * platFunc(prog), -150 + 740 * platFunc(prog), 1, 1, 0);
				drawTextWithTransform (text, f, 1160 - 1360 * platFunc(prog), 690 - 740 * platFunc(prog), 1, 1, 0);
			}
			wordColor = prevColor;
		}
		
		if (currentFade != 0) {
			AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float)currentFade);
			g.setComposite (ac);
			g.setColor (new Color(0, 0, 0));
			if (funny >= 95) {
				g.drawImage (new Sprite("resources/sprites/whopper ad.png").getFrame (0), 0, 0, null);
			} else if (funny >= 80 && thirdWordText.equals ("PERSON")) {
				g.drawImage (new Sprite("resources/sprites/Turtle.png").getFrame (0), 0, 0, null);
			} else {
				g.fillRect (0, 0, 960, 540);
			}
		}
		
		int timeElapsed = (int)(System.currentTimeMillis () - startTime) / 1;

	}
	
	public void setWordColor (Color c) {
		this.wordColor = c;
	}
	
	public void restartAnimation () {
		word1Progress = 0;
		word2Progress = 0;
		word3Progress = 0;
		donePlaying = false;
	}
	public void setWord (String thirdWord) {
		thirdWordText = thirdWord;
	}
	
	public boolean isDone () {
		return donePlaying;
	}
}

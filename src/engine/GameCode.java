package engine;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

import gameObjects.KeyGame;
import map.Room;



public class GameCode {
	
	static int veiwX;
	static int veiwY;
	//static Timer t = new Timer (10000);
	//static IntroAnimation in = new IntroAnimation("HOUR");
	//static onlyTimer ot = new onlyTimer ();
	static onlyBinary ob = new onlyBinary();
	static onlyCowboy oc = new onlyCowboy();	
	
	
	public static void testBitch () {
		
		
	}
	
	public static void beforeGameLogic () {
		
	}

	public static void afterGameLogic () {
		
	}

	public static void init () {
		//in.declare(100, 100);
		//t.declare(100, 100);'
		//op.startGame(0);
		oc.startGame(4);
	}
		
	
	
	public static void gameLoopFunc () {
//		op.isGameOver();
		ObjectHandler.callAll();
//		if (!t.isStarted()) {
//			t.startTimer();
//		}
//		if (op.isGameOver()) {
//			op.endGame();
//		}
		
	}
	
	public static void renderFunc () {
		ObjectHandler.renderAll();
	}
	
	public static void beforeRender() {
		
	}
	
	public static void afterRender()
	{
		
	}
		
	
	public static int getViewX() {
		return veiwX;
	}



	public static void setViewX(int newVeiwX) {
		veiwX = newVeiwX;
	}



	public static int getViewY() {
		return veiwY;
	}



	public static void setViewY(int newVeiwY) {
		veiwY = newVeiwY;
	}



	
}

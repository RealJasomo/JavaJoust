package main;

import main.Joust;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.Timer;

/**
 * The main class for your arcade game.
 * 
 * You can design your game any way you like, but make the game start by running
 * main here.
 * 
 * Also don't forget to write javadocs for your classes and functions!
 * 
 * @author Jason Cramer, Hao Jiang
 *
 */
public class Joust {

	public enum GameState {
		SPLASH, HIGH_SCORE, TITLE_SCREEN, DEMO_VIDEO, WAVE_START, WAVE_END;
	}

	private Joust instance;
	private JFrame gui;
	private long framecount;
	private GameComponent game;
	private long lastTime;
	private Timer gameTimer;
	public Joust() {
		instance = this;
		framecount = 0;
		lastTime = System.currentTimeMillis();
		gui = new JFrame("Joust");
		game = new GameComponent(this);
		gui.setPreferredSize(new Dimension(32*32, 18*32));
		gui.setResizable(false);
		gui.pack();
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setVisible(true);
		gui.addKeyListener(new PlayerInputListener(game,this));
		gui.add(game);
		gui.getContentPane().setBackground(Color.black);
		GameActionListener gameLoop = new GameActionListener(instance, game);
		gameTimer = new Timer(12, gameLoop);
		gameTimer.start();
		
	}

	public static void main(String[] args) {
		new SplashScreen();
	}


	public void calcFPS() {
		// TODO Auto-generated method stub
		framecount++;
		if(System.currentTimeMillis() - lastTime >= 1000) {
			lastTime = System.currentTimeMillis();
			System.out.println("fps:" +  framecount);
			framecount = 0;
		}		
	}

	public void stop() {
		gameTimer.stop();
	}

	public void restart() {
		// TODO Auto-generated method stub
		game.getAudioManger().stop();
		new SplashScreen();
		stop();
		gui.dispose();
		instance = null;
	}
}

package main;

import java.awt.Dimension;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class SplashScreen {
	private JFrame gui;
	private SplashScreen instance;
	public SplashScreen() {
		instance = this;
		gui = new JFrame("Joust");
		gui.setPreferredSize(new Dimension(32*32, 18*32));
		gui.setResizable(false);
		try {
			File media = new File(getClass().getResource("/splash.gif").getFile());
			ImageIcon icon = new ImageIcon(media.getPath());
			JLabel splash = new JLabel(icon);
			gui.add(splash);
		}catch(Exception e) {
			e.printStackTrace();
		}
		gui.pack();
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setVisible(true);
		gui.addKeyListener(new SplashScreenListener(this));
	}
	public void startGame() {
		// TODO Auto-generated method stub
		new Joust();
		gui.dispose();
		instance = null;
	}
}

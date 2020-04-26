package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SplashScreenListener implements KeyListener {
	
	private SplashScreen splash;

	public SplashScreenListener(SplashScreen splash) {
		this.splash = splash;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			splash.startGame();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}

package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameActionListener implements ActionListener {
	
	private Joust instance;
	private GameComponent  game;
	private int tics;
	public GameActionListener(Joust instance, GameComponent game) {
		this.instance = instance;
		this.game = game;
		tics = 0;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		advanceOneTick();	
	}

	private void advanceOneTick() {
		if(tics > 3) {
		game.repositionPlayer();
		instance.calcFPS();
		game.repaint();	
		game.moveMonsters();
		game.moveEgg();
		game.collision();
		game.levelup();
		}
		tics++;
	}

}

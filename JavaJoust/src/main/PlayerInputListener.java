package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

import entities.Player;
import level.LevelLoader;

public class PlayerInputListener implements KeyListener {

	private Player player;
	private LevelLoader loader;
	private GameComponent game;
	private int levelNumber;
	private Joust joust;

	public PlayerInputListener(GameComponent game, Joust joust) {
		// TODO Auto-generated constructor stub
		this.player = game.getPlayer();
		this.game = game;
		this.levelNumber = 0;
		loader = new LevelLoader();
		this.joust = joust;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			player.fly();
			player.setYVelocity(-3);
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.moving();
			player.setXVelocity(-2);
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.moving();
			player.setXVelocity(2);
		}
		if (Character.toLowerCase(e.getKeyChar()) == 'u') {
			game.setLevel(loader.loadFile("level_" + ((Math.abs(levelNumber) % 2) + 1)));
			levelNumber++;
		}
		if (Character.toLowerCase(e.getKeyChar()) == 'd') {
			game.setLevel(loader.loadFile("level_" + ((Math.abs(levelNumber % 2) + 1))));
			levelNumber--;
		}
		if (Character.toLowerCase(e.getKeyChar()) == '=') {
			joust.restart();
		}
		if (Character.toLowerCase(e.getKeyChar()) == 'h') {
			JOptionPane.showMessageDialog(null,
					"Controls:\n arrow keys to move,\n U/D to change levels,\n = to restart,\n h for help");
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			player.setYVelocity(0);
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.setXVelocity(0);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}

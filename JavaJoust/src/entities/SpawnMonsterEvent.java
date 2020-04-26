package entities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.GameComponent;

public class SpawnMonsterEvent implements ActionListener {

	private GameComponent game;
	private Egg sender;
	public SpawnMonsterEvent(GameComponent game, Egg sender) {
		this.game = game;
		this.sender = sender;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("spawning monster");
		game.addMonster(new Monster(sender.centerPosition.x,sender.centerPosition.y -24,game));
		game.removeEgg(sender);
	}

}

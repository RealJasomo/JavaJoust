package entities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InvincibleTimerEvent implements ActionListener {
	Player player;
	public InvincibleTimerEvent(Player player) {
		// TODO Auto-generated constructor stub
		this.player = player;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		player.stopInvincibility();
	}

}

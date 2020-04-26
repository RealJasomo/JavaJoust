package entities;

import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.Timer;

import main.GameComponent;

public class ShootMonster extends Monster {
	private Timer shootTimer;
	public ShootMonster(int x, int y,ProjectileRenderer render, GameComponent game) {
		super(x, y, game);
		shootTimer = new Timer(20, new ProjectileLaunchListener(this, render));
		loadSprite("monster_green","monster_green");
		shootTimer.start();
	} 
	@Override
	public void destruct() {
		shootTimer.stop();
		super.destruct();
	}
}

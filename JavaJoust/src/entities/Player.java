package entities;

import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;

import java.util.ArrayList;
import javax.swing.Timer;

import javax.imageio.ImageIO;

import main.GameComponent;
import main.PlayerDeathException;

@SuppressWarnings("serial")
public class Player extends Entity {

	private ArrayList<KeyListener> listeners;
	private GameComponent game;
	private boolean invincible;
	private Timer invincibleTime;
	public Player(int x, int y, GameComponent game) {
		super(x, y);
		fly();
		this.game = game;
		invincible = false;
		loadSprite("character","character_fly");
		invincibleTime = new Timer(1500,new InvincibleTimerEvent(this));
		listeners = new ArrayList<KeyListener>();
	}

	public boolean onCollision(Monster monster) throws PlayerDeathException {
		if (monster.getHitbox() != null && !invincible) {
			if (monster.getHitbox().intersects(getHitbox()) && centerPosition.y < monster.centerPosition.y) {
				game.incrementScore(500);
				game.removeMonster(monster);
				return true;
			}else if(monster.getHitbox().intersects(getHitbox()) && centerPosition.y > monster.centerPosition.y) {
				game.incrementScore(50);
				game.die();
			}
		}
		return false;
	}
	

	public void onCollision(Projectile projectile) throws PlayerDeathException {
		if(projectile.getHitbox() != null && projectile != null && !invincible)
			if(projectile.getHitbox().intersects(getHitbox())) {
		game.incrementScore(50);
		game.die();
		game.removeProjectile(projectile);
		}
	}

	public boolean onCollision(Egg egg) {
		if(egg.getHitbox() != null && egg.isNotInvicible())
		if(egg.getHitbox().intersects(getHitbox())) {
			game.removeEgg(egg);
			game.incrementScore(250);
			egg.destruct();
			System.out.println("remove the egg");
			return true;
		}
		return false;
	}

	public void onCollision(Environment environment) {
		Rectangle2D.Double hitboxE = environment.getHitbox();
		if (isMoving()) {
			fly();
		}
		if (isNotWalking() && hitboxE.intersects(getHitbox()) && hitboxE.getY() > getHitbox().getY()) {
			walk();
		}else if(hitboxE.intersects(getHitbox()) && hitboxE.getY() < getHitbox().getY()) {
			setYVelocity(0);
		}
	}

	public void addInputListener(KeyListener listener) {
		listeners.add(listener);
	}

	public void checkCollisions(ArrayList<Environment> levelData, ArrayList<Monster> monsters, ArrayList<Egg> eggs,
			ProjectileRenderer render) throws PlayerDeathException {
		// TODO Auto-generated method stub

		for (Environment environment : levelData) {
			if(environment != null && environment.getHitbox() != null)
				onCollision(environment);
		}
		for (Monster monster : monsters) {
			if(onCollision(monster)) {
				break;
			}
		}
		for (Egg egg : eggs) {
			if(onCollision(egg)) {
				break;
			}

		}
		for (Projectile projectile : render.getProjectiles()) {
			onCollision(projectile);
		}

	}

	public void startInvincibility() {
		// TODO Auto-generated method stub
		invincible = true;
		invincibleTime.start();
	}
	public void stopInvincibility() {
		invincible = false;
		invincibleTime.stop();
	}

}

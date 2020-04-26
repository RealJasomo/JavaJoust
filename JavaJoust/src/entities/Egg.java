package entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.Timer;

import main.GameComponent;

public class Egg extends Entity {
	private Timer spawnTime;
	private long timeOfSpawn;
	private GameComponent game;
	public Egg(int x, int y, GameComponent game) {
		super(x, y);
		// TODO Auto-generated constructor stub
		fly();
		loadSprite("egg","egg");
		this.game = game;
		spawnTime = new Timer(6000, new SpawnMonsterEvent(game,this));
		timeOfSpawn = System.currentTimeMillis();
	}
	@Override
	public void calculatePosition() {
		super.calculatePosition();
		if(centerPosition.y > 32*18)
			game.removeEgg(this);
		
	}
	public void checkCollisions(ArrayList<Environment> levelData) {
		// TODO Auto-generated method stub
		for(Environment environment : levelData)
			if(environment != null)
				onCollision(environment);
	}
	private void onCollision(Environment environment) {
		if(environment.getHitbox() != null && getHitbox() != null)
		if(environment.getHitbox().intersects(getHitbox()) && isNotWalking()) {
			walk();
			spawnTime.start();
		}
	}
	@Override
	public void destruct() {
		spawnTime.stop();
		super.destruct();
	}
	@Override
	public void onDraw(Graphics2D g2) {
		g2.setColor(Color.green);
		hitbox = new Rectangle2D.Double(centerPosition.x, centerPosition.y, 12, 12);
		Ellipse2D.Double ball = new Ellipse2D.Double(centerPosition.x, centerPosition.y, 12, 12);
		if(sprite == null && spriteFlying == null)
			g2.fill(ball);
		else {
			if(!isNotWalking())
				g2.drawImage(sprite, null, centerPosition.x, centerPosition.y);
			else
				g2.drawImage(spriteFlying, null, centerPosition.x, centerPosition.y);
		}
		g2.setColor(Color.BLACK);
		}
	public boolean isNotInvicible() {
		return (System.currentTimeMillis() - timeOfSpawn) > 1500;
	}
}

package entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Projectile extends Entity {

	public Projectile(int x, int y, int direction) {
		super(x, y);
		setXVelocity(direction*2);
		// TODO Auto-generated constructor stub
		loadSprite("projectile","projectile");
	}

	public void onCollision(Projectile projectile) {

	}
	@Override
	public void onDraw(Graphics2D g2) {
		g2.setColor(Color.red);
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
	@Override
	public void calculatePosition() {
		if(centerPosition.x > 32*32 || centerPosition.x < -12)
			destruct();
		else
			super.calculatePosition();
		}

}

package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.swing.JComponent;

public abstract class Entity extends JComponent {
	private EntityState state;
	private Entity instance;
	protected Point centerPosition;
	private int dx;
	private int dy;
	protected int direction;
	protected BufferedImage sprite;
	protected BufferedImage spriteFlying;
	private AudioInputStream collisionSoundEffect;
	protected Rectangle2D.Double hitbox;

	public Entity(int x, int y) {
		state = EntityState.WALK;
		dx = 0;
		dy = 0;
		instance = this;
		direction = 1;
		centerPosition = new Point(x, y);
	}

	public void calculatePosition() {
		if (centerPosition.x > 32 * 32) {
			centerPosition.setLocation(0, centerPosition.y);
		}
		if (centerPosition.x < -32) {
			centerPosition.setLocation(32*32, centerPosition.y);
		}
		if (state == EntityState.WALK || state == EntityState.MOVING) {
			int x = dx + centerPosition.x;
			centerPosition.setLocation(x, centerPosition.y);
			return;
		}
		if (state == EntityState.FLY) {
			int x = dx + centerPosition.x;
			int y = dy + centerPosition.y + 1;
			centerPosition.setLocation(x, y);
		}
		if (centerPosition.y > 32 * 18)
			destruct();
	}

	public void destruct() {
		instance = null;
	}

	public void onDraw(Graphics2D g2) {
		hitbox = new Rectangle2D.Double(centerPosition.x, centerPosition.y, 32, 32);
		if (sprite == null && spriteFlying == null)
			g2.fill(getHitbox());
		else {
			if (state == EntityState.WALK)
				g2.drawImage(sprite, null, centerPosition.x, centerPosition.y);
			else
				g2.drawImage(spriteFlying, null, centerPosition.x, centerPosition.y);
		}
		g2.setColor(Color.BLACK);
	}

	private BufferedImage flipVertical(BufferedImage src) {
		AffineTransform translate = AffineTransform.getScaleInstance(-1.0, 1.0); // scaling
		translate.translate(-src.getWidth(), 0); // translating
		AffineTransformOp transform = new AffineTransformOp(translate, null); // transforming

		return transform.filter(src, null); // filtering
	}

	protected void flipSprites() {
		sprite = flipVertical(sprite);
		spriteFlying = flipVertical(spriteFlying);
	}

	public void setXVelocity(int x) {
		int oldDirection = direction;
		if(x!=0)
		direction = x / Math.abs(x);
		if (oldDirection != direction) {
			if (sprite != null && spriteFlying != null) {
				flipSprites();
			}
		}
		dx = x;
	}

	protected void loadSprite(String walkName, String flyName) {
		try {
			File spriteHero = new File(getClass().getResource("/" + walkName + ".png").getFile());
			sprite = ImageIO.read(spriteHero);
			File spriteHeroFlying = new File(getClass().getResource("/" + flyName + ".png").getFile());
			spriteFlying = ImageIO.read(spriteHeroFlying);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setYVelocity(int y) {
		dy = y;
	}

	public Point centerPosition() {
		return centerPosition;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		onDraw(g2);

	}

	public boolean isMoving() {
		return state == EntityState.MOVING;
	}

	public void walk() {
		state = EntityState.WALK;
	}

	public void fly() {
		state = EntityState.FLY;
	}

	public void moving() {
		state = EntityState.MOVING;
	}

	public boolean isNotWalking() {
		return state == EntityState.FLY;
	}

	public Rectangle2D.Double getHitbox() {
		return hitbox;
	}
}

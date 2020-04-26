package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Environment extends Entity {

	public Environment(int x, int y) {
		super(x, y);
		loadSprite("env","env");
	}

	@Override
	public void onDraw(Graphics2D g2) {
		g2.setColor(new Color(214,143,43));
		super.onDraw(g2);
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

}

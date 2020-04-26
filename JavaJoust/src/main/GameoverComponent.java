package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;

public class GameoverComponent {
	private int x, y, score;
	public GameoverComponent(int x, int y, int score) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		this.score = score;
	}

	public void onDraw(Graphics2D g2) {
		// TODO Auto-generated method stub
		Rectangle2D.Double box = new Rectangle2D.Double(x,y,32*32,18*32);
		g2.fill(box);
		g2.setColor(Color.white);
		g2.setFont(new Font("TimesRoman", Font.PLAIN, 40));
		String one = "Game over";
		String two = "Final Score " + score;
		String three = "Press = to restart the game!! You loser";
		g2.drawString(one, (int)box.getCenterX()-getAdjustment(one), (int)box.getCenterY());
		g2.drawString(two, (int)box.getCenterX()-getAdjustment(two), (int)box.getCenterY()+40);
		g2.drawString(three,(int)box.getCenterX()-getAdjustment(three), (int)box.getCenterY()+80);
		g2.setColor(Color.black);
	}
	private int getAdjustment(String s) {
		return (s.length()/4) * 40;
	}
}

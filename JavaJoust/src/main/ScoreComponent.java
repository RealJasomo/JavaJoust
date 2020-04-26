package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;
import javax.swing.JLabel;

import entities.Player;


@SuppressWarnings("serial")
public class ScoreComponent extends JComponent {
	private int lives, score,x,y;
	private String score_text;
	private Rectangle2D.Double componentBox;
	public ScoreComponent(int x, int y) {
		this.x = x;
		this.y = y;
		this.lives = 5;
		this.score = 0;
		setText();
		componentBox = new Rectangle2D.Double(x,y,10*32,32);
	}
	
	public void onDraw(Graphics2D g2) {
		g2.setColor(new Color(199,141,16));
		g2.fill(componentBox);
		g2.setColor(Color.white);
		g2.setFont(new Font("TimesRoman", Font.PLAIN, 30));
		g2.drawString(score_text, x, y+30);
		g2.setColor(Color.black);
	}
	
	public void addScore(int scoreincrement) {
		score+=scoreincrement;
		setText();
	}
	
	private void setText() {
		score_text = "Score: " + score +" Lives: " + lives;
	}
	public void removeLife() {
		lives--;
		setText();
	}

	public void die(Player player) throws PlayerDeathException{
		// TODO Auto-generated method stub
		removeLife();
		if(lives <= 0) {
			player.destruct();
			throw new PlayerDeathException();
		}else {
			player.startInvincibility();
		}
	}
	public int getScore() {
		return score;
	}
}

package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JComponent;
import javax.swing.JFrame;

import audio.AudioManager;
import entities.Egg;
import entities.Environment;
import entities.Monster;
import entities.Player;
import entities.Projectile;
import entities.ProjectileRenderer;
import entities.ShootMonster;
import level.LevelLoader;

@SuppressWarnings("serial")
public class GameComponent extends JComponent {
	private Player player;
	private ArrayList<Environment> levelData;

	private ArrayList<Monster> monsters;
	private ArrayList<Monster> monstersToRemove;
	private ArrayList<Egg> eggs;
	private ArrayList<Egg> eggsToRemove;
	private ArrayList<Projectile> projectilesToRemove;

	private ProjectileRenderer render;
	private ScoreComponent score;
	private AudioManager am;
	private GameoverComponent gameover;
	private Joust joust;
	private LevelLoader loader;
	private int levelNumber;

	public GameComponent(Joust joust) {
		this.player = new Player(15 * 32, 10 * 32, this);

		this.render = new ProjectileRenderer();

		this.monsters = new ArrayList<Monster>();
		this.monstersToRemove = new ArrayList<Monster>();
		this.eggs = new ArrayList<Egg>();
		this.eggsToRemove = new ArrayList<Egg>();
		this.projectilesToRemove = new ArrayList<Projectile>();

		this.monsters.add(new ShootMonster(32, 14 * 32, render, this));
		this.monsters.add(new Monster(32, 2 * 32, this));
		addRandomMonsters(5);

		this.levelData = new ArrayList<Environment>();
		loader = new LevelLoader();
		levelNumber = 0;
		this.setLevel(loader.loadFile("level_1"));

		this.joust = joust;
		this.am = new AudioManager();
		am.playBGM();

		this.score = new ScoreComponent(0, 0);
	}

	public void collision() {
		monsters.removeAll(monstersToRemove);
		for (Monster m : monstersToRemove) {
			eggs.add(new Egg(m.centerPosition().x + 16, m.centerPosition().y, this));
			m.destruct();
		}
		monstersToRemove.clear();
		eggs.removeAll(eggsToRemove);
		eggsToRemove.clear();
		for (Projectile p : projectilesToRemove)
			render.removeProjectile(p);
		projectilesToRemove.clear();
		try {
			player.checkCollisions(levelData, monsters, eggs, render);
		} catch (PlayerDeathException e) {
			gameover();
		}
		for (Egg egg : eggs) {
			egg.checkCollisions(levelData);
		}
		for (Monster monster : monsters)
			monster.checkCollision(levelData, monsters);
		for (Projectile p : render.getProjectiles())
			if (p == null)
				render.removeProjectile(p);
	}

	private void gameover() {
		gameover = new GameoverComponent(0, 0, score.getScore());
		am.stop();
		joust.stop();

	}

	public void setLevel(ArrayList<Environment> loadFile) {
		levelData = loadFile;
	}

	public void levelup() {
		System.out.println(monsters.size() + ": " + eggs.size());
		if (monsters.size() == 0 && eggs.size() == 0) {
			levelNumber += 1;
			setLevel(loader.loadFile("level_" + (levelNumber % 2 + 1)));
			addRandomMonsters(10);
		}
	}

	public void repositionPlayer() {
		player.calculatePosition();
	}

	public AudioManager getAudioManger() {
		return am;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		player.onDraw(g2);
		render.onDraw(g2);
		score.onDraw(g2);

		for (Egg e : eggs) {
			e.onDraw(g2);
		}
		for (Monster m : monsters) {
			if (m != null)
				m.onDraw(g2);
		}
		for (Environment e : levelData)
			e.onDraw(g2);
		if (gameover != null)
			gameover.onDraw(g2);
	}

	@Override
	public void repaint() {
		super.repaint();
		render.repaint();
	}

	public void moveMonsters() {
		// TODO Auto-generated method stub
		for (Monster monster : monsters) {
			if (monster != null) {
				monster.move();
				monster.calculatePosition();
			}
		}
	}

	public void moveEgg() {
		for (Egg egg : eggs)
			if (egg != null)
				egg.calculatePosition();
	}

	public void addMonster(Monster monster) {
		// TODO Auto-generated method stub
		this.monsters.add(monster);
	}

	public void removeEgg(Egg sender) {
		eggsToRemove.add(sender);
		sender.destruct();
	}

	public void removeMonster(Monster monster) {
		monstersToRemove.add(monster);
	}

	public void incrementScore(int scoreincrement) {
		this.score.addScore(scoreincrement);
	}

	public Player getPlayer() {
		// TODO Auto-generated method stub
		return player;
	}

	public void die() throws PlayerDeathException {
		this.score.die(player);
	}

	public void removeProjectile(Projectile projectile) {
		// TODO Auto-generated method stub
		projectilesToRemove.add(projectile);

	}

	public void addRandomMonsters(int amount) {
		int ymax = 17, xmax = 32;
		for (int i = 0; i < amount; i++) {
			boolean toSpawn = true;
			int randomy = ((int)(Math.random() * ymax) * 32) + 32;
			int randomx = (int) (Math.random() * xmax) * 32;
	
				int type = (int) (Math.random() * 9000);
				if (type >= 9000 / 2)
					this.monsters.add(new Monster(randomx, randomy, this));
				else
					this.monsters.add(new ShootMonster(randomx, randomy, render, this));
		}
	}

}

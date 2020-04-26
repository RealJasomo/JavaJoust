package entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import main.GameComponent;

public class Monster extends Entity{
	private int id;
	private GameComponent game;
	public Monster(int x, int y, GameComponent game) {
		super(x, y);
		// TODO Auto-generated constructor stub
		this.game  = game;
		direction = 1;
		setXVelocity(1);
		loadSprite("monster_red","monster_red");
		this.id = (int)(Math.random()*9999999);
	}
	public void onCollision(Entity entity) {
		if(entity != null && entity.getHitbox()!= null && getHitbox() != entity.getHitbox())
			if(getHitbox() != null)
			if(entity.getHitbox().intersects(getHitbox())) {
					flipDirection();
					centerPosition.x += direction * 3;
					flipSprites();
			}
	}
	public void onCollision(Environment environment) {
		if(environment.hitbox != null && hitbox != null) {
			if(environment.hitbox.getY() == hitbox.getY() && environment.hitbox.getX() == hitbox.getX()) {
				game.removeMonster(this);
			}
		}
	}

	private void flipDirection() {
		direction = -direction;
		
	}
	public int getId(){
		return id;
	}	
	public void move() {
		setXVelocity(direction);
		setYVelocity(-1);
		fly();
	}
	public void checkCollision(ArrayList<Environment> levelData, ArrayList<Monster> monsters) {
		ArrayList<Entity> entities = new ArrayList<Entity>();
		entities.addAll(levelData);
		entities.addAll(monsters);
		for(Entity e : entities)
			onCollision(e);
		for(Environment e : levelData)
			onCollision(e);
		
	}

}

package entities;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class ProjectileLaunchListener implements ActionListener {
	private ProjectileRenderer render;
	private ShootMonster monster;

	public ProjectileLaunchListener(ShootMonster monster, ProjectileRenderer render) {
		this.monster = monster;
		this.render = render;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		int spawnChance = (int)(Math.random() * 50000);
		if(spawnChance >= 49000) {
			Point centerPoint = monster.centerPosition;
			Projectile toBeAdded = new Projectile((int)(centerPoint.x+ 19),(int)(centerPoint.y+16),monster.direction);
			if(render.projectileCount() > 10)
				render.removeProjectile();
			render.updateProjectiles(toBeAdded);	
			}
	}

	
}

package entities;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class ProjectileRenderer {
	private ArrayList<Projectile> projectiles;
	
	public ProjectileRenderer() {
		this.projectiles = new ArrayList<Projectile>();
	}
	public void updateProjectiles(Projectile projectile) {
		// TODO Auto-generated method stub
		projectiles.add(projectile);
	}

	public void removeProjectile() {
		// TODO Auto-generated method stub
		projectiles.remove(0);
		
	}
	public void removeProjectile(Projectile projectile) {
		projectiles.remove(projectile);
	}
	public void onDraw(Graphics2D g2) {
		for(Projectile p : projectiles) {
			p.onDraw(g2);
		}
	}
	public int projectileCount() {
		// TODO Auto-generated method stub
		return projectiles.size();
	}
	public ArrayList<Projectile> getProjectiles(){
		return projectiles;
	}
	public void repaint() {
		for(Projectile p: projectiles) {
			p.calculatePosition();
			p.repaint();
		}
		
	}



}

package svemir;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Objekat {
	protected int x, y;
	protected Color color;
	
	public Objekat(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public synchronized void moveX(int disposition) {
		this.x += disposition;
	}
	
	public synchronized void moveY(int disposition) {
		this.y += disposition;
	}
	
	public abstract void draw(Graphics g);
	
	public static boolean isCollision(Objekat o1, int r1, Objekat o2, int r2) {
		if (Math.sqrt(Math.pow(o2.x - o1.x, 2) + Math.pow(o2.y - o1.y, 2)) <= (r1 + r2)) return true;
		else return false;
		// Ukoliko je rastojanje centara tela manje nego zbir poluprecnika njihovih kruznica doslo je do kolizije
	}
}

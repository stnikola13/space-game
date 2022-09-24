package svemir;

import java.awt.Color;
import java.awt.Graphics;

public class Kometa extends NebeskoTelo {
	private int angleOffset;
	private int xcoord[];
	private int ycoord[];
	private int points;

	public Kometa(int x, int y, int radius) {
		super(x, y, Color.GRAY, radius);
		this.angleOffset = (int)(Math.random() * 360);
		
		points = 5;
		int step = 360 / points;
		xcoord = new int[points];
		ycoord = new int[points];
																					// Generise se nasumican broj od 0 do 360 stepeni
		for (int i = 0; i < points; i++) {											// koji ce sluziti kao "nasumican pomeraj" svake
			xcoord[i] = (int)(r * Math.cos(Math.toRadians(step * i + angleOffset)));// tacke za isti broj stepeni. Time se od obicnog
			ycoord[i] = (int)(r * Math.sin(Math.toRadians(step * i + angleOffset)));// "uspravnog" dobija pomereni petougao.
		}
		
	}

	@Override
	public void draw(Graphics g) {
		Color oldColor = g.getColor();
		g.setColor(color);															// Promena boje Graphics
		
		g.translate(x, y);														// Promena pozicije centra Graphics
		g.fillPolygon(xcoord, ycoord, points);
		
		g.translate(-x, -y);													// Vracanje originalne pozicije centra Graphics
		g.setColor(oldColor);													// Vracanje originalne boje Graphics
	}

}

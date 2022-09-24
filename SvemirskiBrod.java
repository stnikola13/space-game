package svemir;

import java.awt.Color;
import java.awt.Graphics;

public class SvemirskiBrod extends Objekat {
	private int points;
	private int xcoord[], ycoord[];

	public SvemirskiBrod(int x, int y) {
		super(x, y, Color.GREEN);
		points = 4;
		xcoord = new int[points];
		ycoord = new int[points];
		
		xcoord[0] = 0; ycoord[0] = -10; 
		xcoord[1] = -6; ycoord[1] = 0; 
		xcoord[2] = 0; ycoord[2] = 10; 
		xcoord[3] = 6; ycoord[3] = 0; 
		
	}

	@Override
	public void draw(Graphics g) {
		Color oldColor = g.getColor();
		g.setColor(Color.GREEN);															// Promena boje Graphics
		
		g.translate(x, y);														// Promena pozicije centra Graphics
		g.fillPolygon(xcoord, ycoord, points);
		
		g.translate(-x, -y);													// Vracanje originalne pozicije centra Graphics
		g.setColor(oldColor);													// Vracanje originalne boje Graphics
	}

}

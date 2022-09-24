package svemir;

import java.awt.Color;
import java.awt.Graphics;

public class KosmickaPrasina extends NebeskoTelo {

	public KosmickaPrasina(int x, int y, int radius) {
		super(x, y, Color.YELLOW, radius);
	}

	@Override
	public void draw(Graphics g) {
		Color oldColor = g.getColor();
		g.setColor(color);															// Promena boje Graphics
																					// Generise se nasumican broj od 0 do 360 step
		g.translate(x, y);														// Promena pozicije centra Graphics
		g.fillOval(0, 0, r, r);
		
		g.translate(-x, -y);													// Vracanje originalne pozicije centra Graphics
		g.setColor(oldColor);	
	}

}

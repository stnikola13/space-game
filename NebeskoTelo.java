package svemir;

import java.awt.Color;

public abstract class NebeskoTelo extends Objekat {
	protected int r;

	public NebeskoTelo(int x, int y, Color color, int radius) {
		super(x, y, color);
		this.r = radius;
	}
}

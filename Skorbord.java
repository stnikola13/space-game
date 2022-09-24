package svemir;

import java.awt.Label;

@SuppressWarnings("serial")
public class Skorbord extends Label {
	private int points;
	
	public Skorbord() {
		points = 0;
		this.setText("Poeni: " + points);
	}
	
	public synchronized void addPoints(int points) {
		this.points += points;
		this.setText("Poeni: " + this.points);
	}
	
	public int getPoints() {
		return points;
	}
}

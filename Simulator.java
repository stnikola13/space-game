package svemir;

import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class Simulator extends Frame {
	private Svemir space;
	private Generator gen;
	private Button buttonStart;
	private Skorbord score;
	
	public Simulator() {
		super("");
		setBounds(1100, 550, 200, 400);
		setResizable(false);
		
		space = new Svemir();
		gen = new Generator(space);
		buttonStart = new Button("Pokreni!");
		score = new Skorbord();
		
		populateWindow();
		addListeners();
		setVisible(true);
		space.requestFocus();
	}
	
	private void populateWindow() {
		Panel bottom = new Panel();
		bottom.add(score);
		bottom.add(buttonStart);
		
		this.setLayout(new BorderLayout());
		this.add(space, BorderLayout.CENTER);
		this.add(bottom, BorderLayout.SOUTH);	
	}
	
	private void addListeners() {
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				space.stop();
				gen.stop();
				dispose();
			}
		});
		
		buttonStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				space.start();
				gen.start();
				buttonStart.setEnabled(false);
				space.requestFocus();
			}		
		});
	}
	
	public void gameOver() {
		space.stop();
		gen.stop();
		new Kraj(this, score);
	}
	
	public void close() {
		space.stop();
		gen.stop();
		dispose();
	}
	
	public void collected() {
		score.addPoints(1);
		score.revalidate();
	}

	public static void main(String[] args) {
		new Simulator();
	}

}

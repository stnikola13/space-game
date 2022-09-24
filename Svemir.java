package svemir;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Svemir extends Canvas implements Runnable {
	private ArrayList<NebeskoTelo> celestialBodies;
	private SvemirskiBrod player;
	private Thread thread = null;

	public Svemir() {
		this.setBackground(Color.BLACK);
		celestialBodies = new ArrayList<NebeskoTelo>();
		player = new SvemirskiBrod(93, 300);
		addListener();
	}
	
	public synchronized void addBody(NebeskoTelo body) {
		celestialBodies.add(body);
	}
	
	private void addListener() {
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int disp = 7;
				char key = Character.toUpperCase(e.getKeyChar());
				switch (key) {
					case KeyEvent.VK_W: {
						if (player.getY() - disp > 6) player.moveY(-disp); 		
						break;
					}
					case KeyEvent.VK_A: {
						if (player.getX() - disp > 5) player.moveX(-disp); 
						break;
					}
					case KeyEvent.VK_S: {
						if (player.getY() + disp < getHeight() - 5) player.moveY(+disp); 
						break;
					}
					case KeyEvent.VK_D: {
						if (player.getX() + disp < getWidth() - 3) player.moveX(+disp);  
						break;
					}
				}
				repaint();
			}			
		});
	}
	
	@Override
	public void paint(Graphics g) {
		player.draw(g);
		synchronized (this) {
			for (int i = 0; i < celestialBodies.size(); i++) {
				NebeskoTelo body = celestialBodies.get(i);
				if (body instanceof KosmickaPrasina) body.draw(g);
			}
			for (int i = 0; i < celestialBodies.size(); i++) {
				NebeskoTelo body = celestialBodies.get(i);
				if (body instanceof Kometa) body.draw(g);
			}
		}	
	}

	@Override
	public void run() {
		try {
			while (!thread.isInterrupted()) {
				Thread.sleep(100);
				repaint();
				
				for (int i = 0; i < celestialBodies.size(); i++) {
					NebeskoTelo body = celestialBodies.get(i);
					if (body instanceof KosmickaPrasina) {
						KosmickaPrasina p = (KosmickaPrasina)body;
						if (Objekat.isCollision(p, p.r, player, 10)) {	// 10 je radius igraca
							celestialBodies.remove(i);
							i--;
							Simulator sim = (Simulator)this.getParent();
							sim.collected();
						}
						else {
							p.moveY(5);
						}
					}
					
					if (body instanceof Kometa) {
						Kometa kometa = (Kometa)body;
						if (Objekat.isCollision(kometa, kometa.r, player, 10)) {	// 10 je radius igraca
							Simulator sim = (Simulator)this.getParent();
							sim.gameOver();
						}
						kometa.moveY(5);
					}
					
					if (body.getY() > getHeight() + 50) celestialBodies.remove(i);
				}
			}
			
		} catch (InterruptedException e) {}
	}
	
	public void start() {
		thread = new Thread(this);
		thread.start();
	}
	
	public void stop() {
		if (thread != null) {
			celestialBodies.clear();
			repaint();
			thread.interrupt();
		}
	}
}

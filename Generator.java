package svemir;

public class Generator implements Runnable {
	private Svemir space;
	private Thread thread = null;
	
	public Generator(Svemir space) {
		this.space = space;
	}

	@Override
	public void run() {
		try {
			boolean cosmicDust = false;
			while (!thread.isInterrupted()) {
				Thread.sleep(900);
				int x = (int)(Math.random() * 200); 			// Vrednost izmedju 0 i 200
				int y = 0;
				int r = (int)(Math.random() * 20 + 10); 		// Vrednost izmedju 10 i 30
				space.addBody(new Kometa(x, y, r));
				if (cosmicDust) {
					x = (int)(Math.random() * 200);
					y = (int)(Math.random() * 150 + 100); 
					r = 7;
					space.addBody(new KosmickaPrasina(x, y, r));
				}
				cosmicDust = !cosmicDust;
			}
		}
		catch (InterruptedException e) {}
	}
	
	public void start() {										// Ako treba omoguciti vise niti ovde dodaj promenu stopped
			thread = new Thread(this);
			thread.start();
	}
	
	public void stop() {
		if (thread != null) {
			thread.interrupt();
		}
	}
}

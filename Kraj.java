package svemir;

import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class Kraj extends Dialog {
	private Button end;
	private Label points;
	@SuppressWarnings("unused")
	private Skorbord score;
	
	public Kraj(Frame owner, Skorbord score) {
		super(owner);
		this.score = score;
		
		this.setTitle("Kraj");
		this.setBounds(1100, 700, 200, 100);
		this.setModalityType(ModalityType.APPLICATION_MODAL);
		
		points = new Label("Kraj igre! Osvojen broj poena: " + score.getPoints());
		points.setAlignment(Label.CENTER);
		end = new Button("Završi!");
		
		Panel p1 = new Panel(); p1.add(points);
		Panel p2 = new Panel(); p2.add(end);
		
		this.setLayout(new BorderLayout());
		
		this.add(p1, BorderLayout.CENTER);				
		this.add(p2, BorderLayout.SOUTH);
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {			
				((Simulator)owner).close();
				Kraj.this.dispose();
			}
		});
		
		end.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {	
				((Simulator)owner).close();
				Kraj.this.dispose();
			}
		});
		
		this.setResizable(false);
		this.setVisible(true);
	}
}

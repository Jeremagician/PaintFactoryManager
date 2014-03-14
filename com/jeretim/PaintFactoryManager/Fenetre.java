package com.jeretim.PaintFactoryManager;

import java.awt.*;
import javax.swing.*;
import com.jeretim.PaintFactoryManager.Tool.*;

public class Fenetre implements Runnable {
	public void run() {
		AireDeDessin mon_dessin = new AireDeDessin(500, 200);
		TraceManager trace_manager = new TraceManager(mon_dessin, new Pencil(10), new Eraser(10));
		EcouteurDevenements listener = new EcouteurDevenements(trace_manager);
		// Creation d'une fenetre
		JFrame frame = new JFrame("Ma fenetre a moi");

		// Creation du composant de dessin et ajout de l'objet de traitement
		// des evenements provenant de la souris
		mon_dessin.addMouseListener(listener);
		mon_dessin.addMouseMotionListener(listener);
		mon_dessin.addComponentListener(mon_dessin);

		frame.add(mon_dessin);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 200);
		frame.setVisible(true);
	}
}

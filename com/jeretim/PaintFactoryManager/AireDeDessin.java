package com.jeretim.PaintFactoryManager;

import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.awt.event.*;

class AireDeDessin extends JComponent implements ComponentListener {
	public BufferedImage image;

	public AireDeDessin(int width, int height) {
		// Par defaut, cree une image suffisament grande.
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D drawable = image.createGraphics();
		drawable.setPaint(Color.white);
		drawable.fillRect(0, 0, width, height);
	}

	public void clear() {
		Graphics2D drawable = image.createGraphics();
		drawable.setPaint(Color.white);
		drawable.fillRect(0, 0, getSize().width, getSize().height);
	}
	
	public void resize() {
		// Redimensionne que si on agrandi la fenetre.
		if (getSize().width > image.getWidth() ||
		    getSize().height > image.getHeight()) {
			BufferedImage scaled = new BufferedImage(getSize().width, getSize().height, BufferedImage.TYPE_INT_RGB);
			Graphics2D drawable = scaled.createGraphics();

			// Copie l'ancien buffer
			drawable.setPaint(Color.white);
			drawable.fillRect(0, 0, scaled.getWidth(), scaled.getHeight());
			drawable.drawImage(image.getSubimage(0, 0, image.getWidth(), image.getHeight()), 0, 0, Color.white, null);
			image = scaled;
		}
	}

	public void paintComponent(Graphics g) {
		Graphics2D drawable = image.createGraphics();
		g.drawImage(image, 0, 0, null);
	}

	public void componentResized(ComponentEvent e) {
		resize();
		repaint();
	}

	public void componentHidden(ComponentEvent e) {}
	public void componentMoved(ComponentEvent e) {}
	public void componentShown(ComponentEvent e) {}
}

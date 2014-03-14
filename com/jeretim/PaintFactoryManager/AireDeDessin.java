package com.jeretim.PaintFactoryManager;

import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.awt.event.*;

class AireDeDessin extends JComponent implements ComponentListener {
	public BufferedImage image;
	private Point sel_origin, sel_dest;
	private BufferedImage clipboard;

	public AireDeDessin(int width, int height) {
		// Par defaut, cree une image suffisament grande.
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D drawable = image.createGraphics();
		drawable.setPaint(Color.white);
		drawable.fillRect(0, 0, width, height);

		sel_origin = null;
		sel_dest   = null;
		clipboard  = null;
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
		g.drawImage(image, 0, 0, null);
		if (sel_origin != null) {
			g.setColor(Color.red);
			g.drawRect(sel_origin.x, sel_origin.y, sel_dest.x, sel_dest.y);
		}
	}

	public void componentResized(ComponentEvent e) {
		resize();
		repaint();
	}

	public void from_file(File file) {
		try {
			image = javax.imageio.ImageIO.read(file);
		} catch(Exception e) {
			System.out.println(e);
		}
		repaint();
	}

	public void to_file(File file) {
		try {
			javax.imageio.ImageIO.write(image.getSubimage(0, 0, getSize().width, getSize().height), "png", file);
		} catch(Exception e) {
			System.out.println(e);
		}
	}

	public void begin_selection(Point sel) {
		sel_origin = sel;
		sel_dest   = sel;
		clipboard = null;
	}

	public void update_selection(Point sel) {
		sel_dest = new Point(sel.x - sel_origin.x, sel.y - sel_origin.y);
	}

	public void end_selection(Point sel) {
		clipboard = image.getSubimage(sel_origin.x, sel_origin.y, sel_dest.x, sel_dest.y);
		sel_origin = null;
	}

	public void paste(Point pos) {
		if (clipboard == null)
			return;

		Graphics2D drawable = image.createGraphics();
		drawable.drawImage(clipboard, pos.x, pos.y, null);
	}

	// Ignore methods
	public void componentHidden(ComponentEvent e) {}
	public void componentMoved(ComponentEvent e)  {}
	public void componentShown(ComponentEvent e)  {}
}

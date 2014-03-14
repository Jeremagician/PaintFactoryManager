package com.jeretim.PaintFactoryManager;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class EcouteurDevenements implements MouseListener, MouseMotionListener {
	TraceManager manager;
	AireDeDessin drawarea;
	
	public static enum InputMode {
		DRAW, COPY, PASTE
	};
	
	private InputMode mode;

	public EcouteurDevenements(TraceManager manager, AireDeDessin drawarea) {
		this.manager = manager;
		this.drawarea = drawarea;
		mode = InputMode.DRAW;
	}
	
	public void set_mode(InputMode mode) {
		this.mode = mode;
	}

	public void mousePressed(MouseEvent e) {
		if (mode == InputMode.DRAW) {
			if (e.getButton() == MouseEvent.BUTTON1)
				manager.startDraw(TraceManager.ToolId.LEFT, e.getX(), e.getY());
			else if (e.getButton() == MouseEvent.BUTTON3)
				manager.startDraw(TraceManager.ToolId.RIGHT, e.getX(), e.getY());
		} else if (mode == InputMode.PASTE) {
			drawarea.paste(e.getPoint());
			mode = InputMode.DRAW;
			drawarea.repaint();
		} else if (mode == InputMode.COPY) {
			drawarea.begin_selection(e.getPoint());
		}
	}

	public void mouseReleased(MouseEvent e) {
		if (mode == InputMode.COPY) {
			drawarea.end_selection(e.getPoint());
			mode = InputMode.DRAW;
		} else if (mode == InputMode.DRAW)
			manager.stopDraw();
		drawarea.repaint();
	}

	public void mouseDragged(MouseEvent e) {
		if (mode == InputMode.COPY)
			drawarea.update_selection(e.getPoint());
		else if (mode == InputMode.DRAW)
			manager.trace(e.getPoint().x, e.getPoint().y);
		drawarea.repaint();
	}
	
	public void mouseMoved(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
}

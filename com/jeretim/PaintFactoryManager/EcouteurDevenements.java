package com.jeretim.PaintFactoryManager;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class EcouteurDevenements implements MouseListener, MouseMotionListener {
	TraceManager manager;
	
	public EcouteurDevenements(TraceManager manager) {
		this.manager = manager;
	}
	
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1)
			manager.startDraw(TraceManager.ToolId.LEFT, e.getX(), e.getY());
		else if (e.getButton() == MouseEvent.BUTTON3)
			manager.startDraw(TraceManager.ToolId.RIGHT, e.getX(), e.getY());	
	}

	public void mouseReleased(MouseEvent e) {
		manager.stopDraw();
	}

	public void mouseDragged(MouseEvent e) {
		manager.trace(e.getX(), e.getY());
	}
	
	public void mouseMoved(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
}

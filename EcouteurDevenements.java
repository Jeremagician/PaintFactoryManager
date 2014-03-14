import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class EcouteurDevenements implements MouseListener, MouseMotionListener {
	AireDeDessin aire;
	int origin_x, origin_y;


	private Trace.DrawMode mode;

	public EcouteurDevenements(AireDeDessin aire) {
		this.aire = aire;
		this.mode = Trace.DrawMode.NONE;
	}
	
	public void mousePressed(MouseEvent e) {
		origin_x = e.getX();
		origin_y = e.getY();

		if (e.getButton() == MouseEvent.BUTTON1)
			mode = Trace.DrawMode.DRAW;
		else if (e.getButton() == MouseEvent.BUTTON3)
			mode = Trace.DrawMode.ERASE;
	}

	public void mouseReleased(MouseEvent e) {
		this.mode = Trace.DrawMode.NONE;
		aire.repaint();
	}

	public void mouseDragged(MouseEvent e) {
		// Selon le mode de dessin, dessine ou efface
		if (mode == Trace.DrawMode.DRAW) {
			aire.draw_line(origin_x, origin_y, e.getX(), e.getY());
			origin_x = e.getX();
			origin_y = e.getY();
			aire.repaint();
		} else if (mode == Trace.DrawMode.ERASE) {
			aire.erase(e.getX(), e.getY());
			aire.repaint();
		}
	}


	
	public void mouseMoved(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
}

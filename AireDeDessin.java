import java.awt.*;
import javax.swing.*;
import java.awt.image.*;

class AireDeDessin extends JComponent {
	BufferedImage image;

	public AireDeDessin(int width, int height) {
		// Par defaut, cree une image suffisament grande.
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D drawable = image.createGraphics();
		drawable.setPaint(Color.white);
		drawable.fillRect(0, 0, 1000, 1000);
	}

	public void clear() {
		
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

	public void draw_line(int x, int y, int ox, int oy) {
		Graphics2D drawable = image.createGraphics();
		drawable.setPaint(Color.black);
		drawable.drawLine(x, y, ox, oy);
	}

	public void erase(int x, int y) {
		Graphics2D drawable = image.createGraphics();
		drawable.setPaint(Color.white);
		drawable.drawRect(x - 5, y - 5, 10, 10);
	}

	public void paintComponent(Graphics g) {
		Graphics2D drawable = image.createGraphics();
		g.drawImage(image, 0, 0, null);
	}
}
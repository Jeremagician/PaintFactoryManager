import java.awt.*;
import javax.swing.*;

public class TestFenetre implements Runnable {
	public void run() {
		AireDeDessin mon_dessin = new AireDeDessin();
		EcouteurDevenements listener = new EcouteurDevenements(mon_dessin);
		// Creation d'une fenetre
		JFrame frame = new JFrame("Ma fenetre a moi");

		// Creation du composant de dessin et ajout de l'objet de traitement
		// des evenements provenant de la souris
		mon_dessin.addMouseListener(listener);
		mon_dessin.addMouseMotionListener(listener);
		mon_dessin.addComponentListener(listener);

		frame.add(mon_dessin);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 200);
		frame.setVisible(true);
	}

	public static void main(String [] args) {
		SwingUtilities.invokeLater(new TestFenetre());
	}
}

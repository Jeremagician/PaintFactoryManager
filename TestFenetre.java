import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TestFenetre implements Runnable {
	public JMenuBar create_menubar() {
		JMenuBar bar = new JMenuBar();
		JMenu fichier = new JMenu("Fichier");
		JMenu edition = new JMenu("Edition");
		JMenuItem item;

		// Fichier
		fichier.setMnemonic(KeyEvent.VK_F);
		item = new JMenuItem("Nouveau",
				     UIManager.getIcon("FileView.fileIcon"));
		item.setMnemonic(KeyEvent.VK_N);
		fichier.add(item);
		item = new JMenuItem("Ouvrir",
				     UIManager.getIcon("FileView.directoryIcon"));
		item.setMnemonic(KeyEvent.VK_O);
		fichier.add(item);
		item = new JMenuItem("Enregistrer",
				     UIManager.getIcon("FileView.floppyDriveIcon"));
		item.setMnemonic(KeyEvent.VK_S);
		fichier.add(item);
		fichier.addSeparator();
		item = new JMenuItem("Quitter", KeyEvent.VK_Q);
		fichier.add(item);

		// Edition
		edition.setMnemonic(KeyEvent.VK_E);
		item = new JMenuItem("Annuler", KeyEvent.VK_A);
		edition.add(item);
		item = new JMenuItem("Refaire", KeyEvent.VK_R);
		edition.add(item);
		edition.addSeparator();
		item = new JMenuItem("Copier", KeyEvent.VK_C);
		edition.add(item);
		item = new JMenuItem("Coller", KeyEvent.VK_V);
		edition.add(item);

		bar.add(fichier);
		bar.add(edition);

		return bar;
	}

	public JFrame create_frame() {
		JFrame frame = new JFrame("PaintFactoryManager");
		AireDeDessin drawarea = new AireDeDessin(1000, 1000);
		EcouteurDevenements listener = new EcouteurDevenements(drawarea);

		drawarea.addMouseListener(listener);
		drawarea.addMouseMotionListener(listener);
		drawarea.addComponentListener(listener);

		frame.add(drawarea);
		frame.setJMenuBar(create_menubar());
		return frame;
	}

	public void run() {
		JFrame frame = create_frame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 200);
		frame.setVisible(true);
	}

	public static void main(String [] args) {
		SwingUtilities.invokeLater(new TestFenetre());
	}
}

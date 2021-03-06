package com.jeretim.PaintFactoryManager;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.*;
import com.jeretim.PaintFactoryManager.Tool.*;

public class Fenetre implements Runnable, ActionListener, MenuListener {
	private JFrame frame;
	private AireDeDessin drawarea;
	private TraceManager trace_manager;
	private EcouteurDevenements listener;

	public void save() {
		JFileChooser file_chooser = new JFileChooser();
		if (file_chooser.showSaveDialog(frame) != JFileChooser.APPROVE_OPTION)
			return;
		drawarea.to_file(file_chooser.getSelectedFile());
	}

	public void open() {
		JFileChooser file_chooser = new JFileChooser();
		if (file_chooser.showOpenDialog(frame) != JFileChooser.APPROVE_OPTION)
			return;
		drawarea.from_file(file_chooser.getSelectedFile());
	}

	public void show_about() {
		JOptionPane.showMessageDialog(frame,
					      "PaintFactoryManager\n" +
					      "A new kind of PhotoshopStrategyListener (Approved by test driven Dev.)\n\n" +
					      "By the mysterious team Jeretim.");
	}

	public void show_tipofday() {
		int n = (new Random()).nextInt(3);

		if (n == 0) {
			Object[] choices = {"La pizza 4 fromages", "La nullPointerException", "La réponse D"};
			String s = (String)JOptionPane.showInputDialog(
				frame,
				"C'est l'heure de ...",
				"Conseil du jour",
				JOptionPane.PLAIN_MESSAGE,
				null,
				choices,
				null);
			if (s == choices[1]) {
				try {
					throw null;
				} catch(Exception e) {
					e.printStackTrace();
					System.exit(1);
				}
			}
		} else if (n == 1) {
			JOptionPane.showMessageDialog(
				frame, "Les conseils du jour son accéssible dans le menu Aide -> Conseil du jour");
		} else if (n == 2) {
			String s = (String)JOptionPane.showInputDialog(
				frame,
				"Pour plus de sécurité, PaintFactoryManager vous conseil de rentrer votre mot de passe ci-dessous",
				"Conseil du jour",
				JOptionPane.PLAIN_MESSAGE,
				null,
				null,
				null);
		}
	}

	public JMenuBar create_menubar() {
		JMenuBar bar = new JMenuBar();
		JMenu file   = new JMenu("Fichier");
		JMenu edit   = new JMenu("Edition");
		JMenu help   = new JMenu("Aide");
		JMenuItem item;

		// Fichier
		file.setMnemonic(KeyEvent.VK_F);
		item = new JMenuItem("Nouveau",
				     UIManager.getIcon("FileView.fileIcon"));
		item.setMnemonic(KeyEvent.VK_N);
		item.setActionCommand("new");
		item.addActionListener(this);
		file.add(item);
		item = new JMenuItem("Ouvrir",
				     UIManager.getIcon("FileView.directoryIcon"));
		item.setMnemonic(KeyEvent.VK_O);
		item.setActionCommand("open");
		item.addActionListener(this);
		file.add(item);
		item = new JMenuItem("Enregistrer",
				     UIManager.getIcon("FileView.floppyDriveIcon"));
		item.setMnemonic(KeyEvent.VK_S);
		item.setActionCommand("save");
		item.addActionListener(this);
		file.add(item);
		file.addSeparator();
		item = new JMenuItem("Quitter", KeyEvent.VK_Q);
		item.setActionCommand("quit");
		item.addActionListener(this);
		file.add(item);

		// Edition
		edit.setMnemonic(KeyEvent.VK_E);
		item = new JMenuItem("Annuler", KeyEvent.VK_A);
		item.setActionCommand("undo");
		item.addActionListener(this);
		edit.add(item);
		item = new JMenuItem("Refaire", KeyEvent.VK_R);
		item.setActionCommand("redo");
		item.addActionListener(this);
		edit.add(item);
		edit.addSeparator();
		item = new JMenuItem("Copier", KeyEvent.VK_C);
		item.setActionCommand("copy");
		item.addActionListener(this);
		edit.add(item);
		item = new JMenuItem("Coller", KeyEvent.VK_V);
		item.setActionCommand("paste");
		item.addActionListener(this);
		edit.add(item);

		// Aide
		help.setMnemonic(KeyEvent.VK_H);
		item = new JMenuItem("Conseil du jour");
		item.setActionCommand("tipofday");
		item.addActionListener(this);
		help.add(item);
		item = new JMenuItem("Apropos",
				     UIManager.getIcon("OptionPane.informationIcon"));
		item.setMnemonic(KeyEvent.VK_A);
		item.setActionCommand("about");
		item.addActionListener(this);
		help.add(item);
		item = new JMenuItem("Aide",
				     UIManager.getIcon("OptionPane.questionIcon"));
		item.setMnemonic(KeyEvent.VK_H);
		help.addSeparator();
		help.add(item);

		bar.add(file);
		bar.add(edit);
		bar.add(help);

		return bar;
	}

	public void create_frame() {
		frame = new JFrame("PaintFactoryManager");
		drawarea      = new AireDeDessin(500, 200);
		trace_manager = new TraceManager(drawarea, new Pencil(10), new Eraser(10));
		listener      = new EcouteurDevenements(trace_manager, drawarea);

		drawarea.addMouseListener(listener);
		drawarea.addMouseMotionListener(listener);
		drawarea.addComponentListener(drawarea);

		frame.add(drawarea);
		frame.setJMenuBar(create_menubar());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 200);
		frame.setVisible(true);
	}

	public void run() {
		create_frame();
	}
	
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();

		if (command == "quit") {
			frame.dispose();
		} else if (command == "about") {
			show_about();
		} else if (command == "tipofday") {
			show_tipofday();
		} else if (command == "undo") {
			trace_manager.undo();
		} else if (command == "redo") {
			trace_manager.redo();
		} else if (command == "new") {
			drawarea.clear();
			drawarea.repaint();
		} else if (command == "save") {
			save();
		} else if (command == "open") {
			open();
		} else if (command == "copy") {
			listener.set_mode(EcouteurDevenements.InputMode.COPY);
		} else if (command == "paste") {
			listener.set_mode(EcouteurDevenements.InputMode.PASTE);
		}
	}

	public void menuSelected(MenuEvent e) {
		show_about();
	}

	// Ignore les auters evenement de MenuListener
	public void menuDeselected(MenuEvent e) {}
	public void menuCanceled(MenuEvent e) {}
}

package mvc;

import JPanels.*;
import javax.swing.*;
import java.awt.BorderLayout;

public class View extends JFrame {

	private Model model;
	private Controller controller;

	private Icon icon;

	public View() {

		model = new Model();
		this.model = model;
		model.setView(this);

		controller = new Controller(model);
		this.controller = controller;

		MenuAndOptions menuAndOptions = new MenuAndOptions();
		add(menuAndOptions, BorderLayout.NORTH);

		ImagePanel imagePanel = new ImagePanel();
		JScrollPane imageScrollPane = new JScrollPane(imagePanel);
		add(imageScrollPane, BorderLayout.CENTER);

		Categories categories = new Categories();
		add(categories, BorderLayout.EAST);

		setName("Inlupp2");
		setSize(800, 800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	public void update() {



	}

	public static void main(String[] args) {

		javax.swing.SwingUtilities.invokeLater(new Runnable() {

			public void run() {

				View view = new View();

				view.setVisible(true);

			}

		});

	}

}
package mvc;

import JPanels.MainContentPane;
import javax.swing.*;

public class View extends JFrame {

	private Model model;
	private Controller controller;

	public View() {

		model = new Model();
		this.model = model;
		model.setView(this);

		controller = new Controller(model);
		this.controller = controller;

		setContentPane(new MainContentPane());

		setName("Inlupp2");
		setSize(1000, 850);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		//pack();

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
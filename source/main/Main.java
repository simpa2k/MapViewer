package main;

import mvc.View;

public class Main {
	
	public static void main(String[] args) {

		javax.swing.SwingUtilities.invokeLater(new Runnable() {

			public void run() {

				View view = new View();

				view.setVisible(true);

			}

		});

	}

}

package mvc;

import JPanels.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.io.*;

public class View extends JFrame {

	private Model model;
	private Controller controller;

	private ImagePanel imagePanel;

	public View() {

		model = new Model();
		this.model = model;
		model.setView(this);

		controller = new Controller(model);
		this.controller = controller;

		MenuAndOptions menuAndOptions = new MenuAndOptions(controller);
		add(menuAndOptions, BorderLayout.NORTH);

		Categories categories = new Categories(controller);
		add(categories, BorderLayout.EAST);

		setName("Inlupp2");
		setSize(800, 800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	private void instantiateImagePanel() {

		imagePanel = new ImagePanel();
		JScrollPane imageScrollPane = new JScrollPane(imagePanel);
		add(imageScrollPane, BorderLayout.CENTER);

	}

	public void updateMap() {

		if(imagePanel == null) {

			instantiateImagePanel();

		}

		imagePanel.setMap(model.getMapFile());
		revalidate();

	}

	public void updatePlaces() {

		if(imagePanel == null) {

			instantiateImagePanel();

		}

		imagePanel.setPlaces(model.getPlacesFile());
		revalidate();

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
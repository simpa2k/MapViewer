package mvc;

import controllers.Controller;
import places.*;

import jPanels.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.io.*;

public class View extends JFrame {

	private Model model;
	private Controller controller;

	private ImagePanel imagePanel;
	private Categories categories;

	public View() {

		model = new Model();
		this.model = model;
		model.setView(this);

		controller = new Controller(model, this);
		this.controller = controller;

		MenuAndOptions menuAndOptions = new MenuAndOptions(controller);
		add(menuAndOptions, BorderLayout.NORTH);

		categories = new Categories(controller);
		add(categories, BorderLayout.EAST);

		setName("Inlupp2");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		pack();
	}

	private void instantiateImagePanel() {

		imagePanel = new ImagePanel();
		JScrollPane imageScrollPane = new JScrollPane(imagePanel);
		add(imageScrollPane, BorderLayout.CENTER);

	}

	public ImagePanel getImagePanel() {

		return imagePanel;

	}

	public void updateMap() {

		if(imagePanel == null) {

			instantiateImagePanel();

		}

		imagePanel.setMap(model.getMapFile());

		pack();
		repaint();

	}

	public void updatePlaces() {

		imagePanel.drawPlaces(model.getPlaces());

		repaint();

	}

	public void drawPlace(Position position) {

		imagePanel.drawPlace(model.getPlace(position));

	}

	public String getSelectedCategory() {

		return categories.getSelectedCategory();

	}


}

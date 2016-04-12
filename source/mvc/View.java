package mvc;

import mediator.Mediator;
import places.*;

import jPanels.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.io.*;

public class View extends JFrame {

	private Model model;
	private Mediator mediator;

	private ImagePanel imagePanel;
	private Categories categories;

	public View() {

		model = new Model();
		this.model = model;
		model.setView(this);

		mediator = new Mediator(model, this);
		this.mediator = mediator;

		MenuAndOptions menuAndOptions = new MenuAndOptions(this, mediator);
		add(menuAndOptions, BorderLayout.NORTH);

		categories = new Categories(mediator, this);
		add(categories, BorderLayout.EAST);

		setName("Inlupp2");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		pack();
	}

	private void instantiateImagePanel() {

		imagePanel = new ImagePanel();
		JScrollPane imageScrollPane = new JScrollPane(imagePanel);
		imageScrollPane.setBorder(null);

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
		validate();
		repaint();

	}

	public void updatePlaces() {

		imagePanel.drawPlaces(model.getPlaces());

		repaint();

	}

	public void drawPlace(Position position) {

		imagePanel.drawPlace(model.getPlace(position));
		imagePanel.repaint();

	}

	public String getSelectedCategory() {

		return categories.getSelectedCategory();

	}

	public Model getModel() {
		
		return model;

	}


}

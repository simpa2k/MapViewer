package controllers;

import mvc.*;
import JPanels.ImagePanel;
import places.*;

import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Controller {

	private Model model;
	private View view;

	private final JFileChooser fileChooser = new JFileChooser("../");

	private NewPlaceController newPlaceController;
	private WhatIsHereController whatIsHereController;

	public Controller(Model model, View view) {

		this.model = model;
		this.view = view;

	}

	private File openFileDialog() {

		int okOrCancel = fileChooser.showOpenDialog(view);

		if(okOrCancel == JFileChooser.APPROVE_OPTION) {

			return fileChooser.getSelectedFile();

		}
		return null;

	}

	public void openMap() {
		
		File mapFile = openFileDialog();

		if(mapFile != null) {

			model.setMapFile(mapFile);

		}

	}

	public void openPlaces() {

		if(view.getImagePanel() == null) {

			JOptionPane.showMessageDialog(view, "No map chosen", "No map", JOptionPane.ERROR_MESSAGE);
			return;
		}

		File placesFile = openFileDialog();

		if(placesFile != null) {
			
			model.loadPlaces(placesFile);

		}

	}

	//Se över namngivningen här - båda de två nedanstående lägger till lyssnare på kartan
	public void addMapListener(String selectedType) {

		ImagePanel mapPanel = view.getImagePanel();

		if(mapPanel != null) {

			mapPanel.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
			newPlaceController = new NewPlaceController(this, mapPanel, selectedType);

			mapPanel.addMouseListener(newPlaceController);
		}

	}

	public void addWhatIsHereController() {

		ImagePanel mapPanel = view.getImagePanel();

		if(mapPanel != null) {

			whatIsHereController = new WhatIsHereController(model, this);
			mapPanel.addMouseListener(whatIsHereController);

		}

	}

	public void removeWhatIsHereController() {

		ImagePanel mapPanel = view.getImagePanel();

		if(mapPanel != null) {

			mapPanel.removeMouseListener(whatIsHereController);

		}

	}

	protected void createPlace(int xPosition, int yPosition, String name, String description) {

		ImagePanel mapPanel = view.getImagePanel();

		if(mapPanel != null) {

			String type = description == null ? "Named" : "Described";
			String selectedCategory = view.getSelectedCategory();

			model.createPlace(selectedCategory, xPosition, yPosition, name, description);

			//Det här är inte världens snyggate lösning
			view.drawPlace(new Position(xPosition, yPosition));

			mapPanel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			mapPanel.removeMouseListener(newPlaceController);

		}

	}

	public void showCategory(ListSelectionEvent event, String selectedCategory) {

		if(!event.getValueIsAdjusting()) {

			model.setCategoryToVisible(selectedCategory, true);

		}

	}

	public void hideCategory(String selectedCategory) {

		model.setCategoryToVisible(selectedCategory, false);

	}

	//Borde inte det här ske i ExistingPlaceController?
	public void search(String query) {

		ArrayList<Place> placesByName = model.getPlacesByName(query);

		if(placesByName != null) {

			for(Place place : placesByName) {
			
				place.setVisible(true);
				place.setMarked(true);

				view.repaint();

			}

		}

	}

	public void hideSelectedPlaces() {

		model.getPlaces().forEach( (position, place) -> {

			if(place.getMarked()) {

				place.setVisible(false);

			}

		});

	}

	public void removeMarkedPlaces() {

		model.getPlaces().forEach( (position, place) -> {

			if(place.getMarked()) {

				view.getImagePanel().remove(place);

			}

		});

		model.removeMarkedPlaces();

		view.revalidate();
		view.repaint();

	}

}

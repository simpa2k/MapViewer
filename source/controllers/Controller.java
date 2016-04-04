package controllers;

import mvc.*;
import JPanels.ImagePanel;
import places.*;

import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

public class Controller {

	private Model model;
	private View view;

	private final JFileChooser fileChooser = new JFileChooser();

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

		File placesFile = openFileDialog();

		if(placesFile != null) {
			
			model.loadPlaces(placesFile);

		}

	}

	public void addMapListener(String selectedType) {

		ImagePanel mapPanel = view.getImagePanel();

		mapPanel.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		mapPanel.addMouseListener(new NewPlaceController(this, mapPanel, selectedType));

	}

	protected void createPlace(int xPosition, int yPosition, String name, String description) {

		String type = description == null ? "Named" : "Described";
		String selectedCategory = view.getSelectedCategory();

		model.createPlace(selectedCategory, xPosition, yPosition, name, description);

		//Det här är inte världens snyggate lösning
		view.drawPlace(new Position(xPosition, yPosition));

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

		for(Place place : model.getPlacesByName(query)) {

			place.setVisible(true);
			place.setMarked(true);

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
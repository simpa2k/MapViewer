package controllers;

import mvc.*;
import jPanels.ImagePanel;
import places.*;

import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;

import javax.swing.filechooser.*;
import javax.swing.filechooser.FileFilter;

public class Controller {

	private Model model;
	private View view;

	//Överväg att använda user.dir som argument till FileChooserkonstruktorn
	private final JFileChooser fileChooser = new JFileChooser("../");
	private FileFilter placesFilter = new FileNameExtensionFilter("Places", "places");
	private FileFilter imageFilter = new FileNameExtensionFilter("Images", "jpg", "gif", "png");

	private NewPlaceController newPlaceController;
	private WhatIsHereController whatIsHereController;

	public Controller(Model model, View view) {

		this.model = model;
		this.view = view;

	}

	private File getFile(int okOrCancel) {

		if(okOrCancel == JFileChooser.APPROVE_OPTION) {

			return fileChooser.getSelectedFile();

		}
		return null;

	}

	private File openFile() {

		int okOrCancel = fileChooser.showOpenDialog(view);
		File selectedFile = getFile(okOrCancel);
		
		return selectedFile;
	}

	public void openMap() {
		
		fileChooser.setFileFilter(imageFilter);
		File mapFile = openFile();

		if(mapFile != null) {

			model.setMapFile(mapFile);

		}

	}

	public void openPlaces() {

		if(view.getImagePanel() == null) {

			JOptionPane.showMessageDialog(view, "No map chosen", "No map", JOptionPane.ERROR_MESSAGE);
			return;
		}

		fileChooser.setFileFilter(placesFilter);
		File placesFile = openFile();

		if(placesFile != null) {
			
			model.loadPlaces(placesFile);

		}

	}

	public void savePlaces() {
		
		int okOrCancel = fileChooser.showSaveDialog(view);
		File saveFile = getFile(okOrCancel);

		if(saveFile != null) {
			
			model.savePlaces(saveFile.toPath());

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

		HashSet<Place> placesByName = model.getPlacesByName(query);

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

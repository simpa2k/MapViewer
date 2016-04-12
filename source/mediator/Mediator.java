package mediator;

import mvc.*;
import jPanels.ImagePanel;
import places.*;
import listeners.*;

import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;

import javax.swing.filechooser.*;
import javax.swing.filechooser.FileFilter;

public class Mediator {

	private Model model;
	private View view;

	//Överväg att använda user.dir som argument till FileChooserkonstruktorn
	private FileDialogHandler fileDialogHandler = new FileDialogHandler();
	private FileFilter placesFilter = new FileNameExtensionFilter("Places", "places");
	private FileFilter imageFilter = new FileNameExtensionFilter("Images", "jpg", "gif", "png");

	private NewPlaceListener newPlaceListener;
	private WhatIsHereListener whatIsHereListener;

	public Mediator(Model model, View view) {

		this.model = model;
		this.view = view;

	}

	public void openMap() {
		
		File mapFile = fileDialogHandler.openFile(view, imageFilter);		
		model.setMapFile(mapFile);

	}

	public void openPlaces() {

		if(view.getImagePanel() == null) {

			JOptionPane.showMessageDialog(view, "No map chosen", "No map", JOptionPane.ERROR_MESSAGE);
			return;
		}

		File placesFile = fileDialogHandler.openFile(view, placesFilter);
		model.loadPlaces(placesFile);

	}

	public void savePlaces() {
		
		File saveFile = fileDialogHandler.getSaveFile(view);
		if(saveFile != null) {

			model.savePlaces(saveFile.toPath());

		}
	}

 	
	private void setCrosshairCursor(JPanel panel) {

		panel.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));

	}

	private void setDefaultCursor(JPanel panel) {

		panel.setCursor(Cursor.getDefaultCursor());

	}

	//Se över namngivningen här - båda de två nedanstående lägger till lyssnare på kartan
	public void addMapListener(String selectedType) {

		ImagePanel mapPanel = view.getImagePanel();

		if( (mapPanel != null) && (mapPanel.getMouseListeners().length == 0) ) {

			setCrosshairCursor(mapPanel);
			
			newPlaceListener = new NewPlaceListener(this, mapPanel, selectedType);
			mapPanel.addMouseListener(newPlaceListener);

		}

	}
	
	public void removeMapListener() {

		ImagePanel mapPanel = view.getImagePanel();

		setDefaultCursor(mapPanel);
		mapPanel.removeMouseListener(newPlaceListener);

	}

	public void addWhatIsHereListener() {

		ImagePanel mapPanel = view.getImagePanel();

		if( (mapPanel != null) && (mapPanel.getMouseListeners().length == 0) ){
			
			setCrosshairCursor(mapPanel);

			whatIsHereListener = new WhatIsHereListener(model, this);
			mapPanel.addMouseListener(whatIsHereListener);

		}

	}

	public void removeWhatIsHereListener() {

		ImagePanel mapPanel = view.getImagePanel();

		if(mapPanel != null) {

			setDefaultCursor(mapPanel);
			mapPanel.removeMouseListener(whatIsHereListener);

		}

	}

	public void createPlace(int xPosition, int yPosition, String name, String description) {

		ImagePanel mapPanel = view.getImagePanel();

		if(mapPanel != null) {

			String type = description == null ? "Named" : "Described";
			String selectedCategory = view.getSelectedCategory();

			model.createPlace(selectedCategory, xPosition, yPosition, name, description);

			//Det här är inte världens snyggate lösning
			view.drawPlace(new Position(xPosition, yPosition));
			
			removeMapListener();

		}

	}

}

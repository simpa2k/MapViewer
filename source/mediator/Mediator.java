package mediator;

import mapPanel.*;
import mvc.View;
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

	private MapModel mapModel;
	private View view;

	private NewPlaceListener newPlaceListener;
	private WhatIsHereListener whatIsHereListener;

	public Mediator(MapModel mapModel, View view) {

		this.mapModel = mapModel;
		this.view = view;

	}
 	
	private void setCrosshairCursor(JPanel panel) {

		panel.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));

	}

	private void setDefaultCursor(JPanel panel) {

		panel.setCursor(Cursor.getDefaultCursor());

	}

	//Se över namngivningen här - båda de två nedanstående lägger till lyssnare på kartan
	public void addMapListener(String selectedType) {

		MapPanel mapPanel = view.getMapPanel();

		if( (mapPanel != null) && (mapPanel.getMouseListeners().length == 0) ) {

			setCrosshairCursor(mapPanel);
			
			newPlaceListener = new NewPlaceListener(this, mapPanel, selectedType, view.getSelectedCategory());
			mapPanel.addMouseListener(newPlaceListener);

		}

	}
	
	public void removeMapListener() {

		MapPanel mapPanel = view.getMapPanel();

		setDefaultCursor(mapPanel);
		mapPanel.removeMouseListener(newPlaceListener);

	}

	public void addWhatIsHereListener() {

		MapPanel mapPanel = view.getMapPanel();

		if( (mapPanel != null) && (mapPanel.getMouseListeners().length == 0) ){
			
			setCrosshairCursor(mapPanel);

			whatIsHereListener = new WhatIsHereListener(mapModel, this);
			mapPanel.addMouseListener(whatIsHereListener);

		}

	}

	public void removeWhatIsHereListener() {

		MapPanel mapPanel = view.getMapPanel();

		if(mapPanel != null) {

			setDefaultCursor(mapPanel);
			mapPanel.removeMouseListener(whatIsHereListener);

		}

	}

	public void createPlace(int xPosition, int yPosition, String name, String description) {

//			String type = description == null ? "Named" : "Described";
		String selectedCategory = view.getSelectedCategory();

		mapModel.addPlace(selectedCategory, xPosition, yPosition, name, description);
		
		removeMapListener();


	}

}

package mvc;

import mapPanel.*;
import mediator.Mediator;
import places.*;
import listeners.*;

import jPanels.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.io.*;

public class View extends JFrame {

	private MapModel mapModel;
	private MapPanel mapPanel;
	
	private Mediator mediator;

	private ExitListener exitListener; 
	private JMenuItem loadPlaces;
	private JMenuItem save;

	
	private ControlPanel controlPanel;
	private Categories categories;

	public View() {

		addMapPanel();

		mediator = new Mediator(mapModel, this);
		this.mediator = mediator;
		
		exitListener = new ExitListener(mapModel, this);
		addMenuBar();

		controlPanel = new ControlPanel(this, mediator);
		add(controlPanel, BorderLayout.NORTH);

		categories = new Categories(mediator, this);
		add(categories, BorderLayout.EAST);

		addWindowListener(exitListener);

		setName("Inlupp2");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		pack();
	}

	private void addMapPanel() {

		mapModel = new MapModel();
		mapPanel = new MapPanel(mapModel);

		JScrollPane mapScrollPane = new JScrollPane(mapPanel);
		mapScrollPane.setBorder(null);
		
		add(mapScrollPane, BorderLayout.CENTER);
		
//		makeMenuItemsEnabled();	
	}

	private void addMenuBar() {
		
		FileDialogHandler fileDialogHandler = new FileDialogHandler(mapModel, this);
				
		JMenuBar menuBar = new JMenuBar();
		JMenu archive = new JMenu("Archive");

		menuBar.add(archive);

		JMenuItem newMap = new JMenuItem("New Map");
		newMap.addActionListener(event -> fileDialogHandler.openMap());

		loadPlaces = new JMenuItem("Load Places");
		loadPlaces.addActionListener(event -> fileDialogHandler.loadPlaces());
		loadPlaces.setEnabled(false);

		save = new JMenuItem("Save");
		save.addActionListener(event -> fileDialogHandler.savePlaces());
		save.setEnabled(false);

		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(exitListener);

		archive.add(newMap);
		archive.add(loadPlaces);
		archive.add(save);
		archive.add(exit);

		setJMenuBar(menuBar);

	}

	public void makeMenuItemsEnabled() {
		
		loadPlaces.setEnabled(true);
		save.setEnabled(true);		

	}


	public MapPanel getMapPanel() {

		return mapPanel;

	}

	public void drawPlace(Position position) {

		mapPanel.drawPlace(mapModel.getPlace(position));
		mapPanel.repaint();

	}

	public String getSelectedCategory() {

		return categories.getSelectedCategory();

	}

	public MapModel getModel() {
		
		return mapModel;

	}


}

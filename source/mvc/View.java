package mvc;

import mediator.Mediator;
import places.*;
import listeners.*;

import jPanels.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.io.*;

public class View extends JFrame {

	private Model model;
	private Mediator mediator;

	JMenuItem loadPlaces;
	JMenuItem save;

	private ImagePanel imagePanel;
	private ControlPanel controlPanel;
	private Categories categories;

	public View() {

		model = new Model();
		this.model = model;
		model.setView(this);

		mediator = new Mediator(model, this);
		this.mediator = mediator;
		
		addMenuBar();

		controlPanel = new ControlPanel(this, mediator);
		add(controlPanel, BorderLayout.NORTH);

		categories = new Categories(mediator, this);
		add(categories, BorderLayout.EAST);

		setName("Inlupp2");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		pack();
	}

	private void addMenuBar() {
		
		FileDialogHandler fileDialogHandler = new FileDialogHandler(model, this);
				
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
		exit.addActionListener(new ExitListener(model, this));

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

	private void instantiateImagePanel() {

		imagePanel = new ImagePanel();
		JScrollPane imageScrollPane = new JScrollPane(imagePanel);
		imageScrollPane.setBorder(null);
		
		add(imageScrollPane, BorderLayout.CENTER);
		
		makeMenuItemsEnabled();	
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

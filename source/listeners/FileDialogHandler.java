package listeners;

import mapPanel.MapModel;
import mvc.View;

import java.io.*;
import javax.swing.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;

public class FileDialogHandler {
	
	private JFileChooser fileChooser = new JFileChooser("../");

	private FileFilter imageFilter = new FileNameExtensionFilter("Images", "jpg", "gif", "png");
	private FileFilter placesFilter = new FileNameExtensionFilter("Places", "places");

	private MapModel mapModel;
	private View window;

	public FileDialogHandler(MapModel mapModel, View window) {

		this.mapModel = mapModel;
		this.window = window;

	}
	private File getFile(int okOrCancel) {
	
		if(okOrCancel == JFileChooser.APPROVE_OPTION) {
			
			return fileChooser.getSelectedFile();

		}
		return null;

	}

	private File openFile() {
		
		int okOrCancel = fileChooser.showOpenDialog(window);
		return getFile(okOrCancel);

	}


	public void openMap() {

		fileChooser.setFileFilter(imageFilter);
		File mapFile = openFile();

		if(mapFile == null) {

			return;

		}

		try {	

			mapModel.setMapFile(mapFile);

			window.pack();
			window.validate();
			window.makeMenuItemsEnabled();

		} catch(IOException e) {

			JOptionPane.showMessageDialog(window, "Error: " + e.getMessage());

		} 


	}
	
	public void loadPlaces() {
		
		fileChooser.setFileFilter(placesFilter);
		File placesFile = openFile();

		if(placesFile == null) {

			return;

		}

		try {

			mapModel.loadPlaces(placesFile);

		} catch(IOException e) {

			JOptionPane.showMessageDialog(window, "Error: " + e.getMessage());

		} 

	}

	public void savePlaces() {

		fileChooser.setFileFilter(placesFilter);
		
		int okOrCancel = fileChooser.showSaveDialog(window);		
		File saveFile = getFile(okOrCancel);

		try {

			mapModel.savePlaces(saveFile);

		} catch(FileNotFoundException e) {

			JOptionPane.showMessageDialog(window, "Could not open file.");

		} catch(IOException e) {
			
			JOptionPane.showMessageDialog(window, "Error: " + e.getMessage());
			

		}

	}

}

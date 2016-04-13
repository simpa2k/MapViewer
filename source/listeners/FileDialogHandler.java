package listeners;

import mvc.*;

import java.io.File;
import javax.swing.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;

public class FileDialogHandler {
	
	private JFileChooser fileChooser = new JFileChooser("../");

	private FileFilter imageFilter = new FileNameExtensionFilter("Images", "jpg", "gif", "png");
	private FileFilter placesFilter = new FileNameExtensionFilter("Places", "places");

	private Model model;
	private View window;

	public FileDialogHandler(Model model, View window) {

		this.model = model;
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

		if(mapFile != null) {
			
			model.setMapFile(mapFile);

		}

	}
	
	public void loadPlaces() {
		
		fileChooser.setFileFilter(placesFilter);
		File placesFile = openFile();

		if(placesFile != null) {

			model.loadPlaces(placesFile);

		}

	}

	public void savePlaces() {

		fileChooser.setFileFilter(placesFilter);
		
		int okOrCancel = fileChooser.showSaveDialog(window);		
		File saveFile = getFile(okOrCancel);

		if(saveFile != null) {

			model.savePlaces(saveFile);
			model.setChanged(false);

		
		}

	}

}

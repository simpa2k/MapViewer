package listeners;

import mvc.Model;

import java.io.File;
import java.awt.event.*;
import javax.swing.filechooser.*;
import javax.swing.filechooser.FileFilter;

public class PlaceOpener implements ActionListener {

	private Model model;
	private FileFilter placesFilter = new FileNameExtensionFilter("Places", "places");

	public PlaceOpener(Model model) {

		this.model = model;

	}

	public void actionPerformed(ActionEvent e) {

		FileDialogHandler fileDialogHandler = new FileDialogHandler();
		File placesFile = fileDialogHandler.openFile(null, placesFilter);

		if(placesFile != null) {

			model.loadPlaces(placesFile);

		}

	}

}


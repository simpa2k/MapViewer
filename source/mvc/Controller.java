package mvc;

import javax.swing.*;
import javax.swing.event.*;
import java.io.*;

public class Controller {

	private Model model;

	private final JFileChooser fileChooser = new JFileChooser();

	public Controller(Model model) {

		this.model = model;

	}

	private File openFileDialog(JPanel eventFiringPanel) {

		int okOrCancel = fileChooser.showOpenDialog(eventFiringPanel);

		if(okOrCancel == JFileChooser.APPROVE_OPTION) {

			return fileChooser.getSelectedFile();

		}
		return null;

	}

	public void openMap(JPanel eventFiringPanel) {

		File mapFile = openFileDialog(eventFiringPanel);

		if(mapFile != null) {

			model.setMapFile(mapFile);

		}

	}

	public void openPlaces(JPanel eventFiringPanel) {

		File placesFile = openFileDialog(eventFiringPanel);

		if(placesFile != null) {
			
			model.loadPlaces(placesFile);

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

}
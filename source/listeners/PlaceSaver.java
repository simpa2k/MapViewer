package listeners;

import mvc.Model;

import java.io.File;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import javax.swing.filechooser.FileFilter;

public class PlaceSaver implements ActionListener {

	private Model model;
//	private FileFilter placesFilter = new FileNameExtensionFilter("Places", "places");

	public PlaceSaver(Model model) {

		this.model = model;

	}

	public void actionPerformed(ActionEvent e) {

		FileDialogHandler fileDialogHandler = new FileDialogHandler();
		
		//Displaying some confirmation that the save proceeded successfully
		//would also be nice
		File saveFile = fileDialogHandler.getSaveFile(null, null);	


		model.savePlaces(saveFile);
		model.setChanged(false);


	}

}

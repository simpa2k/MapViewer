package listeners;

import mvc.Model;

import java.io.File;
import java.awt.event.*;

public class PlaceSaver implements ActionListener {

	private Model model;

	public PlaceSaver(Model model) {

		this.model = model;

	}

	public void actionPerformed(ActionEvent e) {

		FileDialogHandler fileDialogHandler = new FileDialogHandler();
		
		//This dialog should also be provided a FileFilter, really
		//Displaying some confirmation that the save proceeded successfully
		//would also be nice
		File saveFile = fileDialogHandler.getSaveFile(null);	

		if(saveFile != null) {

			model.savePlaces(saveFile.toPath());

		}
		
	}

}

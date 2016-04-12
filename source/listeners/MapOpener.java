package listeners;

import mvc.Model;
import jPanels.MenuAndOptions;

import java.awt.event.*;
import java.io.*;
import javax.swing.filechooser.*;
import javax.swing.filechooser.FileFilter;

public class MapOpener implements ActionListener {

	private Model model;
	private FileFilter imageFilter = new FileNameExtensionFilter("Images", "jpg", "gif", "png");

	public MapOpener(Model model) {

		this.model = model;

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		FileDialogHandler fileDialogHandler = new FileDialogHandler();
		
		File mapFile = fileDialogHandler.openFile(null, imageFilter);

		if(mapFile != null) {
			
			model.setMapFile(mapFile);

		}
	}
}

package listeners;

import java.io.File;
import javax.swing.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;

public class FileDialogHandler {
	
	private JFileChooser fileChooser = new JFileChooser("../");

	private File getFile(int okOrCancel) {
	
		if(okOrCancel == JFileChooser.APPROVE_OPTION) {

			return fileChooser.getSelectedFile();

		}
		return null;

	}

	public File openFile(JFrame window, FileFilter fileFilter) {
		
		if(fileFilter != null) {

			fileChooser.setFileFilter(fileFilter);
			
		}

		int okOrCancel = fileChooser.showOpenDialog(window);
		return getFile(okOrCancel);
	}

	public File getSaveFile(JFrame window, FileFilter fileFilter) {

		if(fileFilter != null) {

			fileChooser.setFileFilter(fileFilter);
			
		}
		
		int okOrCancel = fileChooser.showSaveDialog(window);		
		return getFile(okOrCancel);
		
	}
}

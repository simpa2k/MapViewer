package mediator;

import java.io.File;
import javax.swing.JFrame;
import javax.swing.JFileChooser;
import javax.swing.filechooser.*;
import javax.swing.filechooser.FileFilter;

public class FileDialogHandler {
	
	private final JFileChooser fileChooser = new JFileChooser("../");

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

	public File getSaveFile(JFrame window) {
		
		int okOrCancel = fileChooser.showSaveDialog(window);

		return getFile(okOrCancel);
		
	}
}

package listeners;

import mvc.Model;

import javax.swing.*;
import java.awt.Component;
import java.awt.event.*;

public class ExitListener implements ActionListener {

	private Model model;
	private Component eventFiringComponent;

	public ExitListener(Model model, Component eventFiringComponent) {

		this.model = model;
		this.eventFiringComponent = eventFiringComponent;

	}
	
	public void actionPerformed(ActionEvent e) {

		if(model.getChanged()) {

			int okOrCancel = JOptionPane.showConfirmDialog(eventFiringComponent, "You have unsaved changes, exit anyway?",
								       "Unsaved changes", JOptionPane.OK_CANCEL_OPTION);

			if(okOrCancel == JOptionPane.OK_OPTION) {

				System.exit(0);

			}

		} else {

			System.exit(0);

		}	

	}

}

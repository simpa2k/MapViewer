package listeners;

import mediator.Mediator;
import mapPanel.MapPanel;
import dialogs.*;

import java.awt.event.*;
import javax.swing.*;

public class NewPlaceListener extends MouseAdapter {

	private Mediator mediator;
	private MapPanel mapPanel;
	private String selectedType;

	public NewPlaceListener(Mediator mediator, MapPanel mapPanel, String selectedType) {

		this.mediator = mediator;
		this.mapPanel = mapPanel;
		this.selectedType = selectedType;

	}

	private NamedPlaceDialog determineDialog() {

		switch(selectedType) {

			case "Named":
				return new NamedPlaceDialog();
			case "Described":
				return new DescribedPlaceDialog();
		}
		return null;

	}

	private void getInput(MouseEvent e, NamedPlaceDialog placeDialog) {

		switch(selectedType) {

			case "Named":
				mediator.createPlace(e.getX(), e.getY(), placeDialog.getNameInput(), null);
				break;
			case "Described":
				DescribedPlaceDialog describedPlaceDialog = (DescribedPlaceDialog) placeDialog;
				mediator.createPlace(e.getX(), e.getY(), describedPlaceDialog.getNameInput(), describedPlaceDialog.getDescriptionInput());
				break;

		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {

		NamedPlaceDialog placeDialog = determineDialog();

		int okOrCancel = JOptionPane.showOptionDialog(mapPanel, 
							placeDialog, 
							"New " + selectedType + " place", 
							JOptionPane.OK_CANCEL_OPTION, 
							JOptionPane.QUESTION_MESSAGE, 
							null, null, null);

		if(okOrCancel == JOptionPane.OK_OPTION) {

			getInput(e, placeDialog);

		} else {
				
			mediator.removeMapListener();

		}
	}
}

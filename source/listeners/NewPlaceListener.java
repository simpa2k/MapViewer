package listeners;

import mediator.Mediator;
import mapPanel.MapPanel;
import dialogs.*;
import places.*;

import java.awt.event.*;
import javax.swing.*;

public class NewPlaceListener extends MouseAdapter {

	private Mediator mediator;
	private MapPanel mapPanel;
	private String selectedType;
	private String selectedCategory;

	public NewPlaceListener(Mediator mediator, MapPanel mapPanel, String selectedType, String selectedCategory) {

		this.mediator = mediator;
		this.mapPanel = mapPanel;
		this.selectedType = selectedType;
		this.selectedCategory = selectedCategory;

	}

	private NamedPlaceDialog determineDialog(Position position) {

		switch(selectedType) {

			case "Named":
				return new NamedPlaceDialog(selectedCategory,position);
			case "Described":
				return new DescribedPlaceDialog(selectedCategory, position);
		}
		return null;

	}

	@Override
	public void mouseClicked(MouseEvent e) {

		NamedPlaceDialog placeDialog = determineDialog(new Position(e.getX(), e.getY()));

		int okOrCancel = JOptionPane.showOptionDialog(mapPanel, 
							placeDialog, 
							"New " + selectedType + " place", 
							JOptionPane.OK_CANCEL_OPTION, 
							JOptionPane.QUESTION_MESSAGE, 
							null, null, null);

		if(okOrCancel == JOptionPane.OK_OPTION) {

//			getInput(e, placeDialog);
			mediator.createPlace(placeDialog.getPlace());

		} else {
				
			mediator.removeMapListener();

		}
	}
}

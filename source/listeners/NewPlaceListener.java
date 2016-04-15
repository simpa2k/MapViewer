package listeners;

import mediator.Mediator;
import mapPanel.MapPanel;
import dialogs.*;
import places.*;
import jPanels.*;

import java.awt.event.*;
import javax.swing.*;

public class NewPlaceListener extends MouseAdapter {

	private Mediator mediator;
	private MapPanel mapPanel;
	private ControlPanel controlPanel;
	private Categories categories;

	public NewPlaceListener(Mediator mediator, MapPanel mapPanel, ControlPanel controlPanel, Categories categories) {

		this.mediator = mediator;
		this.mapPanel = mapPanel;
		this.controlPanel = controlPanel;
		this.categories = categories;
	}

	private NamedPlaceDialog determineDialog(String selectedType, Position position) {

		switch(selectedType) {

			case "Named":
				return new NamedPlaceDialog(categories.getSelectedCategory(),position);
			case "Described":
				return new DescribedPlaceDialog(categories.getSelectedCategory(), position);

		}
		return null;

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		String selectedType = controlPanel.getSelectedType();
		NamedPlaceDialog placeDialog = determineDialog(selectedType, new Position(e.getX(), e.getY()));

		int okOrCancel = JOptionPane.showOptionDialog(mapPanel, 
							placeDialog, 
							"New " + selectedType + " place", 
							JOptionPane.OK_CANCEL_OPTION, 
							JOptionPane.QUESTION_MESSAGE, 
							null, null, null);

		if(okOrCancel == JOptionPane.OK_OPTION) {

			mediator.createPlace(placeDialog.getPlace());

		} else {
				
			mediator.removeMapListener();

		}
	}
}

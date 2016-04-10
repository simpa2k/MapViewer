package controllers;

import jPanels.ImagePanel;
import dialogs.*;

import java.awt.event.*;
import javax.swing.*;

public class NewPlaceController implements MouseListener {

	private Controller parentController;
	private ImagePanel mapView;
	private String selectedType;

	public NewPlaceController(Controller parentController, ImagePanel mapView, String selectedType) {

		this.parentController = parentController;
		this.mapView = mapView;
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
				parentController.createPlace(e.getX(), e.getY(), placeDialog.getNameInput(), null);
				break;
			case "Described":
				DescribedPlaceDialog describedPlaceDialog = (DescribedPlaceDialog) placeDialog;
				parentController.createPlace(e.getX(), e.getY(), describedPlaceDialog.getNameInput(), describedPlaceDialog.getDescriptionInput());
				break;

		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {

		NamedPlaceDialog placeDialog = determineDialog();

		int okOrCancel = JOptionPane.showOptionDialog(mapView, 
													   placeDialog, 
													   "New " + selectedType + " place", 
													   JOptionPane.OK_CANCEL_OPTION, 
													   JOptionPane.QUESTION_MESSAGE, 
													   null, null, null);
		if(okOrCancel == JOptionPane.OK_OPTION) {

			getInput(e, placeDialog);

		}
	}

	@Override
	public void mousePressed(MouseEvent e) {



	}

	@Override
	public void mouseReleased(MouseEvent e) {



	}

	@Override
	public void mouseEntered(MouseEvent e) {



	}

	@Override
	public void mouseExited(MouseEvent e) {



	}

}

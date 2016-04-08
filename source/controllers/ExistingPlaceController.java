package controllers;

import places.*;
import JPanels.ImagePanel;

import javax.swing.*;
import java.awt.event.*;

public class ExistingPlaceController extends MouseAdapter {

	private Place model;

	public ExistingPlaceController(Place model) {

		this.model = model;

	}

	@Override
	public void mouseClicked(MouseEvent e) {

		if(SwingUtilities.isLeftMouseButton(e)) {
			
			model.setMarked();
			model.repaint();

		} else if(SwingUtilities.isRightMouseButton(e)) {

			model.setFolded();
			model.repaint();

		}

	}

}

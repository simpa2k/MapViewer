package controllers;

import places.*;
import JPanels.ImagePanel;

import javax.swing.*;
import java.awt.event.*;

public class ExistingPlaceController implements MouseListener {

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
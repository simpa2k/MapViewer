package controllers;

import mvc.Model;
import places.*;

import java.awt.event.*;

public class WhatIsHereController implements MouseListener {

	Model model;
	Controller parentController;

	public WhatIsHereController(Model model, Controller parentController) {

		this.model = model;
		this.parentController = parentController;

	}

	@Override
	public void mouseClicked(MouseEvent e) {

		Position clickedPosition = new Position(e.getX(), e.getY());
		System.out.println("x: " + e.getX()  + " y: " + e.getY());
		Place place = model.searchAreaAroundPosition(clickedPosition);

		if(place != null) {

			System.out.println("Hit!");
			place.setVisible(true);
			place.repaint();

		}

		parentController.removeWhatIsHereController();
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

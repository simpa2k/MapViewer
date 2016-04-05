package controllers;

import mvc.Model;
import places.*;

import java.awt.event.*;

public class WhatIsHereController implements MouseListener {

	Model model;

	public WhatIsHereController(Model model) {

		this.model = model;

	}

	@Override
	public void mouseClicked(MouseEvent e) {

		Position clickedPosition = new Position(e.getX(), e.getY());

		Place place = model.searchAreaAroundPosition(clickedPosition);

		if(place != null) {

			System.out.println("Hit!");
			place.setVisible(true);
			place.repaint();

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
package listeners;

import mediator.Mediator;
import mvc.Model;
import places.*;

import java.awt.event.*;

public class WhatIsHereListener extends MouseAdapter {

	Model model;
	Mediator mediator;

	public WhatIsHereListener(Model model, Mediator mediator) {

		this.model = model;
		this.mediator = mediator;

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

		mediator.removeWhatIsHereListener();
	}

}

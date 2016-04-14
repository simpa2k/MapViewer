package listeners;

import mediator.Mediator;
import mapPanel.MapModel;
import places.*;

import java.awt.event.*;

public class WhatIsHereListener extends MouseAdapter {

	private MapModel mapModel;
	private	Mediator mediator;

	public WhatIsHereListener(MapModel mapModel, Mediator mediator) {

		this.mapModel = mapModel;
		this.mediator = mediator;

	}

	@Override
	public void mouseClicked(MouseEvent e) {

		Position clickedPosition = new Position(e.getX(), e.getY());
		Place place = mapModel.searchAreaAroundPosition(clickedPosition);

		if(place != null) {

			place.setVisible(true);
			place.repaint();

		}

		mediator.removeWhatIsHereListener();
	}

}

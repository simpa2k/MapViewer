package listeners;

import mediator.Mediator;
import places.*;
import jPanels.ImagePanel;

import javax.swing.*;
import java.awt.event.*;

public class ExistingPlaceListener extends MouseAdapter {

	private Place model;

	public ExistingPlaceListener(Place model) {

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

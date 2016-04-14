package listeners;

import mapPanel.MapModel;
import places.Place;

import java.awt.event.*;
import java.util.function.*; 

public class VisibilityByMarkedSetter implements ActionListener {
	
	private MapModel mapModel; 

	public VisibilityByMarkedSetter(MapModel mapModel) {

		this.mapModel = mapModel;

	}

	public void actionPerformed(ActionEvent e) {
		
		mapModel.getPlaces().forEach((position, place) -> {	
	
			if(place.getMarked()) {

				place.setVisible(false);

			}

		});

	}

}

package listeners;

import mapPanel.MapModel;
import places.Place;

import java.awt.event.*;
import javax.swing.event.*;
import java.util.function.*; 

public class PlaceVisibilityModifier implements ActionListener, ListSelectionListener {
	
	private MapModel mapModel; 
	private Predicate<Place> predicate;
	boolean visible;

	public PlaceVisibilityModifier(MapModel mapModel, Predicate<Place> predicate, boolean visible) {

		this.mapModel = mapModel;
		this.predicate = predicate;
		this.visible = visible;
	}

	public void actionPerformed(ActionEvent e) {
		
		setVisibility();

	}

	public void valueChanged(ListSelectionEvent e) {
		
		if(!e.getValueIsAdjusting()) {

			setVisibility();

		}

	}	

	private void setVisibility() {
		
		mapModel.getPlaces().forEach((position, place) -> {	
	
			if(predicate.test(place)) {

				place.setVisible(visible);

			}

		});

	}
}

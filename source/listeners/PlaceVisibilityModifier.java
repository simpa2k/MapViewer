package listeners;

import mvc.Model;
import places.Place;

import java.awt.event.*;
import javax.swing.event.*;
import java.util.function.*; 

public class PlaceVisibilityModifier implements ActionListener, ListSelectionListener {
	
	private Model model; 
	private Predicate<Place> predicate;
	boolean visible;

	public PlaceVisibilityModifier(Model model, Predicate<Place> predicate, boolean visible) {

		this.model = model;
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
		
		model.getPlaces().forEach((position, place) -> {	
	
			if(predicate.test(place)) {

				place.setVisible(visible);

			}

		});

	}
}

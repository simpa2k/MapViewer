package listeners;

import mvc.Model;
import java.awt.event.*;

public class PlaceHider implements ActionListener {
	
	private Model model;

	public PlaceHider(Model model) {

		this.model = model;

	}

	public void actionPerformed(ActionEvent e) {

		model.getPlaces().forEach( (position, place) -> {

			if(place.getMarked()) {

				place.setVisible(false);

			}

		});

	}
	
}

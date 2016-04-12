package listeners;

import mvc.*;
import java.awt.event.*;

public class PlaceRemover implements ActionListener {

	private Model model;
	private View view;

	public PlaceRemover(Model model, View view) {

		this.model = model;
		this.view = view;

	}

	public void actionPerformed(ActionEvent e) {

		model.getPlaces().forEach( (position, place) -> {

			if(place.getMarked()) {

				view.getImagePanel().remove(place);

			}

		});

		model.removeMarkedPlaces();

		view.revalidate();
		view.repaint();

	}

}

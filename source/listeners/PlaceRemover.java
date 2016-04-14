package listeners;

import mapPanel.MapModel;
import mvc.*;
import java.awt.event.*;

public class PlaceRemover implements ActionListener {

	private MapModel mapModel;
	private View view;

	public PlaceRemover(MapModel mapModel, View view) {

		this.mapModel = mapModel;
		this.view = view;

	}

	public void actionPerformed(ActionEvent e) {

		mapModel.getPlaces().forEach( (position, place) -> {

			if(place.getMarked()) {

				view.getMapPanel().remove(place);

			}

		});

		mapModel.removeMarkedPlaces();

		view.revalidate();
		view.repaint();

	}

}

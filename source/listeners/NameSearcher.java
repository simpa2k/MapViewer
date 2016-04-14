package listeners;

import mapPanel.MapModel;
import mvc.View;

import places.Place;
import java.awt.event.*;
import java.util.HashSet;
import javax.swing.*;

public class NameSearcher implements ActionListener {

	private MapModel mapModel;
	private JTextField searchField;

	public NameSearcher(MapModel mapModel, JTextField searchField) {

		this.mapModel = mapModel;
		this.searchField = searchField;

	}

	public void actionPerformed(ActionEvent e) {
	
		HashSet<Place> placesByName = mapModel.getPlacesByName(searchField.getText());

		if(placesByName != null) {

			for(Place place : placesByName) {
			
				place.makeVisibleAndMarked();

			}

		}

	}
}

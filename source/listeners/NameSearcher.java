package listeners;

import mvc.*;
import jPanels.MenuAndOptions;

import places.Place;
import java.awt.event.*;
import java.util.HashSet;
import javax.swing.*;

public class NameSearcher implements ActionListener {

	private Model model;
	private JTextField searchField;

	public NameSearcher(Model model, JTextField searchField) {

		this.model = model;
		this.searchField = searchField;

	}

	public void actionPerformed(ActionEvent e) {
	
		HashSet<Place> placesByName = model.getPlacesByName(searchField.getText());

		if(placesByName != null) {

			for(Place place : placesByName) {
			
				place.makeVisibleAndMarked();

			}

		}

	}
}

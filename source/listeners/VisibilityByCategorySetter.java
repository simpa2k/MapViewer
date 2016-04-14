package listeners;

import mapPanel.MapModel;
import places.Place;

import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.JList;
import java.util.HashSet;

public class VisibilityByCategorySetter implements ActionListener, ListSelectionListener {

	private MapModel mapModel;
	private JList categoryList;
	private boolean visible;

	public VisibilityByCategorySetter(MapModel mapModel, JList categoryList, boolean visible) {

		this.mapModel = mapModel;
		this.categoryList = categoryList;
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
		
		HashSet<Place> places = mapModel.getPlacesByCategory((String) categoryList.getSelectedValue());
		
		for(Place place : places) {

			place.setVisible(visible);

		}
	}
}

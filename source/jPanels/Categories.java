package jPanels;

import mvc.*;
import mediator.Mediator;
import places.Place;
import listeners.VisibilityByCategorySetter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.function.*;

public class Categories extends JPanel {
	
	//Should these be generated dynamically from the actual places?
	//A list model will have to be used.
	String[] categories = {" ","Buss", "Tunnelbana", "Tåg"};
	private JList<String> categoryList; 

	public Categories(Mediator mediator, View parentFrame) {

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		JPanel centerPanel = new JPanel();

		centerPanel.setLayout(new BorderLayout());

		JLabel heading = new JLabel("Categories");
		centerPanel.add(heading, BorderLayout.NORTH);

		categoryList = new JList<>(categories);
		//You could store entire arraylists of places of a certain category as entries in the JList.
		//If you wanted to modify the places you could just do it for all places in the entire array.
		//Also check if it should be possible to select multiple values, or if one is sufficient.
		//The current layout managers don't allow setVisibleRowCount(). The assignment pictures suggest
		//that might be bad.
		categoryList.setSelectedIndex(0);
		categoryList.addListSelectionListener(new VisibilityByCategorySetter(parentFrame.getModel(), categoryList, true));
		centerPanel.add(categoryList, BorderLayout.CENTER);

		JButton hideCategory = new JButton("Hide category");
		hideCategory.addActionListener(new VisibilityByCategorySetter(parentFrame.getModel(), categoryList, false)); 
		centerPanel.add(hideCategory, BorderLayout.SOUTH);

		c.gridx = 0;
		c.gridy = 1;

		add(centerPanel, c);

	}

	public String getSelectedCategory() {

		return categoryList.getSelectedValue();

	}

}

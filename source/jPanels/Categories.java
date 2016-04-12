package jPanels;

import mvc.*;
import mediator.Mediator;
import places.Place;
import listeners.PlaceVisibilityModifier;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.function.*;

public class Categories extends JPanel {

	String[] categories = {"Buss", "Tunnelbana", "Tåg"};
	private JList categoryList; 

	public Categories(Mediator mediator, View parentFrame) {

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		JPanel centerPanel = new JPanel();

		centerPanel.setLayout(new BorderLayout());

		JLabel heading = new JLabel("Categories");
		centerPanel.add(heading, BorderLayout.NORTH);

		categoryList = new JList(categories);
		categoryList.addListSelectionListener(new PlaceVisibilityModifier(parentFrame.getModel(), matchesSelectedCategory(), true));
		centerPanel.add(categoryList, BorderLayout.CENTER);

		JButton hideCategory = new JButton("Hide category");
		hideCategory.addActionListener(new PlaceVisibilityModifier(parentFrame.getModel(), matchesSelectedCategory(), false)); 
		centerPanel.add(hideCategory, BorderLayout.SOUTH);

		c.gridx = 0;
		c.gridy = 1;

		add(centerPanel, c);

	}

	public String getSelectedCategory() {

		return categories[categoryList.getSelectedIndex()];

	}

	private Predicate<Place> matchesSelectedCategory() {
		
		return place -> place.getCategory().equals(getSelectedCategory());

	}

}

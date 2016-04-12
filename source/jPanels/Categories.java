package jPanels;

import mvc.*;
import mediator.Mediator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Categories extends JPanel {

	private Mediator mediator;

	String[] categories = {"Buss", "Tunnelbana", "Tåg"};
	private JList categoryList; 

	public Categories(Mediator mediator) {

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		JPanel centerPanel = new JPanel();

		centerPanel.setLayout(new BorderLayout());

		JLabel heading = new JLabel("Categories");
		centerPanel.add(heading, BorderLayout.NORTH);

		categoryList = new JList(categories);
		categoryList.addListSelectionListener(event ->  mediator.showCategory(event, categories[categoryList.getSelectedIndex()]));
		centerPanel.add(categoryList, BorderLayout.CENTER);

		JButton hideCategory = new JButton("Hide category");
		hideCategory.addActionListener(event -> mediator.hideCategory(categories[categoryList.getSelectedIndex()]));
		centerPanel.add(hideCategory, BorderLayout.SOUTH);

		c.gridx = 0;
		c.gridy = 1;

		add(centerPanel, c);

	}

	public String getSelectedCategory() {

		return categories[categoryList.getSelectedIndex()];

	}

}

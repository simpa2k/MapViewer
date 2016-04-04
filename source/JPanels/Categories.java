package JPanels;

import mvc.*;
import controllers.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Categories extends JPanel {

	private Controller controller;

	String[] categories = {"Buss", "Tunnelbana", "Tåg"};
	private JList categoryList; 

	public Categories(Controller controller) {

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		JPanel centerPanel = new JPanel();

		centerPanel.setLayout(new BorderLayout());

		JLabel heading = new JLabel("Categories");
		centerPanel.add(heading, BorderLayout.NORTH);

		categoryList = new JList(categories);
		categoryList.addListSelectionListener(event -> 
											  controller.showCategory(event, categories[categoryList.getSelectedIndex()]));
		centerPanel.add(categoryList, BorderLayout.CENTER);

		JButton hideCategory = new JButton("Hide category");
		hideCategory.addActionListener(event ->
									   controller.hideCategory(categories[categoryList.getSelectedIndex()]));
		centerPanel.add(hideCategory, BorderLayout.SOUTH);

		c.gridx = 0;
		c.gridy = 1;

		add(centerPanel, c);

	}

	public String getSelectedCategory() {

		return categories[categoryList.getSelectedIndex()];

	}

}
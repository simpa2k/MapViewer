package JPanels;

import mvc.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Categories extends JPanel {

	Controller controller;

	JList categoryList; 

	public Categories(Controller controller) {

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		JPanel centerPanel = new JPanel();

		centerPanel.setLayout(new BorderLayout());

		JLabel heading = new JLabel("Categories");
		centerPanel.add(heading, BorderLayout.NORTH);

		String[] categories = {"Buss", "Tunnelbana", "Tåg"};
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

}
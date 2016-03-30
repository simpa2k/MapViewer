package JPanels;

import javax.swing.*;
import java.awt.*;

public class Categories extends JPanel {

	JList categoryList; 

	public Categories() {

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		JPanel centerPanel = new JPanel();

		centerPanel.setLayout(new BorderLayout());

		JLabel heading = new JLabel("Categories");
		centerPanel.add(heading, BorderLayout.NORTH);

		String[] categories = {"Buss", "Tunnelbana", "Tåg"};
		categoryList = new JList(categories);
		centerPanel.add(categoryList, BorderLayout.CENTER);

		JButton hideCategory = new JButton("Hide category");
		centerPanel.add(hideCategory, BorderLayout.SOUTH);

		c.gridx = 0;
		c.gridy = 1;

		add(centerPanel, c);

	}

}
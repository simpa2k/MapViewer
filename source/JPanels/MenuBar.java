package JPanels;

import javax.swing.*;

public class MenuBar extends JPanel {

	public MenuBar() {

		JLabel createNew = new JLabel("New:");
		add(createNew);

		String[] typesOfPlaces = {"Named", "Described"};
		JComboBox namedOrDescribed = new JComboBox(typesOfPlaces);
		add(namedOrDescribed);

		JTextField searchField = new JTextField("Search", 10);
		add(searchField);

		JButton searchButton = new JButton("Search");
		add(searchButton);

		JButton hideButton = new JButton("Hide");
		add(hideButton);

		JButton removeButton = new JButton("Remove");
		add(removeButton);

		JButton whatIsHereButton = new JButton("What is here?");
		add(whatIsHereButton);

	}

}
package JPanels;

import mvc.*;
import controllers.Controller;

import javax.swing.*;
import java.awt.*;

public class MenuAndOptions extends JPanel {

	Controller controller;

	public MenuAndOptions(Controller controller) {

		this.controller = controller;

		setLayout(new GridLayout(0, 1));

		JMenuBar menuBar = new JMenuBar();
		JMenu archive = new JMenu("Archive");

		menuBar.add(archive);

		JMenuItem newMap = new JMenuItem("New Map");
		newMap.addActionListener(event -> controller.openMap());

		JMenuItem loadPlaces = new JMenuItem("Load Places");
		loadPlaces.addActionListener(event -> controller.openPlaces());

		JMenuItem save = new JMenuItem("Save");
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(event -> System.exit(0));

		archive.add(newMap);
		archive.add(loadPlaces);
		archive.add(save);
		archive.add(exit);

		add(menuBar);

		JPanel optionBar = new JPanel();

		JLabel createNew = new JLabel("New:");
		optionBar.add(createNew);

		String[] typesOfPlaces = {"Named", "Described"};
		JComboBox namedOrDescribed = new JComboBox(typesOfPlaces);
		namedOrDescribed.addActionListener(event -> controller.addMapListener(typesOfPlaces[namedOrDescribed.getSelectedIndex()]));
		optionBar.add(namedOrDescribed);

		JTextField searchField = new JTextField("Search", 10);
		optionBar.add(searchField);

		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(event -> controller.search(searchField.getText()));
		optionBar.add(searchButton);

		JButton hideButton = new JButton("Hide");
		hideButton.addActionListener(event -> controller.hideSelectedPlaces());
		optionBar.add(hideButton);

		JButton removeButton = new JButton("Remove");
		removeButton.addActionListener(event -> controller.removeMarkedPlaces());
		optionBar.add(removeButton);

		JButton whatIsHereButton = new JButton("What is here?");
		whatIsHereButton.addActionListener(event -> controller.addWhatIsHereController());
		optionBar.add(whatIsHereButton);

		add(optionBar);

	}

}

package JPanels;

import javax.swing.*;
import java.awt.*;

public class MenuAndOptions extends JPanel {

	public MenuAndOptions() {

		setLayout(new GridLayout(0, 1));

		JMenuBar menuBar = new JMenuBar();
		JMenu archive = new JMenu("Archive");

		menuBar.add(archive);

		JMenuItem newMap = new JMenuItem("New Map");
		JMenuItem loadPlaces = new JMenuItem("Load Places");
		JMenuItem save = new JMenuItem("Save");
		JMenuItem exit = new JMenuItem("Exit");

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
		optionBar.add(namedOrDescribed);

		JTextField searchField = new JTextField("Search", 10);
		optionBar.add(searchField);

		JButton searchButton = new JButton("Search");
		optionBar.add(searchButton);

		JButton hideButton = new JButton("Hide");
		optionBar.add(hideButton);

		JButton removeButton = new JButton("Remove");
		optionBar.add(removeButton);

		JButton whatIsHereButton = new JButton("What is here?");
		optionBar.add(whatIsHereButton);

		add(optionBar);

	}

}
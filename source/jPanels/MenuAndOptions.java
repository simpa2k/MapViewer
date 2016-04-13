package jPanels;

import mvc.*;
import mediator.Mediator;
import listeners.*;

import javax.swing.*;
import java.awt.*;

public class MenuAndOptions extends JPanel {

	Mediator mediator;
	View parentFrame;

	JMenuItem loadPlaces;
	JMenuItem save;

	JTextField searchField;

	public MenuAndOptions(View parentFrame, Mediator mediator) {
		
		this.parentFrame = parentFrame;
		this.mediator = mediator;

		setLayout(new GridLayout(0, 1));

		JMenuBar menuBar = new JMenuBar();
		JMenu archive = new JMenu("Archive");

		menuBar.add(archive);

		JMenuItem newMap = new JMenuItem("New Map");
		newMap.addActionListener(new MapOpener(parentFrame.getModel()));

		loadPlaces = new JMenuItem("Load Places");
		loadPlaces.addActionListener(new PlaceOpener(parentFrame.getModel()));
		loadPlaces.setEnabled(false);

		save = new JMenuItem("Save");
		save.addActionListener(new PlaceSaver(parentFrame.getModel()));
		save.setEnabled(false);

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
		namedOrDescribed.addActionListener(event -> mediator.addMapListener(typesOfPlaces[namedOrDescribed.getSelectedIndex()]));
		optionBar.add(namedOrDescribed);

		JTextField searchField = new JTextField("Search", 10);
		optionBar.add(searchField);

		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(new NameSearcher(parentFrame.getModel(), searchField));
		optionBar.add(searchButton);

		JButton hideButton = new JButton("Hide");
		hideButton.addActionListener(new PlaceVisibilityModifier(parentFrame.getModel(), (place -> place.getMarked()), false));
		optionBar.add(hideButton);

		JButton removeButton = new JButton("Remove");
		removeButton.addActionListener(new PlaceRemover(parentFrame.getModel(), parentFrame));
		optionBar.add(removeButton);

		JButton whatIsHereButton = new JButton("What is here?");
		whatIsHereButton.addActionListener(event -> mediator.addWhatIsHereListener());
		optionBar.add(whatIsHereButton);

		add(optionBar);

	}
	
	public void makeMenuItemsEnabled() {
		
		loadPlaces.setEnabled(true);
		save.setEnabled(true);		

	}
}

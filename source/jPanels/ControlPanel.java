package jPanels;

import mvc.*;
import mediator.Mediator;
import listeners.*;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {

	Mediator mediator;
	View parentFrame;

	JTextField searchField;

	public ControlPanel(View parentFrame, Mediator mediator) {
		
		this.parentFrame = parentFrame;
		this.mediator = mediator;

		setLayout(new GridLayout(0, 1));

		JPanel optionBar = new JPanel();

		JLabel createNew = new JLabel("New:");
		optionBar.add(createNew);

		String[] typesOfPlaces = {"Named", "Described"};
		JComboBox<String> namedOrDescribed = new JComboBox<>(typesOfPlaces);
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
	
}

package dialogs;

import javax.swing.*;

public class NamedPlaceDialog extends PlaceDialog {

	private JTextField nameInput;

	public NamedPlaceDialog(String category, Position position) {
		
		super(category, position);

		JLabel name = new JLabel("Name:");
		add(name);

		nameInput = new JTextField(10);
		add(nameInput);

	}

	protected String getNameInput() {

		return nameInput.getText();

	}

	@Override
	public NamedPlace getPlace() {

		return new NamedPlace(getCategory(), getPosition(), getNameInput());	

	}

}

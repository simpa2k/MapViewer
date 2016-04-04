package dialogs;

import javax.swing.*;

public class NamedPlaceDialog extends JPanel {

	private JTextField nameInput;

	public NamedPlaceDialog() {

		JLabel name = new JLabel("Name:");
		add(name);

		nameInput = new JTextField(10);
		add(nameInput);

	}

	public String getNameInput() {

		return nameInput.getText();

	}

}
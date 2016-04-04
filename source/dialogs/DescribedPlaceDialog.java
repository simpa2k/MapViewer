package dialogs;

import javax.swing.*;

public class DescribedPlaceDialog extends NamedPlaceDialog {

	JTextField descriptionInput;

	public DescribedPlaceDialog() {

		JLabel description = new JLabel("Description:");
		add(description);

		descriptionInput = new JTextField(10);
		add(descriptionInput);

	}

	public String getDescriptionInput() {

		return descriptionInput.getText();

	}

}
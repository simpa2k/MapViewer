package dialogs;

import places.*;
import javax.swing.*;

public class DescribedPlaceDialog extends NamedPlaceDialog {

	JTextField descriptionInput;

	public DescribedPlaceDialog(String category, Position position) {

		super(category, position);

		JLabel description = new JLabel("Description:");
		add(description);

		descriptionInput = new JTextField(10);
		add(descriptionInput);

	}

	protected String getDescriptionInput() {

		return descriptionInput.getText();

	}
	
	@Override
	public DescribedPlace getPlace() {
		
		return new DescribedPlace(getCategory(), getPosition(), getNameInput(), getDescriptionInput());

	}
}

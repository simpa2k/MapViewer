package dialogs;

import places.*;
import javax.swing.JPanel;

public abstract class PlaceDialog extends JPanel {

	private String category;
	private Position position;

	public PlaceDialog(String category, Position position) {

		this.category = category;
		this.position = position;

	}

	protected String getCategory() {

		return category;

	}

	protected Position getPosition() {

		return position;

	}

	public abstract Place getPlace();

}

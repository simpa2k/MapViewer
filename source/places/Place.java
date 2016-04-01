package places;

import javax.swing.JComponent;
import java.awt.Color;

public abstract class Place extends JComponent {

	private String category;
	private Position position;

	public Place(String category, Position position) {

		this.category = category;
		this.position = position;

	}

	protected Color resolveColor() {

		switch(category) {

			case "Buss":
				return Color.RED;
			case "Tunnelbana":
				return Color.BLUE;
			case "Tåg":
				return Color.GREEN;
			default:
				return Color.BLACK;

		}

	}

	public String getCategory() {

		return category;

	}

	public Position getPosition() {

		return position;

	}

}
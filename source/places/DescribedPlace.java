package places;

import java.awt.*;

public class DescribedPlace extends NamedPlace {

	private String description;

	public DescribedPlace(String category, 
						  Position position, 
						  String name, 
						  String description) {

		super(category, position, name);

		this.description = description;

	}

	@Override
	protected void displayAdditionalInfo(Graphics g) {

		super.displayAdditionalInfo(g);

		int descriptionWidth = g.getFontMetrics().stringWidth(description);
		int descriptionHeight = g.getFontMetrics().getHeight();

		int xPosition = getPosition().getX();
		int yPosition = getPosition().getY();

		int leftOffset = 30;

		setBounds(xPosition - 10, yPosition - 20, descriptionWidth + leftOffset, 50);

		g.drawString(description, leftOffset, descriptionHeight * 2);

	}

}
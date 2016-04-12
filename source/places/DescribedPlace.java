package places;

import java.awt.*;
import java.util.ArrayList;

public class DescribedPlace extends NamedPlace {

	private String description;

	public DescribedPlace(String category, 
			      Position position, 
			      String name, 
			      String description) {

		super(category, position, name);

		this.description = description;

	}
	
	public String getDescription() {

		return description;

	}

	@Override
	protected void paintAdditionalInfo(Graphics g) {

		super.paintAdditionalInfo(g);

		int descriptionWidth = g.getFontMetrics().stringWidth(description);
		int descriptionHeight = g.getFontMetrics().getHeight();

		int xPosition = getPosition().getX();
		int yPosition = getPosition().getY();

		int leftOffset = getTriangleWidth() + 10;

		setBounds(getX(), getY(), descriptionWidth + leftOffset, descriptionHeight * 3);

		g.drawString(description, leftOffset, descriptionHeight * 2);

	}

	@Override
	protected String getTypeAsString() {
		
		return "Described";

	}

	@Override
	protected ArrayList<String> getAdditionalProperties() {

		ArrayList<String> additionalProperties = super.getAdditionalProperties();
		additionalProperties.add(description);

		return additionalProperties;


	}

}

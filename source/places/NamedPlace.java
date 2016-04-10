package places;

import java.awt.*;
import java.util.ArrayList;

public class NamedPlace extends Place {

	private String name;

	public NamedPlace(String category, Position position, String name) {

		super(category, position);

		this.name = name;

	}

	public String getName() {

		return name;

	}

	@Override
	protected void paintAdditionalInfo(Graphics g) {

		int nameWidth = g.getFontMetrics().stringWidth(name);
		int nameHeight = g.getFontMetrics().getHeight();

		int xPosition = getPosition().getX();
		int yPosition = getPosition().getY();

		int leftOffset = 30;

		setBounds(xPosition - 10, yPosition - 20, nameWidth + leftOffset, 20);

		g.setColor(Color.BLACK);

		g.drawString(name, leftOffset, nameHeight);

	}

	@Override 
	protected String getTypeAsString() {
		
		return "Named";

	}

	@Override
	protected ArrayList<String> getAdditionalProperties() {

		ArrayList<String> additionalProperties = new ArrayList<>();
		additionalProperties.add(name);

		return additionalProperties;
	}

}

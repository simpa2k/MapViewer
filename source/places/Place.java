package places;

import javax.swing.JComponent;
import java.awt.*;

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

	public Color getColor() {

		return resolveColor();

	}

	public String getCategory() {

		return category;

	}

	public Position getPosition() {

		return position;

	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		g.setColor(resolveColor());

		int[] xPoints = {10, 0, 20};
		int[] yPoints = {20, 0, 0};
		int nPoints = 3;

		g.fillPolygon(xPoints, yPoints, nPoints);

	}

	@Override
	public Dimension getMinimumSize() {

		return new Dimension(200, 200);

	}

	@Override
	public Dimension getMaximumSize() {

		return new Dimension(200, 200);

	}

	@Override
	public Dimension getPreferredSize() {

		return new Dimension(200, 200);

	}

}
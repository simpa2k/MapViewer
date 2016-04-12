package places;

import javax.swing.*;
import java.awt.*;

public class Triangle extends JComponent {

	Color color;

	public Triangle(int width, int height, Color color) {

		setBounds(0, 0, width, height);
		this.color = color;

	}

	@Override
	protected void paintComponent(Graphics g) {

		int[] xPoints = {getWidth() / 2, 0, getWidth()};
		int[] yPoints = {getHeight(), 0, 0};
		int nPoints = 3;

		g.setColor(color);
		g.fillPolygon(xPoints, yPoints, nPoints);

	}

}

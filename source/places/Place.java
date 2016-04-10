package places;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public abstract class Place extends JComponent {

	private String category;
	private Position position;
	private boolean marked = false;
	private boolean foldedOut = false;

	public Place(String category, Position position) {

		this.category = category;
		this.position = position;

		setBounds(position.getX() - 10, position.getY() - 20, 20, 20);

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

	public void setMarked() {

		marked = !marked;

	}

	public void setMarked(boolean markedOrNot) {

		marked = markedOrNot;

	}

	public boolean getMarked() {

		return marked;

	}

	public void setFolded() {

		foldedOut = !foldedOut;

	}

	public boolean getFolded() {

		return foldedOut;

	}

	protected abstract void paintAdditionalInfo(Graphics g);

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		g.setColor(resolveColor());

		int[] xPoints = {10, 0, 20};
		int[] yPoints = {20, 0, 0};
		int nPoints = 3;

		g.fillPolygon(xPoints, yPoints, nPoints);

		if(marked) {

			g.setColor(Color.RED);
			g.drawRect(0, 0, 20, 20);

		}

		if(foldedOut) {
			
			paintAdditionalInfo(g);

		}

	}
	
	protected abstract String getTypeAsString();

	protected abstract ArrayList<String> getAdditionalProperties();

	@Override
	public String toString() {
		
		String toString = String.format("%s,%s,%s,",
				 		 getTypeAsString(),
				    		 category,
				    		 position);

		ArrayList<String> additionalProperties = getAdditionalProperties();

		for(String property : additionalProperties) {
			
			toString += property;

			if(additionalProperties.indexOf(property) != additionalProperties.size() - 1) {	

				toString += ",";
			
			}
		}		
		return toString;
	}
}

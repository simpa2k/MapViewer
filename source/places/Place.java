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

	private Polygon triangle;
	private Rectangle border; //Might it be better to actually use Border?


	public Place(String category, Position position) {

		this.category = category;
		this.position = position;

		setBounds(position.getX() - 10, position.getY() - 20, 20, 20);

		triangle = createTriangle(); 
		border = new Rectangle(new Dimension(getWidth(), getHeight()));

	}
	
	private Polygon createTriangle() {

		int[] xPoints = {getWidth() / 2, 0, getWidth()};
		int[] yPoints = {getHeight(), 0, 0};
		int nPoints = 3;

		return new Polygon(xPoints, yPoints, nPoints);


	}
	
	protected int getTriangleWidth() {

		return (int) triangle.getBounds().getWidth();

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

	public void makeVisibleAndMarked() {

		setVisible(true);
		setMarked(true);

	}

	public void setMarked() {

		marked = !marked;
		repaint();

	}

	public void setMarked(boolean markedOrNot) {

		marked = markedOrNot;
		repaint();

	}

	public boolean getMarked() {

		return marked;

	}

	public void setFolded() {

		foldedOut = !foldedOut;
		repaint();

	}

	public boolean getFolded() {

		return foldedOut;

	}

	protected abstract void paintAdditionalInfo(Graphics g);

	@Override
	protected void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(resolveColor());
		g2d.fill(triangle);;

		if(marked) {

			g2d.setColor(Color.RED);
			g2d.draw(border);

		}

		if(foldedOut) {
			
			paintAdditionalInfo(g2d);

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

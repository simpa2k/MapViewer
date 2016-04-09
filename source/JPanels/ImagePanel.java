package JPanels;

import places.*;

import java.util.HashMap;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImagePanel extends JLayeredPane {

	private Image image;
	
	public ImagePanel() {

		setLayout(null);

	}

	public void setMap(File mapFile) {

		try {
			
			image = ImageIO.read(mapFile);

		} catch(IOException e) {

			System.out.println("Map not found.");

		}

		this.setPreferredSize(new Dimension(image.getWidth(this), image.getHeight(this)));
		
	}

	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		g.drawImage(image, 0, 0, image.getWidth(this), image.getHeight(this), this);

	}

	public void drawPlaces(HashMap<Position, Place> places) {

		places.forEach( (position, place) -> {

			drawPlace(place);

		});

	}

	public void drawPlace(Place place) {

		add(place, new Integer(2));

	}

}

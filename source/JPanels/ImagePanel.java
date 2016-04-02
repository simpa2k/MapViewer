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

	private Icon icon;
	private Image image;

	public ImagePanel() {

		setLayout(null);

	}

	public void setMap(File mapFile) {

		try {
			
			image = ImageIO.read(mapFile);
			icon = new ImageIcon(image);

		} catch(IOException e) {

			System.out.println("Map not found.");

		}

		JLabel iconLabel = new JLabel(icon);
		
		this.setPreferredSize(new Dimension(image.getWidth(null), image.getHeight(null)));
		iconLabel.setBounds(0, 0, image.getWidth(null), image.getHeight(null));
		
		add(iconLabel, new Integer(1));

	}

	public void drawPlaces(HashMap<Position, Place> places) {

		places.forEach( (position, place) -> {

			place.setBounds(position.getX(), position.getY(), 20, 20);
			add(place, new Integer(2));

		});

	}

}
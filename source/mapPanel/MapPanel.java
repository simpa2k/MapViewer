package mapPanel;

import places.*;

import java.util.HashMap;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class MapPanel extends JPanel {
     	
       	private MapModel model;	
	private Image image;
	
	public MapPanel(MapModel model) {

		this.model = model;
		model.setView(this);
		setLayout(null);

	}

	public void setMap(File mapFile) {

		try {
			
			image = ImageIO.read(mapFile);

		} catch(IOException e) {

			System.out.println("Map not found.");

		}

		this.setPreferredSize(new Dimension(image.getWidth(this), image.getHeight(this)));
		repaint();		
	}

	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		if(image != null) {
	
			g.drawImage(image, 0, 0, image.getWidth(this), image.getHeight(this), this);

		}
	}

	public void drawPlace(Place place) {

		add(place); 
		repaint();

	}

}

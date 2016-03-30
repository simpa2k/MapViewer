package JPanels;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImagePanel extends JPanel {

	private Icon icon;

	public ImagePanel() {

		try {
			
			icon = new ImageIcon(getClass().getResource("jarvafaltet.png"));
		
		} catch (NullPointerException e) {

			System.out.println("Map not found.");

		} 

		JLabel iconLabel = new JLabel(icon);

		JScrollPane imageScrollPane = new JScrollPane(iconLabel);
		imageScrollPane.setAlignmentX(LEFT_ALIGNMENT);
		imageScrollPane.setPreferredSize(new Dimension(500, 600));

		add(imageScrollPane);

	}

}
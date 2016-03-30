package JPanels;

import javax.swing.*;
import java.awt.*;

public class MainContentPane extends JPanel {

	public MainContentPane() {

		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		MenuBar menuBar = new MenuBar();
		add(menuBar);

		ImagePanel imagePanel = new ImagePanel();
		add(imagePanel);

	}

}
package mvc;

import places.*;

import java.io.*;
import java.util.*;

public class Model {

	private View view;

	private File mapFile;
	private File placesFile;

	private HashMap<Position, Place> places = new HashMap<>();

	public void setView(View view) {

		this.view = view;

	}

	public void setMapFile(File mapFile) {

		this.mapFile = mapFile;

		view.updateMap();

	}

	public File getMapFile() {

		return mapFile;

	}

	private void instantiatePlace(String[] properties) {

		try {

			String namedOrDescribed = properties[0];
			String category = properties[1];
			int xPosition = Integer.parseInt(properties[2]);
			int yPosition = Integer.parseInt(properties[3]);
			String name = properties[4];

			switch(namedOrDescribed) {

				case "Named":
					NamedPlace namedPlace = new NamedPlace(category, 
														   new Position(xPosition, yPosition), 
														   name);

					places.put(namedPlace.getPosition(), namedPlace);
					break;
				case "Described":

					String description = properties[5];

					DescribedPlace describedPlace = new DescribedPlace(category, 
																	   new Position(xPosition, yPosition), 
																	   name,
																	   description);

					places.put(describedPlace.getPosition(), describedPlace);
					break;
			}

		} catch(IndexOutOfBoundsException e) {

			return;

		}

	}

	public void loadPlaces(File placesFile) {

		this.placesFile = placesFile;

		try {
			
			Scanner scanner = new Scanner(placesFile);

			while(scanner.hasNextLine()) {

				String line = scanner.nextLine();
				String[] properties = line.split(",");

				instantiatePlace(properties);
					
			}

		} catch(FileNotFoundException e) {

			System.out.println("Places file not found.");

		}

		//view.updatePlaces();

	}

	public File getPlacesFile() {

		return placesFile;

	}

	public void putPlace(Position position, Place place) {

		places.put(position, place);

	}

	public void setCategoryToVisible(String category, boolean trueOrFalse) {

		//places.forEach( place -> if(place.getCategory == category) {place.setVisible(trueOrFalse)} );
		
		places.forEach((position, place) -> {

			if(place.getCategory().equals(category)) {

				System.out.println(place.getName() + " " + place.getCategory());

			}

		});

	}

}
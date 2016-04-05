package mvc;

import places.*;
import controllers.ExistingPlaceController;

import java.io.*;
import java.util.*;

public class Model {

	private View view;

	private File mapFile;
	private File placesFile;

	private HashMap<Position, Place> places = new HashMap<>();
	private MultiMap<String, Place> placesByName = new MultiMap<>();

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

	public void createPlace(String category, int xPosition, int yPosition, String name, String description) {

		if(description == null) {
			
			NamedPlace namedPlace = new NamedPlace(category, 
												   new Position(xPosition, yPosition), 
												   name);

			namedPlace.addMouseListener(new ExistingPlaceController(namedPlace));
			places.put(namedPlace.getPosition(), namedPlace);
			placesByName.put(namedPlace.getName(), namedPlace);

		} else {

			DescribedPlace describedPlace = new DescribedPlace(category, 
															   new Position(xPosition, yPosition), 
															   name,
															   description);

			describedPlace.addMouseListener(new ExistingPlaceController(describedPlace));
			places.put(describedPlace.getPosition(), describedPlace);
			placesByName.put(describedPlace.getName(), describedPlace);
			
		}

	}

	private void parsePlaceLine(String[] properties) {

		try {

			String type = properties[0];
			String category = properties[1];
			//Position position = new Position(Integer.parseInt(properties[2]), Integer.parseInt(properties[3]));
			int xPosition = Integer.parseInt(properties[2]);
			int yPosition = Integer.parseInt(properties[3]);
			String name = properties[4];
			String description = properties.length == 6 ? properties[6] : null;

			createPlace(category, xPosition, yPosition, name, description);

		} catch(ArrayIndexOutOfBoundsException e) {

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
				
				parsePlaceLine(properties);
					
			}

		} catch(FileNotFoundException e) {

			System.out.println("Places file not found.");

		}

		view.updatePlaces();

	}

	public File getPlacesFile() {

		return placesFile;

	}

	public HashMap<Position, Place> getPlaces() {

		return places;

	}

	public Place getPlace(Position position) {

		return places.get(position);

	}

	public ArrayList<Place> getPlacesByName(String name) {

		return placesByName.get(name);

	}

	public void putPlace(Position position, Place place) {

		places.put(position, place);

	}

	public void removeMarkedPlaces() {

		/*for(Place place : places.values()) {

			if(place.getMarked()) {

				placesByName.remove(place.getName(), place);

			}

		}*/

		places.entrySet().removeIf(e -> e.getValue().getMarked());
		placesByName.removeIf(place -> {
		
			return ((Place)place).getMarked();

		});

	}

	public void setCategoryToVisible(String category, boolean trueOrFalse) {

		places.forEach( (position, place) -> {
		
			if(place.getCategory().equals(category)) {

				place.setVisible(trueOrFalse);

			}

		});

	}

}
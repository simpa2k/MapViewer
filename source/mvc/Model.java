package mvc;

import places.*;
import listeners.ExistingPlaceListener;

import java.io.*;
import java.nio.*;
import java.nio.file.*;
import java.nio.charset.Charset;
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

			namedPlace.addMouseListener(new ExistingPlaceListener(namedPlace));
			places.put(namedPlace.getPosition(), namedPlace);
			placesByName.put(namedPlace.getName(), namedPlace);

		} else {

			DescribedPlace describedPlace = new DescribedPlace(category, 
									   new Position(xPosition, yPosition), 
									   name,
									   description);
			System.out.println(describedPlace.getCategory());
			describedPlace.addMouseListener(new ExistingPlaceListener(describedPlace));
			places.put(describedPlace.getPosition(), describedPlace);
			placesByName.put(describedPlace.getName(), describedPlace);
			
		}

	}

	private void parsePlaceLine(String[] properties) {

		try {

			String type = properties[0];
			String category = properties[1];
			int xPosition = Integer.parseInt(properties[2]);
			int yPosition = Integer.parseInt(properties[3]);
			String name = properties[4];
			String description = properties.length == 6 ? properties[5] : null;

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

	public void savePlaces(Path pathToSaveFile) {
		
		if(places != null) {
			
			Charset charset = Charset.forName("UTF-8");
			
			places.forEach( (position, place) -> {		
				
				try(BufferedWriter writer = Files.newBufferedWriter(pathToSaveFile, charset, StandardOpenOption.APPEND)) {

					writer.write(place.toString(), 0, place.toString().length());
					writer.newLine();

				} catch(IOException e) {
					
					System.out.println(e);

				}	
			});
		}
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

	public Place searchAreaAroundPosition(Position positionToCheck) {

		for(Map.Entry<Position, Place> entry : places.entrySet()) {

			Position position = entry.getKey();
			Place place = entry.getValue();

			boolean isSufficientlyCloseX = ( positionToCheck.getX() >= (position.getX() - 10) ) && 
						       ( positionToCheck.getX() <= (position.getX() + 10) );

			boolean isSufficientlyCloseY = ( positionToCheck.getY() >= (position.getY() - 10) ) && 
						       ( positionToCheck.getY() <= (position.getY() + 10) );

			if(isSufficientlyCloseX && isSufficientlyCloseY) {

				return place;

			}

		}
		return null;

	}

	public HashSet <Place> getPlacesByName(String name) {

		return placesByName.get(name);

	}

	public void putPlace(Position position, Place place) {

		places.put(position, place);

	}
	
	public void removeMarkedPlaces() {

		places.entrySet().removeIf(e -> e.getValue().getMarked());
		placesByName.removeIf(place -> place.getMarked());

	}

}

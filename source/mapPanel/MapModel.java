package mapPanel;

import places.*;
import listeners.ExistingPlaceListener;

import java.io.*;
import java.nio.*;
import java.nio.file.*;
import java.nio.charset.Charset;
import java.util.*;

public class MapModel {

	private MapPanel view;

	private File mapFile;
	private File placesFile;
	
	private	Charset charset = Charset.forName("UTF-8");

	private HashMap<Position, Place> places = new HashMap<>();
	private MultiMap<String, Place> placesByName = new MultiMap<>();

	private boolean changed = false;

	protected void setView(MapPanel view) {

		this.view = view;

	}

	public void setMapFile(File mapFile) {

		this.mapFile = mapFile;

		view.setMap(mapFile);

	}

	public File getMapFile() {

		return mapFile;

	}

	public boolean getChanged() {

		return changed;

	}

	public void createPlace(String category, int xPosition, int yPosition, String name, String description) {

		Place place;

		if(description == null) {
			
			place = new NamedPlace(category, 
					new Position(xPosition, yPosition), 
					name);

			placesByName.put(place.getName(), place);

		} else {

			place = new DescribedPlace(category, 
						new Position(xPosition, yPosition), 
						name,
						description);

			placesByName.put(place.getName(), place);
			
		}
		
		place.addMouseListener(new ExistingPlaceListener(place));
		places.put(place.getPosition(), place);
		view.drawPlace(place);
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
		
		try(BufferedReader reader = Files.newBufferedReader(placesFile.toPath(), charset)) {
			
			String line;

			while( (line = reader.readLine()) != null ) {

				String[] properties = line.split(",");
				
				parsePlaceLine(properties);
					
			}

		} catch(IOException e) {

			System.out.println("Places file not found.");

		} 

	}

	public void addPlace(String selectedCategory, int xPosition, int yPosition, String name, String description) {
		
		createPlace(selectedCategory, xPosition, yPosition, name, description);
		changed = true;
	}

	public void savePlaces(File saveFile) {
		
		if(places != null) {
			
			places.forEach( (position, place) -> {		
				
				try(BufferedWriter writer = Files.newBufferedWriter(saveFile.toPath(), charset, StandardOpenOption.APPEND)) {

					writer.write(place.toString(), 0, place.toString().length());
					writer.newLine();

				} catch(IOException e) {
					
					System.out.println(e);

				}	
			});

			changed = false;

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

		changed = true;

	}

}

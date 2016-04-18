package mapPanel;

import places.*;
import listeners.ExistingPlaceListener;

import java.io.*;
import java.nio.*;
import java.nio.file.*;
import java.nio.charset.Charset;
import java.util.*;

import javax.swing.JOptionPane;

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

	public void setMapFile(File mapFile) throws IOException {

		this.mapFile = mapFile;

		view.setMap(mapFile);

	}

	public File getMapFile() {

		return mapFile;

	}

	public boolean getChanged() {

		return changed;

	}
	
	private void addPlace(Place place) {

		place.addMouseListener(new ExistingPlaceListener(place));
		
		places.put(place.getPosition(), place);

		if(place instanceof NamedPlace) {

			placesByName.put(((NamedPlace)place).getName(), place);

		}

		view.drawPlace(place);

	}

	public void addNewPlace(Place place) {

		addPlace(place);	
		changed = true;

	}


	public void createPlace(String category, int xPosition, int yPosition, String name, String description) {

		Place place;

		if(description == null) {
			
			place = new NamedPlace(category, 
					new Position(xPosition, yPosition), 
					name);

		} else {

			place = new DescribedPlace(category, 
						new Position(xPosition, yPosition), 
						name,
						description);
			
		}
		
		addPlace(place);
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

	public void loadPlaces(File placesFile) throws IOException {

	        this.placesFile = placesFile;
		
		BufferedReader reader = Files.newBufferedReader(placesFile.toPath(), charset);
			
		String line;

		while( (line = reader.readLine()) != null ) {

			String[] properties = line.split(",");
				
			parsePlaceLine(properties);
					
		}


	}

	public void savePlaces(File saveFile) throws IOException {

		if(places != null) {

			PrintWriter writer = new PrintWriter(new FileWriter(saveFile));

			places.forEach( (position, place) -> {		
						
				writer.println(place.toString());

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
		
		places.forEach((position, place) -> {
			
			if(place.getMarked()) {

				if(place instanceof NamedPlace) {

					String name = ((NamedPlace)place).getName();
					placesByName.remove(name, place);

				}
				places.remove(place);

			}

		});

		changed = true;

	}

}

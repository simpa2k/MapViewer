package places;

public class NamedPlace extends Place {

	private String name;

	public NamedPlace(String category, Position position, String name) {

		super(category, position);

		this.name = name;

	}

	public String getName() {

		return name;

	}

}
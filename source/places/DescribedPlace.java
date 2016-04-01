package places;

public class DescribedPlace extends NamedPlace {

	private String description;

	public DescribedPlace(String category, 
						  Position position, 
						  String name, 
						  String description) {

		super(category, position, name);

		this.description = description;

	}

}
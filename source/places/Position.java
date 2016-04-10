package places;

public class Position {

	private int x;
	private int y;

	public Position(int x, int y) {

		this.x = x;
		this.y = y;

	}

	public int getX() {

		return x;

	}

	public int getY() {

		return y;

	}

	@Override
	public boolean equals(Object other) {

		if(other instanceof Position) {
			
			Position position = (Position) other;

			return (position.getX() == x) && (position.getY() == y);

		}
		return false;

	}

	@Override
	public int hashCode() {

		return x + y * (Integer.MAX_VALUE + 1); //multiplicera med ett värde som är 1 större än det största möjliga värdet för y

	}

	@Override
	public String toString() {
		
		return x + "," + y;

	}
}

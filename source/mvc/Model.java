package mvc;

public class Model {

	private View view;

	public void setView(View view) {

		this.view = view;

	}

	public void update() {

		view.update();

	}

}
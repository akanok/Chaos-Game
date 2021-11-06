package chaos.game.shape;

import chaos.game.shape.generator.PolygonGenerator;

public class Triangle extends PolygonGenerator {

	public Triangle() {
		super();
		super.setSidesNumber(3);
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName();
	}

}

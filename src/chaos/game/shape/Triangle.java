package chaos.game.shape;

import chaos.game.shape.generator.PolygonGenerator;

public class Triangle extends PolygonGenerator {

	public Triangle() {
		super(3);
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName();
	}

	


}

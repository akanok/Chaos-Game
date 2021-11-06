package chaos.game.shape;

import chaos.game.shape.generator.PolygonGenerator;

public class CustomShape extends PolygonGenerator {

	public CustomShape() {
		super();
	}
	
	public CustomShape(int sidesNumber) {
		super();
		super.setSidesNumber(sidesNumber);
	}
	
	
	@Override
	public String toString() {
		return "Custom";
	}

}

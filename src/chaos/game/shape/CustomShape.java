package chaos.game.shape;

import chaos.game.shape.generator.PolygonGenerator;

public class CustomShape extends PolygonGenerator {

	public CustomShape() {
		super(0);
	}
	
	public CustomShape(int sidesNumber) {
		super(sidesNumber);
	}
	
	@Override
	public final void setSidesNumber(int sidesNumber) {
		super.setSidesNumber(sidesNumber);
	}
	
	
	@Override
	public String toString() {
		return "Custom";
	}

}

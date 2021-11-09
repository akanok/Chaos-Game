package chaos.game.shape;

import chaos.game.shape.generator.PolygonGenerator;

public class Square extends PolygonGenerator {

	public Square() {
		super(4);
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName();
	}

}

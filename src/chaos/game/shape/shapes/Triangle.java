package chaos.game.shape.shapes;

import chaos.game.gui.WindowVisitor;
import chaos.game.shape.ShapeActionOnSelectionVisitor;
import chaos.game.shape.generator.PolygonGenerator;

public class Triangle extends PolygonGenerator implements ShapeActionOnSelectionVisitor {

	public Triangle() {
		super(3);
	}
	
	
	@Override
	public void accept(WindowVisitor visitor) {
		visitor.visitTriangle();
		
	}

	
	@Override
	public String toString() {
		return getClass().getSimpleName();
	}

}

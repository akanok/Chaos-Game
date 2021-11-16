package chaos.game.shape.shapes;

import chaos.game.gui.WindowVisitor;
import chaos.game.shape.ShapeActionOnSelectionVisitor;
import chaos.game.shape.generator.PolygonGenerator;

public class CustomShape extends PolygonGenerator implements ShapeActionOnSelectionVisitor{

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
	public void accept(WindowVisitor visitor) {
		visitor.visitCustomShape();
	}
	
	
	@Override
	public String toString() {
		return "Custom";
	}

}

package chaos.game.shape.shapes;

import chaos.game.gui.WindowVisitor;
import chaos.game.shape.ShapeActionOnSelectionVisitor;
import chaos.game.shape.generator.PolygonGenerator;

public class Square extends PolygonGenerator implements ShapeActionOnSelectionVisitor{

	public Square() {
		super(4);
	}

	@Override
	public void accept(WindowVisitor visitor) {
		visitor.visitSquare();
		
	}

	
	@Override
	public String toString() {
		return getClass().getSimpleName();
	}
}

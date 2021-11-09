package chaos.game.rule;

import java.awt.Point;
import java.util.Random;

public class SquareRule extends PolygonRule{


	public SquareRule(int iterations, double distanceFromOldPoint) {
		super(iterations, distanceFromOldPoint);
	}

	public SquareRule() {
		super();
	}

	
	@Override
	protected Point chooseNewVertex(Random randomGenerator, Point[] shapePoints, Point previousVertex) {
		Point newVertex;
		do {
			newVertex = shapePoints[randomGenerator.nextInt(shapePoints.length)];
		} while (newVertex.equals(previousVertex));
		
		return newVertex;
	}

	@Override
	public SquareRule createNewWith(int iterations, double distanceFromOldPoint) {
		return new SquareRule(iterations, distanceFromOldPoint);
	}
	
	
	@Override
	public String toString() {
		return "Defalut rule for squares";
	}
	
	
}

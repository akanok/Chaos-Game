package chaos.game.rule;

import java.awt.Point;
import java.util.Random;

public class SquareRule extends PolygonRule{

	public SquareRule(int iterations, double distanceFromOldPoint) {
		super(iterations, distanceFromOldPoint);
	}


	@Override
	protected Point chooseNewVertex(Random randomGenerator, Point[] shapePoints, Point previousVertex) {
		Point newVertex;
		do {
			newVertex = shapePoints[randomGenerator.nextInt(shapePoints.length)];
		} while (newVertex.equals(previousVertex));
		
		return newVertex;
	}

}

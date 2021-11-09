package chaos.game.rule;

import java.awt.Point;
import java.util.Random;


public class TriangleRule extends PolygonRule {
	
	
	public TriangleRule(int iterations, double distanceFromOldPoint) {
		super(iterations, distanceFromOldPoint);
	}
	
	public TriangleRule() {
		super();
	}

	
	@Override
	protected Point chooseNewVertex(Random randomGenerator, Point[] shapePoints, Point previousVertex) {
		return shapePoints[randomGenerator.nextInt(shapePoints.length)];
	}
	
	@Override
	public TriangleRule createNewWith(int iterations, double distanceFromOldPoint) {
		return new TriangleRule(iterations, distanceFromOldPoint);
	}

	
	@Override
	public String toString() {
		return "Defalut rule for triangles";
	}


}







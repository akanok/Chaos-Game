package chaos.game.rule;

import java.awt.Point;
import java.util.Random;

public  abstract class PolygonRule implements Rule {

	private final Random randomGenerator = new Random();
	private final int iterations;
	private final double distanceFromOldPoint;

	
	protected PolygonRule(int iterations, double distanceFromOldPoint) {
		this.iterations = iterations;
		this.distanceFromOldPoint = distanceFromOldPoint;
	}

	
	@Override
	public Point[] generatePoints(Point[] shapePoints) {
		
		Point[] outputPoints = new Point[iterations];
		
		// Choose first point randomly
		Point currentPoint = new Point(randomGenerator.nextInt(), randomGenerator.nextInt()); 
		
		Point previousVertex = null;
		
		// Generate points randomly
		for (int i = 0; i < iterations; i++) {
			outputPoints[i] = currentPoint;
			
			// Clone point to get a new reference
			currentPoint = new Point(currentPoint.x, currentPoint.y); 
			
			Point newVertex = chooseNewVertex(randomGenerator, shapePoints, previousVertex);
	
			// Calculate midpoint, get new point
			currentPoint.setLocation(distanceFromOldPoint * (currentPoint.x + newVertex.x), distanceFromOldPoint * (currentPoint.y + newVertex.y));
			
			// Save previous Vertex
			previousVertex = new Point(newVertex.x, newVertex.y);
		}
	
		return outputPoints;
	}
	
	
	protected abstract Point chooseNewVertex(Random randomGenerator, Point[] shapePoints, Point previousVertex);
	
}


package chaos.game;

import java.awt.Point;
import java.util.Random;


public class ChaosGameGenerator {
	
	public static enum Rules{ RULE1, RULE2 }
	
	
	public static Point[] generate(int n, Point[] shapePoints, Rules rule) {
		
		Random randomGenerator = new Random();

		Point[] outputPoints = new Point[n];

		// Choose first point randomly
		Point currentPoint = new Point(randomGenerator.nextInt(), randomGenerator.nextInt()); 

		Point previousVertex = null;
		
		for (int i = 0; i < n; i++) {
			outputPoints[i] = currentPoint;
			
			// Clone point to get a new reference
			currentPoint = new Point(currentPoint.x, currentPoint.y); 
			
			Point newVertex = chooseNewVertexRule(shapePoints, randomGenerator, rule, previousVertex);

			// Calculate midpoint, get new point
			currentPoint.setLocation(0.5 * (currentPoint.x + newVertex.x), 0.5 * (currentPoint.y + newVertex.y));
			
			// Save old choosen vertex
			previousVertex = new Point(newVertex.x, newVertex.y);

		}

		return outputPoints;
	}
	
	private static Point chooseNewVertexRule(Point[] shapePoints, Random randomGenerator, Rules rule, Point previousVertex) {
		Point newVertex;
		if (rule == Rules.RULE2){ // Retrieve random shape vertex different from the previous one
			do {
				newVertex = shapePoints[randomGenerator.nextInt(shapePoints.length)];
			} while (newVertex.equals(previousVertex));
		} else { // Retrieve random shape vertex
			newVertex = shapePoints[randomGenerator.nextInt(shapePoints.length)];
		}
		return newVertex;
	}
	
	
}





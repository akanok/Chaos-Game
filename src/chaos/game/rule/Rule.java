package chaos.game.rule;

import java.awt.Point;


public interface Rule {
	
	public Point[] generatePoints(Point[] shapePoints);

	public Rule createNewWith(int iterations, double distanceFromOldPoint);

}

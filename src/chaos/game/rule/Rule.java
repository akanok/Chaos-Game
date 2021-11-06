package chaos.game.rule;

import java.awt.Point;


public interface Rule {
	
	public Point[] generatePoints(Point[] shapePoints);
	
	public void setParameters(int iterations, double distanceFromOldPoint);

}

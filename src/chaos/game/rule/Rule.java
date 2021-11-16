package chaos.game.rule;

import java.awt.Point;


public interface Rule {
	
	public Point[] generatePoints(Point[] shapePoints, int iterations);

}

package chaos.game.rule;

import java.awt.Point;


public interface Rule {
	
	Point[] generatePoints(Point[] shapePoints, int iterations);

}

package chaos.game.shape.generator;

import java.awt.Point;

public interface ShapeGenerator {
	
	public int[][] generate(Point center, int radius);

}

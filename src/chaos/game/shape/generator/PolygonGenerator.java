package chaos.game.shape.generator;

import java.awt.Point;
import java.util.stream.IntStream;

import chaos.game.rule.Rule;


public abstract class PolygonGenerator implements ShapeGenerator {

	private static final double fullTurn = Math.toRadians(360);
	private static final double theta = Math.toRadians(90);

	private int sidesNumber;
	
	protected PolygonGenerator(int sidesNumber) {
		this.sidesNumber = sidesNumber;
	}

	
	public int getSidesNumber() {
		return sidesNumber;
	}
	
	protected void setSidesNumber(int sidesNumber) {
		this.sidesNumber = sidesNumber;
	}
	

	@Override
	public int[][] generate(Point center, int radius) {
		if (sidesNumber<3) throw new IllegalArgumentException("The number of sides is: "+ sidesNumber+ "\nA polygon must have at least 3 sides!");

		final int[][] shapePoints = new int[2][sidesNumber];
		final double angle = fullTurn/sidesNumber;

		IntStream.range(0, shapePoints[0].length)
					.forEach( i -> { shapePoints[0][i] = (int)( radius * Math.cos(calculateAngle(i, angle)) ) + center.x;
									 shapePoints[1][i] = (int)( radius * Math.sin(calculateAngle(i, angle)) ) + center.y; }); 
		return shapePoints;
	}


	private final double calculateAngle(int i, double angle) {
		return theta + angle*i + Math.toRadians( (sidesNumber%2==0)?( (sidesNumber%4==0)?45:90 ):180 );
	}

}




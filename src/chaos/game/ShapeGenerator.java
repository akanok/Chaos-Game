package chaos.game;

import java.awt.Point;
import java.util.stream.IntStream;

import chaos.game.exception.LessThanTowSidesException;

public class ShapeGenerator {
	/**
	private static final double fullTurn = Math.toRadians(360);
	private static final double theta = Math.toRadians(90);
	
	public static int[][] generateRegularPolygon(Point center, int radius, int sidesNumber ) throws LessThanTowSidesException {
		if (sidesNumber<3) throw new LessThanTowSidesException(sidesNumber);
		
		final int[][] shapePoints = new int[2][sidesNumber];
		final double angle = fullTurn/sidesNumber;

		IntStream.range(0, shapePoints[0].length)
			.forEach( i -> { shapePoints[0][i] = (int)( radius * Math.cos(angle(i,sidesNumber, angle)) ) + center.x;
							 shapePoints[1][i] = (int)( radius * Math.sin(angle(i,sidesNumber, angle)) ) + center.y; }); 
		return shapePoints;
	}
	
	private static double angle(int i, int sidesNumber, double angle) {
		return theta + angle*i + Math.toRadians( (sidesNumber%2==0)?( (sidesNumber%4==0)?45:90 ):180 );
	}
	*/

}




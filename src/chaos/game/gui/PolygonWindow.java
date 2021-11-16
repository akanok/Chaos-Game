package chaos.game.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import chaos.game.exception.LessThanTowSidesException;
import chaos.game.rule.Rule;
import chaos.game.shape.generator.PolygonGenerator;

public class PolygonWindow {
	
	private static final Point center = new Point(300,250);
	private static final int radius = 230;

	private final PolygonGenerator polygonShapeGenerator;
	private final Rule rule;
	private final int iterations;

	/**
	 * @param polygonShapeGenerator polygon in which all generated points are loked up
	 * @param rule to apply to generate random points
	 * @param iterations number of red points which will be generated
	 * @throws LessThanTowSidesException if (polygonShapeGenerator.getSidesNumber() <3), a polygon always has at least 2 sides
	 */
	public PolygonWindow(PolygonGenerator polygonShapeGenerator, Rule rule, int iterations) throws LessThanTowSidesException {
		if (polygonShapeGenerator.getSidesNumber() <3) throw new LessThanTowSidesException(polygonShapeGenerator.getSidesNumber());
		this.polygonShapeGenerator = polygonShapeGenerator;
		this.rule = rule;
		this.iterations = iterations;
				
		setUpWindow();
	}
	


	private void setUpWindow() {
		JFrame shapeFrame = new JFrame();
		JPanel shapePanel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				try {
					drawShape(g);
				} catch (LessThanTowSidesException e) {
					// This exception should have been raised before this method
					e.printStackTrace();
				}
			}
		};
		
		shapePanel.setPreferredSize(new Dimension(600,500));
		shapePanel.setBorder( BorderFactory.createEmptyBorder(5,5,5,5) );
		shapePanel.setLayout( new GridLayout(0,1) );

		shapeFrame.add(shapePanel);
		shapeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		shapeFrame.setTitle("Chaos Game: "+polygonShapeGenerator );
		shapeFrame.setLocationRelativeTo(null);
		shapeFrame.pack();
		shapeFrame.setVisible(true);
	}

	
	private void drawShape(Graphics g) throws LessThanTowSidesException {

		final int[][] vertices = polygonShapeGenerator.generate(center, radius);

		g.setColor(Color.GRAY);
		g.drawPolygon(vertices[0], vertices[1], vertices[1].length);

		/*g.setColor(Color.GREEN); // draw the circumpherence
		g.drawOval(center.x-radius, center.y-radius, radius*2, radius*2);*/

		Point[] shapePoints = new Point[vertices[0].length];
		IntStream.range(0,vertices[0].length)
			.mapToObj( i -> new Point(vertices[0][i], vertices[1][i]) )
			.collect(Collectors.toList())
			.toArray(shapePoints);
		
		Point[] outputPoints = rule.generatePoints(shapePoints,iterations);

		g.setColor(Color.RED);
		Stream.of(outputPoints).forEach( p -> g.drawLine(p.x, p.y, p.x, p.y) );
	}


}








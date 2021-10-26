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

import chaos.game.ShapeGenerator;
import chaos.game.exception.LessThanTowSidesException;
import chaos.game.rule.Rule;

public class ShapeWindow {

	private final int sidesNumber;
	private final Rule rule;

	private final Point center = new Point(300,250);
	private final int radius = 230;


	/**
	 * @param shape
	 * @param iterationNumber
	 * @param sidesNumber set to zero if shape is not CUSTOM
	 * @throws LessThanTowSidesException if (shape==Shapes.CUSTOM && sidesNumber<3)
	 */
	public ShapeWindow(int sidesNumber, Rule rule) throws LessThanTowSidesException {
		if (sidesNumber<3) throw new LessThanTowSidesException(sidesNumber);
		this.sidesNumber = sidesNumber;
		this.rule = rule;
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
					// This exception should be raised before this method
					e.printStackTrace();
				}
			}
		};
		
		shapePanel.setPreferredSize(new Dimension(600,500));
		shapePanel.setBorder( BorderFactory.createEmptyBorder(5,5,5,5) );
		shapePanel.setLayout( new GridLayout(0,1) );

		shapeFrame.add(shapePanel);
		shapeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		shapeFrame.setTitle("Chaos Game: "+sidesNumber+"-side polygon" );
		shapeFrame.setLocationRelativeTo(null);
		shapeFrame.pack();
		shapeFrame.setVisible(true);
	}

	private void drawShape(Graphics g) throws LessThanTowSidesException {

		final int[][] vertices = ShapeGenerator.generate(center,radius,sidesNumber);

		g.setColor(Color.GRAY);
		g.drawPolygon(vertices[0], vertices[1], vertices[1].length);

		/*g.setColor(Color.GREEN); // draw the circumpherence
		g.drawOval(center.x-radius, center.y-radius, radius*2, radius*2);*/

		Point[] shapePoints = new Point[vertices[0].length];
		IntStream.range(0,vertices[0].length)
			.mapToObj( i -> new Point(vertices[0][i], vertices[1][i]) )
			.collect( Collectors.toList())
			.toArray( shapePoints );
		
		Point[] outputPoints = rule.generatePoints(shapePoints);

		g.setColor(Color.RED);
		Stream.of(outputPoints).forEach( p -> g.drawLine(p.x, p.y, p.x, p.y) );
	}


}








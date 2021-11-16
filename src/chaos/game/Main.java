package chaos.game;

import java.awt.EventQueue;

import chaos.game.gui.MainWindow;
import chaos.game.rule.Rule;
import chaos.game.rule.SquareRule;
import chaos.game.rule.TriangleRule;
import chaos.game.shape.CustomShape;
import chaos.game.shape.Square;
import chaos.game.shape.Triangle;
import chaos.game.shape.generator.ShapeGenerator;

public class Main { 


	public static void main(String[] args) {

		Rule[] rules = new Rule[]{new TriangleRule(0.5), new SquareRule(0.5)};
		ShapeGenerator[]  shapes = new ShapeGenerator[]{ new Triangle(), new Square(), new CustomShape() };

		// Launch the application
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MainWindow(rules, shapes);
			}
		});


	}

}









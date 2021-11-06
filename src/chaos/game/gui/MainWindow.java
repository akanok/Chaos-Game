package chaos.game.gui;


import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.text.NumberFormatter;

import chaos.game.exception.LessThanTowSidesException;
import chaos.game.rule.Rule;
import chaos.game.rule.SquareRule;
import chaos.game.rule.TriangleRule;
import chaos.game.shape.CustomShape;
import chaos.game.shape.Square;
import chaos.game.shape.Triangle;
import chaos.game.shape.generator.PolygonGenerator;
import chaos.game.shape.generator.ShapeGenerator;


public class MainWindow implements ActionListener {

	private final JFrame mainFrame;
	private final JPanel mainPanel;
	private final JLabel shapeLabel;
	private final JComboBox<ShapeGenerator> shapeMenu;
	private final JLabel sidesLabel;
	private final JFormattedTextField sidesNumber;
	private final JLabel iterationsLabel;
	private final JFormattedTextField iterationsNumber;
	private final JLabel ruleLabel;
	private final JComboBox<Rule> ruleMenu;
	private final JButton startButton;

	public MainWindow() {
		mainFrame = new JFrame();

		mainPanel = new JPanel();
		mainPanel.setBorder( BorderFactory.createEmptyBorder(50, 30, 50, 30) );
		mainPanel.setLayout( new GridLayout(5,2,5,5) );

		shapeLabel = new JLabel("Select shape: ", SwingConstants.CENTER);
		shapeMenu = new JComboBox<ShapeGenerator>( getShapes() );
		shapeMenu.addItemListener( shapeMenuselectionListener() );

		sidesLabel = new JLabel("Number of polygon sides(>=3) : ", SwingConstants.CENTER);
		sidesLabel.setEnabled(false);
		NumberFormatter biggerThen3Formatter = new NumberFormatter();
		biggerThen3Formatter.setValueClass(Integer.class);
		biggerThen3Formatter.setMinimum(3);
		biggerThen3Formatter.setMaximum(Integer.MAX_VALUE);
		biggerThen3Formatter.setAllowsInvalid(true);
		biggerThen3Formatter.setCommitsOnValidEdit(false);
		sidesNumber = new JFormattedTextField(biggerThen3Formatter);
		sidesNumber.setEnabled(false);

		ruleLabel = new JLabel("Select rule to generate random points",SwingConstants.CENTER);
		ruleMenu = new JComboBox<Rule>( getRules() );

		iterationsLabel = new JLabel("Insert the number of iterations: ",SwingConstants.CENTER);
		NumberFormatter intFormatter = new NumberFormatter();
		intFormatter.setValueClass(Integer.class);
		intFormatter.setMinimum(0);
		intFormatter.setMaximum(Integer.MAX_VALUE);
		intFormatter.setAllowsInvalid(false);
		intFormatter.setCommitsOnValidEdit(true);
		iterationsNumber = new JFormattedTextField(intFormatter);
		iterationsNumber.setText("100000");


		startButton = new JButton("Start!");
		startButton.addActionListener(this);


		mainPanel.add(shapeLabel);
		mainPanel.add(shapeMenu);

		mainPanel.add(sidesLabel);
		mainPanel.add(sidesNumber);

		mainPanel.add(ruleLabel);
		mainPanel.add(ruleMenu);

		mainPanel.add(iterationsLabel);
		mainPanel.add(iterationsNumber);

		mainPanel.add( new JLabel() );
		mainPanel.add(startButton);


		mainFrame.add(mainPanel);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLocationRelativeTo(null); // should center the window
		mainFrame.setTitle("Chaos Game");
		mainFrame.pack();
		mainFrame.setVisible(true);

	}


	@Override
	public void actionPerformed(ActionEvent ae) {
		
		PolygonGenerator polygonGenerator = (PolygonGenerator)shapeMenu.getSelectedItem();
		if (sidesNumber.getValue()!=null)
			polygonGenerator.setSidesNumber((int)sidesNumber.getValue());
		
		int iterations = (iterationsNumber.getValue() ==null)?0:(int)iterationsNumber.getValue();
		
		Rule rule = (Rule)ruleMenu.getSelectedItem();
		rule.setParameters(iterations, 0.5);

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new PolygonWindow(polygonGenerator, rule);
				} catch (LessThanTowSidesException e) {
					JOptionPane.showMessageDialog(null,
							("The number of sides is: " + e.getSidesNumber() + "\nA polygon must have at least 3 sides!"),
							"Less than tow sides error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});


	}


	private ItemListener shapeMenuselectionListener() {
		return new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent ie) {

				if (shapeMenu.getSelectedItem() instanceof CustomShape) {
					sidesLabel.setEnabled(true);
					sidesNumber.setEnabled(true);
					ruleMenu.setSelectedIndex(1);
				} else {
					sidesLabel.setEnabled(false);
					sidesNumber.setEnabled(false);
					sidesNumber.setText(null);
					if (shapeMenu.getSelectedItem() instanceof Triangle) {
						ruleMenu.setSelectedIndex(0);
					} else {
						ruleMenu.setSelectedIndex(1);
					}
				}

			}

		};
	}


	
	private Rule[] getRules() {
		return new Rule[]{new TriangleRule(), new SquareRule()};
	}

	private ShapeGenerator[] getShapes() {
		return new ShapeGenerator[]{ new Triangle(), new Square(), new CustomShape() };
	}

	

	
}









package chaos.game.gui;


import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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


public class MainWindow implements ActionListener{

	private enum Shapes{ TRIANGLE, SQUARE, PENTAGON, CUSTOM }

	private final JFrame mainFrame;
	private final JPanel mainPanel;
	private final JLabel shapeLabel;
	private final JComboBox<Shapes> shapeMenu;
	private final JLabel sidesLabel;
	private final JFormattedTextField sidesNumber;
	private final JLabel iterationsLabel;
	private final JFormattedTextField iterationsNumber;
	//private final JCheckBox ruleOption;
	private final JButton startButton;

	public MainWindow() {
		mainFrame = new JFrame();

		mainPanel = new JPanel();
		mainPanel.setBorder( BorderFactory.createEmptyBorder(50, 30, 50, 30) );
		mainPanel.setLayout( new GridLayout(4,2,5,5) );

		shapeLabel = new JLabel("Select shape: ", SwingConstants.CENTER);
		shapeMenu = new JComboBox<Shapes>( Shapes.values() );
		shapeMenu.addActionListener( selectionListener() );

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
		
		iterationsLabel = new JLabel("Insert the number of iterations: ");
		NumberFormatter intFormatter = new NumberFormatter();
		intFormatter.setValueClass(Integer.class);
		intFormatter.setMinimum(0);
		intFormatter.setMaximum(Integer.MAX_VALUE);
		intFormatter.setAllowsInvalid(false);
		intFormatter.setCommitsOnValidEdit(true);
		iterationsNumber = new JFormattedTextField(intFormatter);
		iterationsNumber.setText("100000");

		//ruleOption = new JCheckBox("Use different rule to generate red points",false);

		startButton = new JButton("Start!");
		startButton.addActionListener(this);


		mainPanel.add(shapeLabel);
		mainPanel.add(shapeMenu);

		mainPanel.add(sidesLabel);
		mainPanel.add(sidesNumber);

		mainPanel.add(iterationsLabel);
		mainPanel.add(iterationsNumber);

		//mainPanel.add(ruleOption);
		mainPanel.add(startButton);


		mainFrame.add(mainPanel);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLocationRelativeTo(null); //shoud center the window
		mainFrame.setTitle("Chaos Game");
		mainFrame.pack();
		mainFrame.setVisible(true);

	}


	@Override
	public void actionPerformed(ActionEvent ae) {

		int iterations = (iterationsNumber.getValue() ==null)?0:(int)iterationsNumber.getValue();
		int sides;

		switch ((Shapes) shapeMenu.getSelectedItem()) {
			case CUSTOM:
				sides =  (sidesNumber.getValue()==null)?0:(int)sidesNumber.getValue();
				break;
			case SQUARE:
				sides = 4;	
				break;
			case PENTAGON:
				sides = 5;
				break;
			default: // TRIANGLE 
				sides = 3;
				break;
		}

		Rule rule = new SquareRule(iterations, 0.5 );
		
		
		try {
			new ShapeWindow(sides, rule);
			
		}catch (LessThanTowSidesException e) {
			JOptionPane.showMessageDialog(null,
					("The number of sides is: " + e.getSidesNumber() + "\nA polygon must have at least 3 sides!"),
					"Less than tow sides error",
					JOptionPane.ERROR_MESSAGE);
		}

	}


	private ActionListener selectionListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Shapes selection = (Shapes)shapeMenu.getSelectedItem();

				if (selection == Shapes.TRIANGLE) {
					//ruleOption.setSelected(false);
					sidesLabel.setEnabled(false);
					sidesNumber.setEnabled(false);
					sidesNumber.setText("");
				} else {
					//ruleOption.setSelected(true);
					if (selection == Shapes.CUSTOM) {
						sidesLabel.setEnabled(true);
						sidesNumber.setEnabled(true);
					} else {
						sidesLabel.setEnabled(false);
						sidesNumber.setEnabled(false);
						sidesNumber.setText("");
					}
				}

			}
		};
	}






}









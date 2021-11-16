package chaos.game.gui;


import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import chaos.game.shape.ShapeActionOnSelectionVisitor;
import chaos.game.shape.generator.PolygonGenerator;
import chaos.game.shape.generator.ShapeGenerator;
import chaos.game.shape.shapes.CustomShape;


public class MainWindow implements ActionListener, WindowVisitor {

	private final JComboBox<ShapeGenerator> shapeMenu;
	private final JLabel sidesLabel;
	private final JFormattedTextField sidesNumber, iterationsNumber;
	private final JComboBox<Rule> ruleMenu;


	public MainWindow(Rule[] rules, ShapeGenerator[] shapes) {		
		shapeMenu = new JComboBox<ShapeGenerator>( shapes );
		sidesLabel = new JLabel("Number of polygon sides(>=3) : ", SwingConstants.CENTER);
		ruleMenu = new JComboBox<Rule>( rules );
		sidesNumber = new JFormattedTextField( setUpFormatter(Integer.MIN_VALUE, true) );	
		iterationsNumber = new JFormattedTextField( setUpFormatter(0, false) );
		setUpWindow();
	}
	

	private void setUpWindow() {
		
		final JPanel mainPanel = new JPanel();
		mainPanel.setBorder( BorderFactory.createEmptyBorder(50, 30, 50, 30) );
		mainPanel.setLayout( new GridLayout(5,2,5,5) );

		final JLabel shapeLabel = new JLabel("Select shape: ", SwingConstants.CENTER);
		shapeMenu.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				guiSetEmbleDisableItems();
			}
		} );
		mainPanel.add(shapeLabel);
		mainPanel.add(shapeMenu);

		
		sidesLabel.setEnabled(false);
		sidesNumber.setEnabled(false);
		mainPanel.add(sidesLabel);
		mainPanel.add(sidesNumber);

		final JLabel ruleLabel = new JLabel("Select rule to generate random points",SwingConstants.CENTER);
		mainPanel.add(ruleLabel);
		mainPanel.add(ruleMenu);

		final JLabel iterationsLabel = new JLabel("Insert the number of iterations: ",SwingConstants.CENTER);
		iterationsNumber.setText("100.000");
		mainPanel.add(iterationsLabel);
		mainPanel.add(iterationsNumber);

		final JButton startButton = new JButton("Start!");
		startButton.addActionListener(this);
		mainPanel.add( new JLabel() );
		mainPanel.add(startButton);

		final JFrame mainFrame = new JFrame();
		mainFrame.add(mainPanel);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLocationRelativeTo(null); // should center the window
		mainFrame.setTitle("Chaos Game");
		mainFrame.pack();
		mainFrame.setVisible(true);
	}

	private NumberFormatter setUpFormatter(int min, boolean AllowsInvalid) {
		NumberFormatter formatter = new NumberFormatter();
		formatter.setValueClass(Integer.class);
		formatter.setMinimum(min);
		formatter.setMaximum(Integer.MAX_VALUE);
		formatter.setAllowsInvalid(AllowsInvalid);
		formatter.setCommitsOnValidEdit(true);
		return formatter;
	}

	

	@Override
	public void actionPerformed(ActionEvent ae) {
			
		PolygonGenerator polygonGenerator = (PolygonGenerator)shapeMenu.getSelectedItem();
		int sides = 0;
		
		if (sidesNumber.isEnabled()) {
			sides = (sidesNumber.getValue()==null)?0:(int)sidesNumber.getValue();
			((CustomShape)polygonGenerator).setSidesNumber(sides);
		}
		
		
		Rule rule = (Rule)ruleMenu.getSelectedItem();
		
		int iterations = (iterationsNumber.getValue() ==null)?0:(int)iterationsNumber.getValue();
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new PolygonWindow(polygonGenerator, rule, iterations);
				} catch (LessThanTowSidesException e) {
					JOptionPane.showMessageDialog(null,
							("The number of sides is: " + e.getSidesNumber() + "\nA polygon must have at least 3 sides!"),
							"Less than tow sides error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});


	}

	
	private void guiSetEmbleDisableItems() {
		((ShapeActionOnSelectionVisitor)shapeMenu.getSelectedItem()).accept(this);
	}


	
	@Override
	public void visitTriangle() {
		disableSideLabelAndTextField();
		ruleMenu.setSelectedIndex(0); // this only works as intended if the rules passed are in this order [0]=TriangleRule, [1]SquareRule
	}

	@Override
	public void visitSquare() {
		disableSideLabelAndTextField();
		ruleMenu.setSelectedIndex(1); // this only works as intended if the rules passed are in this order [0]=TriangleRule, [1]SquareRule
		
	}

	@Override
	public void visitCustomShape() {
		sidesLabel.setEnabled(true);
		sidesNumber.setEnabled(true);
		ruleMenu.setSelectedIndex(1); // this only works as intended if the rules passed are in this order [0]=TriangleRule, [1]SquareRule
	}
	
	
	
	private void disableSideLabelAndTextField() {
		sidesLabel.setEnabled(false);
		sidesNumber.setEnabled(false);
		sidesNumber.setText("");
	}




}









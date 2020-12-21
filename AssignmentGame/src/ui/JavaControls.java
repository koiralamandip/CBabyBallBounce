/**
Program: Greenfoot Simulation Engine: Assignment 2 Java
Filename: JavaControls.java
@author: Mandeep Koirala
Course: BSc HONS Computing Year 1
Module: CSY1020 Problem Solving & Programming
Tutor: Lokesh Gupta
@version: 2.0
Date: 07/29/2017
*/

// package of the class
package ui;

// Importing packages and classes
import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;

import javax.swing.*;

import assets.*;
import engine.CBabyBallBounce;
import engine.Item;

/*
 * JavaControls class: Used to create right side controls and components to the simulation screen
 * Declaring public and static variables to store the panels, digital time fields, OPTION, SQUARE, DIRECTION fields
 * Similarly, SCORE fields and labels, COMPASS direction button, arrow buttons, Game Mode Buttons and EXIT button for the game.
 */
public class JavaControls {
	static JPanel timeScorePanel, optionSquareDirectionPanel, arrowButtonsPanel, extrasPanel;
	static JLabel timerLabel, delimiterLabel1, delimiterLabel2, scoreLabel, leftRightScore, optionLabel, squareLabel, directionLabel;
	public static JTextField  optionBox, squareBox, directionBox;
	public static JTextField blackTextBoxes[] = {new JTextField("00", 2), new JTextField("00", 2), new JTextField("00", 2), new JTextField("00", 2), new JTextField("00", 2)};
	static JButton centerButton, upArrow, leftArrow, rightArrow, downArrow;
	static JButton controlsNull[] = {new JButton(), new JButton(), new JButton(), new JButton()} ;
	public static JButton coordinateButton, twoPlayerButton, fourPlayerButton, multiPlayerButton, exitButton;	
	
	// Creating objects of Baby1, Baby2 and Blocker globally to use/add them in the Room once the "4 Player" or " MultiPlayer" mode is trigerred. 
	static Baby1 baby1b = new Baby1(3, 3);
	static Baby1 baby1c = new Baby1(3, 9);
	static Baby2 baby2b = new Baby2(12, 3);
	static Baby2 baby2c = new Baby2(12, 9);
	static Blocker blocker1a = new Blocker(3, 6, new ImageIcon("src/resources/blocker1.png"));
	static Blocker blocker1b = new Blocker(12, 6, new ImageIcon("src/resources/blocker1.png"));
	static Blocker blocker2a = new Blocker(5, 3, new ImageIcon("src/resources/blocker2.png"));
	static Blocker blocker2b = new Blocker(10, 9, new ImageIcon("src/resources/blocker2.png"));
	static Blocker blocker3a = new Blocker(5, 9, new ImageIcon("src/resources/blocker3.png"));
	static Blocker blocker3b = new Blocker(10, 3, new ImageIcon("src/resources/blocker3.png"));
	
	/*
	 * Calls 3 different methods for displaying game components to the screen
	 */
	public static void components(JPanel rootPanel){
		first(rootPanel);
		second(rootPanel);
		third(rootPanel);
		fourth(rootPanel);
	}
	
	/*
	 * First panel, includes digital timer, scores and their labels in the game
	 * Setting up those components and their properties
	 */
	private static void first(JPanel rootPanel){
		timeScorePanel = new JPanel();
		timeScorePanel.setLayout(new FlowLayout());
		timeScorePanel.setPreferredSize(new Dimension(rootPanel.getWidth()-10, rootPanel.getHeight() /5));
		
		timerLabel = new JLabel("DIGITAL TIMER",SwingConstants.CENTER); // Centering the timer Label in the panel
		timerLabel.setPreferredSize(new Dimension(150,15));
		
		for (JTextField textBox : blackTextBoxes){
			textBox.setBackground(Color.black);
			textBox.setForeground(Color.white);
		}
		
		delimiterLabel1 = new JLabel(":");
		delimiterLabel2 = new JLabel(":");
		scoreLabel = new JLabel("SCORE", SwingConstants.CENTER);
		scoreLabel.setPreferredSize(new Dimension(150,15));
		leftRightScore = new JLabel(" < L: R > ");
			
		// Adding components to the panel
		timeScorePanel.add(timerLabel);
		timeScorePanel.add(blackTextBoxes[0]);
		timeScorePanel.add(delimiterLabel1);
		timeScorePanel.add(blackTextBoxes[1]);
		timeScorePanel.add(delimiterLabel2);
		timeScorePanel.add(blackTextBoxes[2]);
		timeScorePanel.add(scoreLabel);
		timeScorePanel.add(blackTextBoxes[3]);
		timeScorePanel.add(leftRightScore);
		timeScorePanel.add(blackTextBoxes[4]);
		
		// Adding panel to root panel
		rootPanel.add(timeScorePanel);

	}
	
	/*
	 * Second panel, includes OPTION, DIRECTION, SQUARE labels and text fields
	 * Setting up those components and their properties
	 */
	private static void second(JPanel rootPanel){
		optionSquareDirectionPanel = new JPanel();
		optionSquareDirectionPanel.setPreferredSize(new Dimension(rootPanel.getWidth()-10, rootPanel.getHeight() /6));
		
		optionLabel = new JLabel("Option:");
		optionLabel.setPreferredSize(new Dimension(60,20));
		optionBox = new JTextField("2 Player");
		optionBox.setPreferredSize(new Dimension(75,20));
		squareLabel = new JLabel("Square:");
		squareLabel.setPreferredSize(new Dimension(60,20));
		squareBox = new JTextField("101");
		squareBox.setPreferredSize(new Dimension(75,20));
		directionLabel = new JLabel("Direction:");
		directionLabel.setPreferredSize(new Dimension(60,20));
		directionBox = new JTextField("SE");
		directionBox.setPreferredSize(new Dimension(75,20));
		
		// Adding components to panel
		optionSquareDirectionPanel.add(optionLabel);
		optionSquareDirectionPanel.add(optionBox);
		optionSquareDirectionPanel.add(squareLabel);
		optionSquareDirectionPanel.add(squareBox);
		optionSquareDirectionPanel.add(directionLabel);
		optionSquareDirectionPanel.add(directionBox);
		
		// Adding panel to root panel
		rootPanel.add(optionSquareDirectionPanel);
	}
	
	/*
	 * Third panel, includes ARROW BUTTONS, COMAPSS BUTTON
	 * Setting up those components and their properties
	 */
	private static void third(JPanel rootPanel){
		arrowButtonsPanel = new JPanel();
		arrowButtonsPanel.setLayout(new GridLayout(3,3));
		arrowButtonsPanel.setPreferredSize(new Dimension(rootPanel.getWidth()-10, rootPanel.getHeight() /5));
		
		for ( JButton buttonNull : controlsNull){
			buttonNull.setEnabled(false);
			buttonNull.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(2,2,2,2),BorderFactory.createLoweredSoftBevelBorder()));
		}
		
		centerButton = new JButton(new ImageIcon("src/resources/ball.png"));
		
		/*
		 * Up Arrow
		 * Notify that the button is pressed and sets up clicked to true
		 * Calls kickBall() method ( in CBabyBallBounce.class )
		 */
		upArrow = new JButton("△");
		upArrow.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				CBabyBallBounce.buttonControl = true;
				CBabyBallBounce.upPressed = true;
				CBabyBallBounce.kickBall();
			}
		});
		
		/*
		 * Down Arrow
		 * Notify that the button is pressed and sets down clicked to true
		 * Calls kickBall() method ( in CBabyBallBounce.class )
		 */
		downArrow = new JButton("▽");
		downArrow.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				CBabyBallBounce.buttonControl = true;
				CBabyBallBounce.downPressed = true;
				CBabyBallBounce.kickBall();
			}
		});
		
		/*
		 * Right Arrow
		 * Notify that the button is pressed and sets right clicked to true
		 * Calls kickBall() method ( in CBabyBallBounce.class )
		 */
		rightArrow = new JButton("▷");
		rightArrow.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				CBabyBallBounce.buttonControl = true;
				CBabyBallBounce.rightPressed = true;
				CBabyBallBounce.kickBall();
			}
		});
		
		/*
		 * Left Arrow
		 * Notify that the button is pressed and sets left clicked to true
		 * Calls kickBall() method ( in CBabyBallBounce.class )
		 */
		leftArrow = new JButton("◁");
		leftArrow.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				CBabyBallBounce.buttonControl = true;
				CBabyBallBounce.leftPressed = true;
				CBabyBallBounce.kickBall();
			}
		});
		
		// Assigning the arrows to an array to give common properties to them without repetition of codes. 
		JButton moveButtons[] = {upArrow, leftArrow, centerButton, rightArrow, downArrow};
		for (JButton mover : moveButtons){
			mover.setBackground(Color.white);
			mover.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(2,2,2,2),BorderFactory.createEtchedBorder()));
		}
		
		// Add control buttons to the panel
		arrowButtonsPanel.add(controlsNull[0]);
		arrowButtonsPanel.add(upArrow);
		arrowButtonsPanel.add(controlsNull[1]);
		arrowButtonsPanel.add(leftArrow);
		arrowButtonsPanel.add(centerButton);
		arrowButtonsPanel.add(rightArrow);
		arrowButtonsPanel.add(controlsNull[2]);
		arrowButtonsPanel.add(downArrow);
		arrowButtonsPanel.add(controlsNull[3]);
		
		// Add panel to root panel
		rootPanel.add(arrowButtonsPanel);
	}
	

	/*
	 * Fourth panel, includes COMPASS BUTTON and GAME PLAY STATE BUTTON and EXIT button
	 * Setting up those components and their properties
	 */
	private static void fourth(JPanel rootPanel){
		extrasPanel = new JPanel();
		extrasPanel.setPreferredSize(new Dimension(rootPanel.getWidth()-10, rootPanel.getHeight() /3 + 20));
		
		// Compass direction button
		coordinateButton = new JButton(new ImageIcon("src/resources/west.jpg"));
		coordinateButton.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(),BorderFactory.createEmptyBorder(7,5,7,5)));
		coordinateButton.setBackground(Color.white);
		
		/*
		 * 2Player mode button
		 * Removes all additional Items from the room and sets up the 2 PLayer mode.
		 * 2 Player mode = BASIC level (supposed)
		 * Checks if the Items are in the Room before removing them to prevent errors.
		 */
		
		twoPlayerButton = new JButton("2 Player");
		twoPlayerButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Ball1 ball = (Ball1) Item.getRoom().getObjects(Ball1.class).get(0);
				ball.level = "basic";
				optionBox.setText("2 Player");
				
				if (Item.getRoom().getObjects(Baby1.class).size() != 1){
					Item.getRoom().removeObject(baby1b);
					Item.getRoom().removeObject(baby1c);
					Item.getRoom().removeObject(baby2b);
					Item.getRoom().removeObject(baby2c);
					if (Item.getRoom().getObjects(Blocker.class).size() == 2){
						Item.getRoom().removeObject(blocker1a);
						Item.getRoom().removeObject(blocker1b);
					}
					else if (Item.getRoom().getObjects(Blocker.class).size() == 6){
						Item.getRoom().removeObject(blocker1a);
						Item.getRoom().removeObject(blocker1b);
						Item.getRoom().removeObject(blocker2a);
						Item.getRoom().removeObject(blocker2b);
						Item.getRoom().removeObject(blocker3a);
						Item.getRoom().removeObject(blocker3b);
					}
				}
			}
		});
		
		
		/*
		 * 4Player mode button
		 * Adds additional Items from the room and sets up the 4 PLayer mode.
		 * 4 Player Mode = INTERMEDIATE level (supposed)
		 * Checks if the Items are already in the Room or not before adding them.
		 */
		fourPlayerButton = new JButton("4 Player");
		fourPlayerButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Ball1 ball = (Ball1) Item.getRoom().getObjects(Ball1.class).get(0);
				ball.level = "intermediate";
				optionBox.setText("4 Player");
				
				if (Item.getRoom().getObjects(Baby1.class).size() == 1){
					Item.getRoom().addObject(baby1b);
					Item.getRoom().addObject(baby1c);
					Item.getRoom().addObject(baby2b);
					Item.getRoom().addObject(baby2c);

				}
				
				if (Item.getRoom().getObjects(Blocker.class).size() == 0){
					Item.getRoom().addObject(blocker1a);
					Item.getRoom().addObject(blocker1b);
				}
				
				if (Item.getRoom().getObjects(Blocker.class).size() == 6){
					Item.getRoom().removeObject(blocker2a);
					Item.getRoom().removeObject(blocker2b);
					Item.getRoom().removeObject(blocker3a);
					Item.getRoom().removeObject(blocker3b);
				}
			}
		});
		
		/*
		 * Multi Player mode button
		 * Adds additional Items from the room and sets up the Multi PLayer mode.
		 * Multi Player Mode = ADVANCED level (supposed)
		 * Checks if the Items are already in the Room or not before adding them.
		 */
		multiPlayerButton = new JButton("Multi");
		multiPlayerButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Ball1 ball = (Ball1) Item.getRoom().getObjects(Ball1.class).get(0);
				ball.level = "advanced";
				optionBox.setText("MultiPlayer");
				
				if (Item.getRoom().getObjects(Baby1.class).size() == 1){
					Item.getRoom().addObject(baby1b);
					Item.getRoom().addObject(baby1c);
					Item.getRoom().addObject(baby2b);
					Item.getRoom().addObject(baby2c);
				}
				
				if (Item.getRoom().getObjects(Blocker.class).size() == 0){
					Item.getRoom().addObject(blocker1a);
					Item.getRoom().addObject(blocker1b);
					Item.getRoom().addObject(blocker2a);
					Item.getRoom().addObject(blocker2b);
					Item.getRoom().addObject(blocker3a);
					Item.getRoom().addObject(blocker3b);
				}
				else if (Item.getRoom().getObjects(Blocker.class).size() <= 2){
					Item.getRoom().addObject(blocker2a);
					Item.getRoom().addObject(blocker2b);
					Item.getRoom().addObject(blocker3a);
					Item.getRoom().addObject(blocker3b);
				}
			}
		});
		
		/*
		 * Exit buttons
		 * Exits the simulation screen on click
		 */
		exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(-1);
			}
		});
		
		JButton modes[] = {twoPlayerButton, fourPlayerButton, multiPlayerButton, exitButton};
		
		// Adding buttons to panel
		extrasPanel.add(coordinateButton);
		for ( JButton buttonMode : modes){
			buttonMode.setBackground(Color.white);
			buttonMode.setBorder(BorderFactory.createEtchedBorder());
			buttonMode.setPreferredSize(new Dimension(65,30));
			extrasPanel.add(buttonMode);
		}
		
		// Adding panel to root panel
		rootPanel.add(extrasPanel);
	}
}

/**
Program: Greenfoot Simulation Engine: Assignment 2 Java
Filename: GreenfootWorkArea.java
@author: Mandeep Koirala
Course: BSc HONS Computing Year 1
Module: CSY1020 Problem Solving & Programming
Tutor: Lokesh Gupta
@version: 2.0
Date: 07/29/2017
*/

// Package of the class
package ui;

// Importing necessary packages and classes
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.sun.glass.ui.Timer;

import java.util.*;

import assets.Ball1;
import assets.MyRoom;
import engine.CBabyBallBounce;
import engine.Item;
import engine.Room;
import engine.core.ControlPanel;

/*
 * Class GreenfootWorkArea: Sets up the inner components of the screen.
 * All the JPanels and JButtons and other components are assigned to the screen with the hep of this method
 */
public class GreenfootWorkArea {
	public static boolean pausableState = false; // Checks if the game is running or paused
	public static JPanel mainScreen, controlScreen, runScreen; // Three JPanel components for main screen, right control screen and bottom controls
	public static JPanel mainScreenWorkArea; // The main panel to add game objects to. Visualization of WORLD in greenfoot. 
	static JPanel runScreenButtonPanel, runScreenSliderPanel; 
	static JButton actButton, runPauseButton, resetButton; // The three greenfoot buttons
	static JLabel speedText;
	static JSlider speedSlider; // JSlider for controlling speeds for the game
	public static java.util.Timer t, timeDigital; // Timers to execute the game codes periodically and simultaneously..
	public static int speed = 3; // Initial speed count for the game play.
	private static int secondTime, minuteTime, hourTime;
	private static ControlPanel keyListenPanel = new ControlPanel(); // Creating keyListener object from the class ControlPanel.class
	
	/**
	 * Creates the inner panels and components for the screen 
	 * @param area, The main container for the simulation screen
	 */
	public static void createIn(Container area){
		area.setLayout(null); // Null layout for the main container. Specify location and size to place components in it.
		
		controlScene(); // Right side control parts are created through this function 
		mainScene(); // Creates the main screen of the simulation environment
		runScene(); // Creates panels for putting ACT, RUN, RESET buttons and SLIDER
		
		// Add three main panels of the simulation screen to the main container
		area.add(mainScreen);
		area.add(controlScreen);
		area.add(runScreen);
	}
	
	/*
	 * Creates the main scene for the simulation
	 * Setting up size and location of the panel and inner components
	 * Setting gridLayout of 13 rows and 16 columns for the main world (Room.java, here) panel.
	 */
	private static void mainScene(){
		mainScreen = new JPanel();
		mainScreen.setSize(new Dimension(650,480));
		mainScreen.setLocation(5,2);
		mainScreen.setLayout(null);
		mainScreen.setBorder(BorderFactory.createEtchedBorder());
		
		mainScreenWorkArea = new JPanel();
		mainScreenWorkArea.setLayout(new GridLayout(13, 16));
		mainScreenWorkArea.setBounds(20,10,mainScreen.getWidth()- 40, mainScreen.getHeight() - 20);
		mainScreenWorkArea.setBackground(Color.white);
		mainScreenWorkArea.setFocusable(true);
		mainScreenWorkArea.setBorder(BorderFactory.createLineBorder(Color.black));
		MyRoom room = new MyRoom(); // Calling MyRoom class to initialize its components and execute them
		mainScreen.add(mainScreenWorkArea); // Adding the main screen panel to main panel
		
	}
	
	// Creating panels and components for the right nav controls
	private static void controlScene(){
		controlScreen = new JPanel();
		controlScreen.setSize(new Dimension(155,480));
		controlScreen.setLocation(660,2);
		controlScreen.setLayout(new FlowLayout());
		controlScreen.setBorder(BorderFactory.createEtchedBorder());
		
		JavaControls.components(controlScreen);
	}
	
	/** 
	 * @param String s: Used to indicate whether the arrow buttons should be disabled or not.
	 * If s is passed "disable", this method disables the arrow controls and enables them on passing "enable" / anything except "disable"
	 */
	private static void disableArrows(String s){
		if (s.equalsIgnoreCase("disable")){
			JavaControls.upArrow.setEnabled(false);
			JavaControls.leftArrow.setEnabled(false);
			JavaControls.centerButton.setEnabled(false);
			JavaControls.rightArrow.setEnabled(false);
			JavaControls.downArrow.setEnabled(false);
		}
		else{
			JavaControls.upArrow.setEnabled(true);
			JavaControls.leftArrow.setEnabled(true);
			JavaControls.centerButton.setEnabled(true);
			JavaControls.rightArrow.setEnabled(true);
			JavaControls.downArrow.setEnabled(true);
		}
	}
	
	/**
	 * This method creates the panel to store run, act and reset button and also the slider. 
	 * This method also contains the functionalities for those components.. 
	 */
	private static void runScene(){
		runScreen = new JPanel();
		runScreen.setLayout(new FlowLayout());
		runScreen.setSize(new Dimension(810,50));
		runScreen.setLocation(5,482);
		runScreen.setBorder(BorderFactory.createEtchedBorder());
		
		runScreenButtonPanel = new JPanel();
		runScreenButtonPanel.setLayout(new FlowLayout());
		runScreenButtonPanel.setPreferredSize(new Dimension(450,35));
		
		actButton = new JButton("Act", new ImageIcon("src/resources/step.png"));
		actButton.setBackground(Color.white);
		
		/*
		 * Act button action event..
		 * Cancels the previous run timer (if any)
		 * Disables the running state because the game should no longer continue in loop while pressing Act button
		 * Enables the clicking of arrow buttons (up, down, left, right)
		 * Calls the kickBall() method afterwards which can be found in CBabyBallBouce.class
		 */
		actButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (t != null){
					t.cancel();
					pausableState = false;
					runPauseButton.setIcon(new ImageIcon("src/resources/run.png"));
					runPauseButton.setText("Run");
					disableArrows("enable");
				}
				CBabyBallBounce.kickBall();
			}
		});
		
		runScreenButtonPanel.add(actButton); // Add Act button to a panel.
		runScreenButtonPanel.setFocusable(true); // Set the focusable state to the panel.

		// Using focus listener to give the panel back its focus once it is lost. So that the key presses would work.
		// Key presses needed focus to operate. 
		runScreenButtonPanel.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				runScreenButtonPanel.requestFocusInWindow(); // Getting back the focus to runScreenButtonPanel... (Oracle, 1995)
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

		
		// Setting up run/pause button and its properties
		runPauseButton = new JButton("Run",new ImageIcon("src/resources/run.png"));
		runPauseButton.setBackground(Color.white);

		/*
		 * Action event for run button
		 * Sets pausable state to negative of itself. So if the game is paused, it starts and if it is running, it gets paused.
		 * Once the Run buttons is pressed, Key Listener is added to the panel containing these buttons. 
		 * Enables the reset button because the game could be reset once it is started playing. 
		 * Disables the arrow keys as they are supposed to be.
		 */
		runPauseButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (e.getSource() == runPauseButton){
					pausableState = !pausableState;
					if (pausableState){
						runScreenButtonPanel.addKeyListener(keyListenPanel);
						runScreenButtonPanel.requestFocusInWindow();
						resetButton.setEnabled(true);
						runPauseButton.setIcon(new ImageIcon("src/resources/pause.png"));
						runPauseButton.setText("Pause");
						disableArrows("disable");
						
						// Cancels the main game timer if already being used and resets it (below), so as to disable repeated timer call.
						if (t != null){
							t.cancel();
						}
						
						// Cancels the digital timer schedule once run button is paused so as to pause the digital time.
						if (timeDigital != null){
							timeDigital.cancel();
						}
						
						// Reassigns timer for the continuous running of the game 
						t = new java.util.Timer();
						
						/**
						 * Schedules the timer call after certain interval
						 * Interval here depends upon the 'speed' value and also with the change in slider's value.
						 * Activates the move() method of every game Items added to the game periodically and also updates the Room,
						 * to make movements and visualize the changes in the Room.
						 * (Oracle, 1999)  
						 */
						t.scheduleAtFixedRate(new TimerTask(){
							@Override
							public void run() {
								// TODO Auto-generated method stub
								
								for (Item actor : Item.getRoom().actorCollection){
									actor.move();			
								}
								Item.getRoom().update();
								
							}
							
						}, 0 , 300 - (speed * 58));
						
						// Reassigns the digital time timer.
						timeDigital = new java.util.Timer();
						/*
						 * Increases the seconds count and also the minutes and hour count accordingly.
						 * Update current time count to their respective JTextFields.
						 * This timer is set at 1s interval, so that the seconds count goes fine.
						 */
						timeDigital.scheduleAtFixedRate(new TimerTask() {
							
							@Override
							public void run() {
								// TODO Auto-generated method stub
								secondTime++;
								if (minuteTime == 60){
									hourTime += 1;
									minuteTime = 0;
								}
								
								if (secondTime == 60){
									minuteTime += 1;
									secondTime = 0;
								}
								
								// Updating the time counts to JTextFields
								JavaControls.blackTextBoxes[2].setText(String.valueOf(secondTime));
								JavaControls.blackTextBoxes[1].setText(String.valueOf(minuteTime));
								JavaControls.blackTextBoxes[0].setText(String.valueOf(hourTime));
							}
						}, 1000, 1000);
						
					}
					// Perform some actions if the run/pause button is pressed and the game is to be paused..
					else{
						runScreenButtonPanel.removeKeyListener(keyListenPanel); // Removes key listener from the panel. As game is paused, babies shouldn't move.
						runPauseButton.setIcon(new ImageIcon("src/resources/run.png")); // Resets the run button icon
						runPauseButton.setText("Run"); // and text
						disableArrows("enable"); // Enables the arrow keys as the game is paused..
						
						// Cancels the execution of codes in timers once the game is paused until it is run again
						t.cancel();
						timeDigital.cancel();
					}
				}
			}
		});
		
		runScreenButtonPanel.add(runPauseButton); // Add RUN/PAUSE button to its panel.
		
		// Initializing and setting up the RESET button.
		resetButton = new JButton("Reset", new ImageIcon("src/resources/reset.png"));
		resetButton.setBackground(Color.white);
		resetButton.setEnabled(false);
		
		/**
		 * Action Listener for reset button
		 * Resets the game components, sets the game state to 2 Player, cancels all the timers and resets the variables to start fresh again
		 */
		resetButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				pausableState = false;
				runScreenButtonPanel.removeKeyListener(keyListenPanel);
				resetButton.setEnabled(false);
				t.cancel();
				secondTime = minuteTime = hourTime = 0;
				JavaControls.blackTextBoxes[2].setText(String.valueOf(secondTime));
				JavaControls.blackTextBoxes[1].setText(String.valueOf(minuteTime));
				JavaControls.blackTextBoxes[0].setText(String.valueOf(hourTime));
				timeDigital.cancel();
				Ball1.scoreLeft = Ball1.scoreRight = 0;
				JavaControls.blackTextBoxes[3].setText("00");
				JavaControls.blackTextBoxes[4].setText("00");
				Item.getRoom().resetRoom(); // Resets the room components
				runPauseButton.setText("Run");
				runPauseButton.setIcon(new ImageIcon("src/resources/run.png"));
				disableArrows("enable");
			}
		});
		
		runScreenButtonPanel.add(resetButton); // Add reset button to its panel
		
		// Setting up the panel for keeping Slider
		runScreenSliderPanel = new JPanel();
		runScreenSliderPanel.setLayout(new FlowLayout());
		runScreenSliderPanel.setPreferredSize(new Dimension(300,35));
		
		speedText = new JLabel("Speed:"); // Label for the slider
		runScreenSliderPanel.add(speedText); // Add label to panel
		
		// Setting up the slider for use
		speedSlider = new JSlider(JSlider.HORIZONTAL, 1, 5, 3);
		speedSlider.setMajorTickSpacing(1);
		speedSlider.setMinorTickSpacing(1);
		speedSlider.setPaintTicks(true); // (Oracle, 1999)
		
		/**
		 * Change listener for the slider
		 * If the slider value gets changed, update the timing of the game loop according to the updated value.
		 * Cancels any previous timer of game loop and creates a new timer for the same with new update time interval value.
		 */
		speedSlider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				speed = speedSlider.getValue();
				if (t != null){
					t.cancel();
				}
				
				t = new java.util.Timer();
				t.scheduleAtFixedRate(new TimerTask(){
					@Override
					public void run() {
						for (Item actor : Item.getRoom().actorCollection){
							actor.move();			
						}
						Item.getRoom().update();
						
					}
					
				}, 0 , 300 - (speed * 58));		
			}
		});
		
		runScreenSliderPanel.add(speedSlider); // Add slider to the panel
		
		// Add panels to the screen
		runScreen.add(runScreenButtonPanel);
		runScreen.add(runScreenSliderPanel);
	}
}

/**
Program: Greenfoot Simulation Engine: Assignment 2 Java
Filename: GreenfootMenuBar.java
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

/*
 * Class GreenfootMenuBar: used to setup menubar, menus and menu items for the simulation screen
 */
public class GreenfootMenuBar {
	static JMenuBar menubar; // Menubar for the screen
	static JMenu scenarioMenu, editMenu, controlsMenu, helpMenu; // Different menus
	static JMenuItem strideProject, javaProject, openProject, saveProject, saveAsProject, quitProject; // Sub menus for File/Scenario
	static JMenuItem newClass, importClass, removeClass, convertClass, preferences; // Sub menus for Edit
	static JMenuItem actControls, runControls, pauseControls, resetControls, showSoundRecorder, saveWorld; // Sub menus for Controls
	static JMenuItem aboutGreenfoot, greenfootTutorial, greenfootDiscussion; // Sub menus for Help
	
	/**
	 * This method assigns JMenuBar to a variable and calls different methods to add different menu items in it.
	 * @param area The main container for the simulation environment. Gets passed when it is called from GreenfootSimulation.class
	 */
	public static void createIn(Container area){
		menubar = new JMenuBar();
		menubar.setBackground(Color.white);
		addMenu();
		addScenarioItems();
		addEditItems();
		addControlsItems();
		addHelpItems();		
	}

	/*
	 * Assigns Main Menus for the menubar
	 */
	private static void addMenu() {
		scenarioMenu = new JMenu("Scenario");
		editMenu = new JMenu("Edit");
		controlsMenu = new JMenu("Controls");
		helpMenu = new JMenu("Help");
	}
	
	/*
	 * Add sub-menus to Scenario Menu
	 * Setting up empty border of certain width to visualize margins
	 * Setting up key accelerators for the sub menu
	 */
	private static void addScenarioItems() {
		strideProject = new JMenuItem("New Stride Scenario...");
		strideProject.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 5));  
		strideProject.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,Event.CTRL_MASK)); 
		
		javaProject = new JMenuItem("New Java Scenario...");
		javaProject.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 5));
		javaProject.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J,Event.CTRL_MASK));
		
		openProject = new JMenuItem("Open");
		openProject.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 5));
		openProject.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,Event.CTRL_MASK));
		
		saveProject = new JMenuItem("Save");
		saveProject.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 5));
		saveProject.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,Event.CTRL_MASK));
		
		saveAsProject = new JMenuItem("Save As");
		saveAsProject.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 5));
		
		// Adding action listener for quit sub menu. It exits the program on click
		quitProject = new JMenuItem("Quit");
		quitProject.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(-1);
				
			}
		});
		
		quitProject.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 5));
		quitProject.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,Event.CTRL_MASK));

		// Adding the above configured sub menus to the Scenario menu
		scenarioMenu.add(strideProject);
		scenarioMenu.add(javaProject);
		scenarioMenu.add(openProject);
		scenarioMenu.add(saveProject);
		scenarioMenu.add(saveAsProject);
		scenarioMenu.addSeparator();
		scenarioMenu.add(quitProject);
		
		// Adding Scenario to menubar
		menubar.add(scenarioMenu);
	}

	/*
	 * Add sub-menus to Edit Menu
	 * Setting up empty border of certain width to visualize margins
	 * Setting up key accelerators for the sub menus
	 */
	private static void addEditItems() {
		newClass = new JMenuItem("New Class...");
		newClass.setBorder(BorderFactory.createEmptyBorder(5,20,5,5));
		newClass.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Event.CTRL_MASK));
		
		importClass = new JMenuItem("Import Class...");
		importClass.setBorder(BorderFactory.createEmptyBorder(5,20,5,5));
		importClass.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, Event.CTRL_MASK));
		
		removeClass = new JMenuItem("Remove Class");
		removeClass.setBorder(BorderFactory.createEmptyBorder(5,20,5,5));
		removeClass.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, Event.CTRL_MASK));
		removeClass.setEnabled(false); // Disabling the menu item from any events
		
		convertClass = new JMenuItem("Convert class to Java");
		convertClass.setBorder(BorderFactory.createEmptyBorder(5,20,5,5));
		convertClass.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, Event.CTRL_MASK));
		convertClass.setEnabled(false); // Disabling the menu item from any events 
		
		preferences = new JMenuItem("Preferences");
		preferences.setBorder(BorderFactory.createEmptyBorder(5,20,5,5));
		preferences.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_COMMA, Event.CTRL_MASK));
		
		// Adding the above configured sub menus to Edit menu
		editMenu.add(newClass);
		editMenu.add(importClass);
		editMenu.add(removeClass);
		editMenu.add(convertClass);
		editMenu.addSeparator(); // Adding a line of separation between two sub menus to differentiate sub categories
		editMenu.add(preferences);
		
		// Adding Edit menu to the main menubar
		menubar.add(editMenu);

	}

	/*
	 * Add sub-menus to Controls Menu
	 * Setting up empty border of certain width to visualize margins
	 * Setting up key accelerators for the sub menus
	 */
	private static void addControlsItems() {
		actControls = new JMenuItem("Act", new ImageIcon("src/resources/step.png"));
		actControls.setBorder(BorderFactory.createEmptyBorder(5,0,5,5));
		actControls.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, Event.CTRL_MASK));
		
		runControls = new JMenuItem("Run", new ImageIcon("src/resources/run.png"));
		runControls.setBorder(BorderFactory.createEmptyBorder(5,0,5,5));
		runControls.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, Event.CTRL_MASK));
		
		pauseControls = new JMenuItem("Pause", new ImageIcon("src/resources/pause.png"));
		pauseControls.setEnabled(false);
		pauseControls.setBorder(BorderFactory.createEmptyBorder(5,0,5,5));
		pauseControls.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, Event.CTRL_MASK + Event.SHIFT_MASK));
		
		resetControls = new JMenuItem("Reset", new ImageIcon("src/resources/reset.png"));
		resetControls.setBorder(BorderFactory.createEmptyBorder(5,0,5,5));
		resetControls.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, Event.CTRL_MASK));
		
		showSoundRecorder = new JMenuItem("Show Sound Recorder");
		showSoundRecorder.setBorder(BorderFactory.createEmptyBorder(5,0,5,5));
		showSoundRecorder.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, Event.CTRL_MASK));
		
		saveWorld = new JMenuItem("Save the World");
		saveWorld.setBorder(BorderFactory.createEmptyBorder(5,0,5,5));
		
		// Adding the above configured sub menus to Controls menu
		controlsMenu.add(actControls);
		controlsMenu.add(runControls);
		controlsMenu.add(pauseControls);
		controlsMenu.add(resetControls);
		controlsMenu.addSeparator();
		controlsMenu.add(showSoundRecorder);
		controlsMenu.add(saveWorld);
		
		// Adding Controls menu to the main menubar
		menubar.add(controlsMenu);
	}

	/*
	 * Add sub-menus to Help Menu
	 * Setting up empty border of certain width to visualize margins
	 * Setting up key accelerators for the sub menus
	 */
	private static void addHelpItems() {
		aboutGreenfoot = new JMenuItem("About Greenfoot...");
		aboutGreenfoot.setBorder(BorderFactory.createEmptyBorder(5,20,5,5));
		
		// Setting up key event for "ABOUT" section.
		aboutGreenfoot.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// Shows the following text in message box when clicked on ABOUT sub menu...
				JOptionPane.showMessageDialog(GreenfootWorkArea.mainScreen, "Program: Greenfoot Simulation Engine\nVersion: v1.0.1.1\nAuthor: Mandeep Koirala\n----------------------------------------------\nhttps://www.***greenfootsimuleng***.com\n----------------------------------------------\nGreenfoot Simulation Engine is a open source software designed for indie begineers\n to develop simple and easy games at no time cost.\n\n See our Help section for examples\n OR visit https://www.***greenfootsimuleng***.com for more information.");
			}
		});
		
		greenfootTutorial = new JMenuItem("Greenfoot Help");
		greenfootTutorial.setBorder(BorderFactory.createEmptyBorder(5,20,5,5));
		
		// Setting up key event to "TUTORIAL" section..
		greenfootTutorial.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// Shows the following text in message box when clicked on TUTORIAL sub menu...
				JOptionPane.showMessageDialog(GreenfootWorkArea.mainScreen, "Help Tutorials are being developed for offline usage.\n Please visit https://www.***greenfootsimuleng***.com till then.");
			}
		});
		
		greenfootDiscussion = new JMenuItem("Greenfoot Discussion");
		greenfootDiscussion.setBorder(BorderFactory.createEmptyBorder(5,20,5,5));
		
		// Adding above configured sub menus to the HELP menu
		helpMenu.add(aboutGreenfoot);
		helpMenu.add(greenfootTutorial);
		helpMenu.addSeparator();
		helpMenu.add(greenfootDiscussion);
		
		// Adding Help menu to the main menu
		menubar.add(helpMenu);
	}
	
	/**
	 * Getter method to get access to the menubar
	 * @return menubar: The menubar of the ssimulation screen
	 */
	public static JMenuBar getMenu(){
		return menubar;
	}
	
}
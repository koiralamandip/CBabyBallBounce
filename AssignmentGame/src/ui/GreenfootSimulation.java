/**
Program: Greenfoot Simulation Engine: Assignment 2 Java
Filename: GreenfootSimulation.java
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
 * GreenfootSimulation class extends JFrame
 * Used to set a frame for greenfoot simulation
 */
public class GreenfootSimulation extends JFrame {

	/*
	 * Constructor for the class
	 */
	public GreenfootSimulation(){
		
	}
	
	/*
	 * Sets necessary details for the simulation of Greenfoot interface
	 */
	public void createScreen() {
		// Set the title of the main JFrame
		setTitle("CBabyBallBounce - Baby Ball Bounce Application");
		// Sets the size of the simulation screen to 825 X 585
		setSize(825,585);
		// Sets the location of the simulation to the center of screen
		setLocationRelativeTo(null);
		// Used to totally close the program when close action is performed
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// Sets the simulation screen unresizable
		setResizable(false);
		setFocusable(true);
		// Sets the logo icon for the simulation screen
		setIconImage(new ImageIcon("src/resources/greenfoot.png").getImage());
		// Create other panels and components inside the simulation screen
		createComponents();
		// Sets menubar for the simulation screen
		setJMenuBar(GreenfootMenuBar.getMenu());
		// Makes the simulation screen visible
		setVisible(true);
	}
	
	/*
	 * This method creates other necessary components inside the simulation screen
	 */
	private void createComponents(){
		// Sets the main container for the simulation screen
		Container greenfootArea = this.getContentPane();
		// Calls GreenfootMenuBar class's createIn() method to create menubar
		GreenfootMenuBar.createIn(greenfootArea);
		// Calls GreenfootWorkArea class's createIn() method to create work areas
		GreenfootWorkArea.createIn(greenfootArea);
	}
}

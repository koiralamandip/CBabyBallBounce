/**
Program: Greenfoot Simulation Engine: Assignment 2 Java
Filename: ShowInfo.java
@author: Mandeep Koirala
Course: BSc HONS Computing Year 1
Module: CSY1020 Problem Solving & Programming
Tutor: Lokesh Gupta
@version: 2.0
Date: 07/29/2017
*/

package engine;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.*;

import ui.GreenfootWorkArea;

/*
 * ShowInfo class extending JFrame class
 * Shows up a frame and adds the details of passed object of Item (from which it is triggered)
 */
public class ShowInfo extends JFrame{
	private ImageIcon displayIcon; // Icon of the object
	private int xTemp, yTemp; // Initial X and Y positions of the object
	private String commonName; // Common Name (Class Name) of the object
	
	/*
	 * Constructor that assigns above variables to actual object's data 
	 */
	public ShowInfo(Item obj){
		this.displayIcon = obj.getPhoto();
		this.xTemp = obj.xTemp;
		this.yTemp = obj.yTemp;
		this.commonName = obj.uniqueName;
		
		showGUI(obj); // to show the GUI screen for the details
	}
	
	/**
	 * Sets up for the display of the JFrame
	 * @param obj : The Item object passed
	 */
	private void showGUI(Item obj){
		setTitle(obj.uniqueName + " Info");
		setAlwaysOnTop(true);
		setSize(GreenfootWorkArea.mainScreen.getWidth() - 400, GreenfootWorkArea.mainScreen.getHeight() - 150);
		setLocationRelativeTo(GreenfootWorkArea.mainScreen); // Centers the Frame in mainScreen Panel
		showComponents(obj); // Sets up container and JLables and JTextFields to show the details to the user 
		setVisible(true); // Sets the JFrame visible
	}
	
	/**
	 * Sets up container and JLables and JTextFields to show the details to the user
	 * @param obj : The Item object passed
	 */
	private void showComponents(Item obj){
		Container container = this.getContentPane();
		container.setLayout(new GridLayout(2, 1));
		JButton btnIcon = new JButton(displayIcon);
		btnIcon.setBorder(BorderFactory.createEmptyBorder());
		btnIcon.setBackground(Color.white);
		JPanel panel = new JPanel();
		panel.setBackground(Color.white);
		panel.setBorder(BorderFactory.createEmptyBorder(2, 20, 2, 20));
		panel.setLayout(new GridLayout(6, 2));
		
		JLabel xLabel = new JLabel("X :");
		JTextField xField = new JTextField(String.valueOf(obj.getX()));
		JLabel yLabel = new JLabel("Y :");
		JTextField yField = new JTextField(String.valueOf(obj.getY()));
		JLabel iniXLabel = new JLabel("Initial X :");
		JTextField iniXField = new JTextField(String.valueOf(xTemp));
		JLabel iniYLabel = new JLabel("Initial Y :");
		JTextField iniYField = new JTextField(String.valueOf(yTemp));
		JLabel commonLabel = new JLabel("Common Name :");
		JTextField commonField = new JTextField(commonName);
		JLabel rotationLabel = new JLabel("Rotation Angle :");
		JTextField rotationField = new JTextField(String.valueOf(obj.getRotation()));
		
		// Add components to panel
		panel.add(xLabel);
		panel.add(xField);
		panel.add(yLabel);
		panel.add(yField);
		panel.add(iniXLabel);
		panel.add(iniXField);
		panel.add(iniYLabel);
		panel.add(iniYField);
		panel.add(commonLabel);
		panel.add(commonField);
		panel.add(rotationLabel);
		panel.add(rotationField);
		
		// Add panels to container
		container.add(btnIcon);
		container.add(panel);
	}
}

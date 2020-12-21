/**
Program: Greenfoot Simulation Engine: Assignment 2 Java
Filename: Engine.java
@author: Mandeep Koirala
Course: BSc HONS Computing Year 1
Module: CSY1020 Problem Solving & Programming
Tutor: Lokesh Gupta
@version: 2.0
Date: 07/29/2017
*/

package engine;
import java.awt.event.KeyEvent;
import java.util.Random;

import engine.core.ControlPanel;

/**
 * Class Engine
 * Includes basic functionality for game
 */
public class Engine {
	
	/**
	 * Returns any random number from 0 to supplied number
	 * @param number
	 * @return randomNumber
	 */
	public static int getRandomNumber(int number){
		Random randomizer = new Random();
		int randomNumber = randomizer.nextInt(number);
		return randomNumber;
	}
	
	/**
	 * Returns true if certain key is pressed
	 * @param keyString
	 * @return status 
	 */
	public static boolean isKeyPressed(String keyString){
		boolean status = false;
		keyString = keyString.toUpperCase();
		if (keyString.equals(ControlPanel.keyString)){
			ControlPanel.keyString = "";
			status = true;
		}
		return status;
	}
}

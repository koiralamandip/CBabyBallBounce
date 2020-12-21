/**
Program: Greenfoot Simulation Engine: Assignment 2 Java
Filename: Blocker.java
@author: Mandeep Koirala
Course: BSc HONS Computing Year 1
Module: CSY1020 Problem Solving & Programming
Tutor: Lokesh Gupta
@version: 2.0
Date: 07/29/2017
*/

package assets;
import engine.*;
import javax.swing.*;

/**
 * Blocker class : Creates blocker objects extending Item
 */
public class Blocker extends Item{

	public Blocker(int x, int y, ImageIcon photo) {
		super(x, y);
		setPhoto(photo);

	}

}

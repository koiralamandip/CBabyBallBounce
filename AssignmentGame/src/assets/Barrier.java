/**
Program: Greenfoot Simulation Engine: Assignment 2 Java
Filename: Barrier.java
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
 * Barrier class : Creates barrier objects extending Item
 */
public class Barrier extends Item{

	public Barrier(int x, int y) {
		super(x, y);
		setPhoto(new ImageIcon("src/resources/bricks2.jpg"));
	}

}

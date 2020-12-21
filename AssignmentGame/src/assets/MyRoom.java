/**
Program: Greenfoot Simulation Engine: Assignment 2 Java
Filename: MyRoom.java
@author: Mandeep Koirala
Course: BSc HONS Computing Year 1
Module: CSY1020 Problem Solving & Programming
Tutor: Lokesh Gupta
@version: 2.0
Date: 07/29/2017
*/

package assets;

import engine.*;

/**
 * MyRoom.class : Child of Room
 * Creates the room and prepare the beginning of the Room
 */
public class MyRoom extends Room{
	public MyRoom() {
		for(int i = 0; i <= 12; i++){
			Barrier barrierPart1 = new Barrier(7,i);
			Barrier barrierPart2 = new Barrier(8,i);
			addObject(barrierPart1);
			addObject(barrierPart2);
		}
		create();
		prepare();
	}
	
	/**
	 * Add Items in preparation
	 */
	public void prepare(){
		Baby1 baby11 = new Baby1(5,6);
		addObject(baby11);
	
		Baby2 baby21 = new Baby2(10,6);
		addObject(baby21);
		
		Ball1 ball1 = new Ball1(6,6);
		addObject(ball1);

		update(); // Update the Room
	}
	
}

/**
Program: Greenfoot Simulation Engine: Assignment 2 Java
Filename: Baby2.java
@author: Mandeep Koirala
Course: BSc HONS Computing Year 1
Module: CSY1020 Problem Solving & Programming
Tutor: Lokesh Gupta
@version: 2.0
Date: 07/29/2017
*/

package assets;
import javax.swing.*;

import engine.*;

/**
 * Making of Baby2 Item
 * Instantiate this class to create as many Baby1 as wanted
 */
public class Baby2 extends Item{
	public static Item baby_1; // to reference the baby2 item
	
	/**
	 * Constructor of Baby2
	 * Takes x position and y position while construction
	 * @param x
	 * @param y
	 */
	public Baby2(int x, int y) {
		super(x, y);
		setPhoto(new ImageIcon("src/resources/baby2.png"));
		setRotation(0);
	}
	
	/**
	 * Overriding the move() method from Item.class
	 */
	public void move(){
		choose_baby2(); // Calls the method to choose among 3 babies of Baby2 class... Z, X, and C keys choose babies..
        move_baby2(); // Calls the method to move the chosen baby vertically..
	}
	
	/*
	 * Select different babies of Baby2.class in different key presses
	 */
	public void choose_baby2(){
		if (Engine.isKeyPressed("1") && getRoom().getObjects(Baby2.class).size() !=1)
        {
            baby_1 = ((Item) getRoom().getObjects(Baby2.class).get(1));
        }
		if (Engine.isKeyPressed("2") && getRoom().getObjects(Baby2.class).size() !=1)
        {
            baby_1 = ((Item) getRoom().getObjects(Baby2.class).get(0));
        }
        if (Engine.isKeyPressed("3") && getRoom().getObjects(Baby2.class).size() !=1)
        {
            baby_1 = ((Item) getRoom().getObjects(Baby2.class).get(2));
        }
	}
	
	// Moves the selected baby when W or S key pressed vertically.
	public void move_baby2(){
		
		if (baby_1 != null){
			//Move chosen baby vertically upward
			Item obstacle = baby_1.detectsObstacle(Blocker.class, 270);
			if (Engine.isKeyPressed("up") && obstacle == null && !(baby_1.getY() <= 0)) 
	        {
				baby_1.setPosition(baby_1.getX(), baby_1.getY() - 1);
				baby_1.setRotation(270);
	        }
	        
	        //Move chosen baby vertically downward
			obstacle = baby_1.detectsObstacle(Blocker.class, 90);
			if (Engine.isKeyPressed("down") && obstacle == null && !(baby_1.getY() >= getRoom().getHeight())) 
	        {
				baby_1.setPosition(baby_1.getX(), baby_1.getY() + 1);
				baby_1.setRotation(90);
	        }	
		}
	}

}

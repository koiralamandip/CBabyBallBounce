/**
Program: Greenfoot Simulation Engine: Assignment 2 Java
Filename: Baby1.java
@author: Mandeep Koirala
Course: BSc HONS Computing Year 1
Module: CSY1020 Problem Solving & Programming
Tutor: Lokesh Gupta
@version: 2.0
Date: 07/29/2017
*/

package assets;
import engine.*;
import java.awt.event.KeyEvent;
import javax.swing.*;

/**
 * Making of Baby1 Item
 * Instantiate this class to create as many Baby1 as wanted
 */
public class Baby1 extends Item{
	
	public static Item baby_1; // to reference the baby1 item
	
	/**
	 * Constructor of Baby1
	 * Takes x position and y position while construction
	 * @param x
	 * @param y
	 */
	public Baby1(int x, int y) {
		super(x, y);
		setPhoto(new ImageIcon("src/resources/Baby1.png"));

	}
	
	/**
	 * Overriding the move() method from Item.class
	 */
	public void move(){
		choose_baby1(); // Calls the method to choose among 3 babies of Baby1 class... Z, X, and C keys choose babies..
        move_baby1(); // Calls the method to move the chosen baby vertically..
	}
	
	/*
	 * Select different babies of Baby1.class in different key presses
	 */
	public void choose_baby1(){
		if (Engine.isKeyPressed("Z") && getRoom().getObjects(Baby1.class).size() !=1)
        {
            baby_1 = ((Item) getRoom().getObjects(Baby1.class).get(1));
        }
		if (Engine.isKeyPressed("X") && getRoom().getObjects(Baby1.class).size() !=1)
        {
            baby_1 = ((Item) getRoom().getObjects(Baby1.class).get(0));
        }
        if (Engine.isKeyPressed("C") && getRoom().getObjects(Baby1.class).size() !=1)
        {
            baby_1 = ((Item) getRoom().getObjects(Baby1.class).get(2));
        }
	}
	
	// Moves the selected baby when W or S key pressed vertically.
	public void move_baby1(){
		
		if (baby_1 != null){
			//Move chosen baby vertically upward
			Item obstacle = baby_1.detectsObstacle(Blocker.class, 270);
			if (Engine.isKeyPressed("w") && obstacle == null && !(baby_1.getY() <= 0)) 
	        {
				baby_1.setPosition(baby_1.getX(), baby_1.getY() - 1);
				baby_1.setRotation(270);
	        }
	        
	        //Move chosen baby vertically downward
			obstacle = baby_1.detectsObstacle(Blocker.class, 90);
			if (Engine.isKeyPressed("s") && obstacle == null && !(baby_1.getY() >= getRoom().getHeight())) 
	        {
				baby_1.setPosition(baby_1.getX(), baby_1.getY() + 1);
				baby_1.setRotation(90);
	        }	
		}
	}	

}

/**
Program: Greenfoot Simulation Engine: Assignment 2 Java
Filename: Ball1.java
@author: Mandeep Koirala
Course: BSc HONS Computing Year 1
Module: CSY1020 Problem Solving & Programming
Tutor: Lokesh Gupta
@version: 2.0
Date: 07/29/2017
*/

package assets;

import java.util.Timer;

import javax.swing.*;
import engine.*;
import ui.JavaControls;

/**
 * Ball1.class : Create as much Ball1 objects as needed from this class
 * Ball1 extends Item
 */
public class Ball1 extends Item {
	public static int scoreLeft, scoreRight; // stores the score of babies
	public static String level = "basic"; // Store the level of game
	
	/**
	 * Constructor of Ball1
	 * @param x
	 * @param y
	 */
	public Ball1(int x, int y) {
		super(x, y);
		setPhoto(new ImageIcon("src/resources/ball.png"));
	}
	
	/**
	 * Periodic calling of this method takes place. Moves the ball continuously
	 */
	@Override
	public void move(){
 		move(1);
		switch (level){
		case "basic":
			moveBasic(); // Basic Level movement
			break;
		case "intermediate":
		case "advanced":
			moveIntermediateAdvanced(); // Intermediate or Advanced Level movement
			break;
		}
	}
	
	// Basic Level Movement
	// turn in opposite direction when collided with other Item or touched border
	public void moveBasic(){
		Item baby1 =  detectsObstacle(Baby1.class);
        Item baby2 =  detectsObstacle(Baby2.class);
        if (getX() <= 0 || getX() >= getRoom().getWidth() || baby1 != null || baby2 != null || getY() <= 0 || getY() >= getRoom().getHeight()){
        	turn(180);
        }
		
	}
	
	// Intermediate or Advanced Level movement
	// rotate the Ball in random direction when collided with objects or wall is touched
	public void moveIntermediateAdvanced(){
        
		if ( getX() >= getRoom().getWidth())
        {
			scoreLeft++; // score of left players increases when ball touches right wall
            setRotation(180 - (Engine.getRandomNumber(90) - 45));
            JavaControls.blackTextBoxes[3].setText(String.valueOf(scoreLeft));
        }
        if ( getX() <= 0 )
        {
        	scoreRight++; // score of right players increases when ball touches left wall
            setRotation((Engine.getRandomNumber(90) - 45));            
            JavaControls.blackTextBoxes[4].setText(String.valueOf(scoreRight));
        }
        
        if ( getY() >= getRoom().getHeight())
        {
        	setY(getRoom().getHeight());
        	setRotation( 360 - getRotation());
        }
        
        if (getY() <= 0)
        {
        	setY(0);
        	setRotation( 360 - getRotation());
        }	
        
        Item baby1 =  detectsObstacle(Baby1.class);
        Item baby2 =  detectsObstacle(Baby2.class);
        
        if ( getRotation() >90 && getRotation() <270)         {
            if (baby1 != null)
            {
                setRotation((Engine.getRandomNumber(90) - 45));
            }
        }
        
        /* If right moving ball touches baby2 (players to the right), it bounces back in random directions...
         * Also a sound of collision is played if the game reaches Advanced Level..
        */
        if (getRotation() >=0 && getRotation() <90 || getRotation() > 270 && getRotation() <360)
        {
            if (baby2 !=null)
            {
                setRotation(180 - (Engine.getRandomNumber(90) - 45));
            }
        }
        
	}

}

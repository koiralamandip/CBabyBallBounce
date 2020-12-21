/**
Program: Greenfoot Simulation Engine: Assignment 2 Java
Filename: CBabyBallBounce.java
@author: Mandeep Koirala
Course: BSc HONS Computing Year 1
Module: CSY1020 Problem Solving & Programming
Tutor: Lokesh Gupta
@version: 2.0
Date: 07/29/2017
*/

package engine;

import assets.*;
import ui.*;

/**
 * CBabyBallBounce.class : Opens up the JFrame i.e from GreenfootSimulation.class
 * Makes one object of GreenfootSimulation global and static to access it from other classes when needed
 * Checks boolean for button pressed and specific button pressed
 */
public class CBabyBallBounce {
	public static GreenfootSimulation g;
	public static boolean buttonControl;
	public static boolean upPressed, downPressed, leftPressed, rightPressed;
	
	public static void main(String args[]){
		g = new GreenfootSimulation();
		g.createScreen(); // creates the screen simulation
	}
	
	/**
	 * kickBall() method
	 * Gets called on arrow button press and ACT button press
	 * Moves strictly in one direction when arrow buttons are pressed
	 * Moves the ball one step of the run sequence when ACT button is pressed
	 */
	public static void kickBall(){
		Ball1 ball = (Ball1) Item.getRoom().getObjects(Ball1.class).get(0);
		Item baby1, baby2;
		if (buttonControl){
			buttonControl = false;
			if (upPressed){
				upPressed = false;
				baby1 = ball.detectsObstacle(Baby1.class, 270);
				baby2 = ball.detectsObstacle(Baby2.class, 270);
				if (ball.getY() > 0 && baby1 == null && baby2 == null) ball.move("up");
			}
			else if (downPressed){
				downPressed = false;
				baby1 = ball.detectsObstacle(Baby1.class, 90);
				baby2 = ball.detectsObstacle(Baby2.class, 90);
				if (ball.getY() < 12 && baby1 == null && baby2 == null) ball.move("down");
			}
			else if (leftPressed){
				leftPressed = false;
				baby1 = ball.detectsObstacle(Baby1.class, 180);
				baby2 = ball.detectsObstacle(Baby2.class, 180);
				if (ball.getX() > 0 && baby1 == null && baby2 == null) ball.move("left");
			}
			else if (rightPressed){
				rightPressed = false;
				baby1 = ball.detectsObstacle(Baby1.class, 0);
				baby2 = ball.detectsObstacle(Baby2.class, 0);
				if (ball.getX() < 15 && baby1 == null && baby2 == null) ball.move("right");
			}
		}
		else{
			ball.move();
		}
		
		ball.getRoom().update(); // Update the room after each movement of the Item
	}
}

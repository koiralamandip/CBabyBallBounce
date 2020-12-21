/**
Program: Greenfoot Simulation Engine: Assignment 2 Java
Filename: Item.java
@author: Mandeep Koirala
Course: BSc HONS Computing Year 1
Module: CSY1020 Problem Solving & Programming
Tutor: Lokesh Gupta
@version: 2.0
Date: 07/29/2017
*/

package engine; /* (Engine, Room, Item) The fundamentals for the engine*/

import java.util.Iterator;
import java.util.List;

import javax.swing.*;

/**
 * This class works as a parent class for each game object.
 * Every character (game object) should extend this class.
 *
 */
public class Item {
	private int x, y; // The x and y position of an item.
	public int xTemp, yTemp;
	protected String uniqueName; // This variable stores the simple class name of an item.
	private static Room myRoom; // Stores the room object an item is placed in.
	private ImageIcon photo; // Stores the icon for an item.
	private int rotation; // stores the rotation angle for an item.
	
	/**
	 * Constructor for Item class
	 * Sets the x and y position of an item as supplied while constructing the item
	 * @param x
	 * @param y
	 */
	public Item(int x, int y){
		this.x = x;
		this.y = y;
		xTemp = this.x;
		yTemp = this.y;
	}
	
	/**
	 * Sets the x position of Item to be the supplied value
	 * @param x : The x position of Item
	 */
	public void setX(int x){
		this.x = x;
	}
	
	/**
	 * Sets the y position of Item to be the supplied value
	 * @param y : The y position of Item
	 */
	public void setY(int y){
		this.y = y;
	}
	
	/**
	 * @return x (The x position of required item)
	 */
	public int getX(){
		return x;
	}
	
	/**
	 * @return y (The y position of required item) 
	 */
	public int getY(){
		return y;
	}
	
	/**
	 * @return rotation : The current rotation angle of the Item
	 */
	public int getRotation(){
		return this.rotation;
	}
	
	/**
	 * Increases the rotation of an Item by supplied amount
	 * @param amount : The turning amount
	 */
	public void turn(int amount){
		setRotation(rotation + amount);
	}
	
	/**
	 * Sets the rotation of the Item to be the supplied value
	 * @param rotation : The rotation value
	 * (Greenfoot, 2017)
	 */
	public void setRotation(int rotation){
		
		   if (rotation >= 360) {
	            // Optimize the rotation value. If the rotation value exceeds 360 but is under 720 -->> subtracts 360 from rotation value
			    // i.e 405deg = 45deg  ( 405 - 360 = 45 )
	            if (rotation < 720) {
	                rotation -= 360;
	            }
	            // If rotation is greater than 720, set rotation to be the remainder of rotation/360 
	            else {
	                rotation = rotation % 360;
	            }
	        }
	        else if (rotation < 0) {
	            // If less than 0, add 360 to rotation
	            if (rotation >= -360) {
	                rotation += 360;
	            }
	            else {
	                rotation = 360 + (rotation % 360);
	            }
	        }
	        
		   // Finally, sets the rotation value to the rotation angle of Item
	        if (this.rotation != rotation) {
	            this.rotation = rotation;
	        }
		
	}
	
    // Sets the position of the Item towards the direction of its rotation
	public void move(int distance){
		
        // Rounding the cos and sin values of rotation angle to nearest integer to allow movement each step in a grid.
        int dx = (int) Math.round(Math.cos(Math.toRadians(rotation)) * distance);
        int dy = (int) Math.round(Math.sin(Math.toRadians(rotation)) * distance);
        
        // Sets the position of the Item towards the direction of its rotation
        setPosition(x + dx, y + dy);
	}
		
	/**
	 * 
	 * @return photo (The icon image of required item)
	 */
	public ImageIcon getPhoto(){
		return photo;
	}
	
	/**
	 * Sets the icon image of an item
	 * @param photo
	 */
	public void setPhoto(ImageIcon photo){
		this.photo = photo;
	}
	
	/**
	 * Sets the position of an item to supplied values in the room
	 * @param x
	 * @param y
	 */
	public void setPosition(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Returns true if an item detects obstacle of supplied class
	 * @param className : To check the obstacle of the given class towards item's rotation 
	 * @return returnItem : The Item which can be collided
	 */
	public Item detectsObstacle(Class<?> className){
		int xStep = (int) Math.round(Math.cos(Math.toRadians(rotation))); 
		int yStep = (int) Math.round(Math.sin(Math.toRadians(rotation)));
		Item returnItem = null;
		List<?> actorsIntersectable = getRoom().getObjects(className);
		Iterator<?> i = actorsIntersectable.iterator();
        while (i.hasNext()) {
            Item actor = (Item) i.next();
            if ( x + xStep == actor.getX() && y + yStep == actor.getY()){
            	returnItem = actor;
            	break;
    		}
        }
		return returnItem;
	}
	
	
	/**
	 * Returns true if an item detects obstacle of supplied class in given direction/position
	 * @param className : The detectable object of supplied class
	 * @return returnItem (if obstacle detected)
	 */
	public Item detectsObstacle(Class<?> className, int rotation){
		int xStep = (int) Math.round(Math.cos(Math.toRadians(rotation))); 
		int yStep = (int) Math.round(Math.sin(Math.toRadians(rotation)));
		Item returnItem = null;
		List<?> actorsIntersectable = getRoom().getObjects(className);
		Iterator<?> i = actorsIntersectable.iterator();
        while (i.hasNext()) {
            Item actor = (Item) i.next();
            if ( x + xStep == actor.getX() && y + yStep == actor.getY()){
            	returnItem = actor;
    		}
        }
		return returnItem;
	}
		
	/**
	 * Called from the Room class, from the method addItem(Item item);
	 * Sets the room and unique name of the item added to the room.
	 * @param myRoom
	 */
	public void addedToRoom(Room myRoom){
		this.myRoom = myRoom;
		this.uniqueName = this.getClass().getSimpleName();
	}
	
	/**
	 * Returns the room where an item is placed.
	 * @return myRoom
	 */
	public static Room getRoom(){
		return myRoom;
	}
	
	/**
	 * Move the Item towards the specified direction by a step
	 * Called from KickBall() method.. On arrow button presses.. CBabyBallBouce.class
	 * @param direction : The direction to move towards
	 */
	public void move(String direction){
		switch (direction){
		case "left":
			setPosition(x - 1, y);
			break;
		case "right":
			setPosition(x + 1, y);
			break;
		case "up":
			setPosition(x, y - 1);
			break;
		case "down":
			setPosition(x, y + 1);
			break;
		}
	}
	
	/**
	 * Empty method
	 * Gets overridden in child classes for specific functionality
	 * Gets called periodically
	 */
	public void move(){
		
	}
	
	/**
	 * Shows up the info of selected Item
	 */
	public void showInfoBox(){
		new ShowInfo(this);
	}
}

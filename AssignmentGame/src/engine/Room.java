/**
Program: Greenfoot Simulation Engine: Assignment 2 Java
Filename: Room.java
@author: Mandeep Koirala
Course: BSc HONS Computing Year 1
Module: CSY1020 Problem Solving & Programming
Tutor: Lokesh Gupta
@version: 2.0
Date: 07/29/2017
*/

package engine;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.*;
import ui.*;
import assets.*;
import engine.core.*;

/*
 * Room class : creates the JButton and assigns to the main Room, each in a grid box..
 * ballCode : index value of Ball's position
 * Addition of mouse listener to each buttons in grid
 */
public class Room {
	private static int ballCode;
	public static ControlPanel mouseListenPanel = new ControlPanel();
	public static ArrayList<Item> actorCollection = new ArrayList<Item>();
	public static GridButton[][] gridButton = new GridButton[13][16];
	
	/*
	 * Creation of JButtons for the first time of loading of the game
	 * Adds buttons to the mainScreenPanel
	 */
	public void create(){
		for (int i =0; i<=12; i++){
			for(int j = 0; j<=15; j++){
				gridButton[i][j] = new GridButton();
				gridButton[i][j].addMouseListener(mouseListenPanel);
				GreenfootWorkArea.mainScreenWorkArea.add(gridButton[i][j]);
			}
		}
	}
	
	/**
	 * Direction update for the Ball.
	 * Changes the text field value of direction field and the icon of compass direction according to the direction of the ball in the Room
	 * Ball's direction is changed relative to its own axis
	 */
	private void directionUpdater(){
		Item ball = (Item) getObjects(Ball1.class).get(0); // getting the ball object
		int dx = (int) Math.round(Math.cos(Math.toRadians(ball.getRotation()))); // The next x step
        int dy = (int) Math.round(Math.sin(Math.toRadians(ball.getRotation()))); // The next y step
		
        if (dx == 1){
        	switch (dy){
        	case -1:
        		JavaControls.directionBox.setText("NE");
        		JavaControls.coordinateButton.setIcon(new ImageIcon("src/resources/ne.jpg"));
        		break;
        	case 0:
        		JavaControls.directionBox.setText("E");
        		JavaControls.coordinateButton.setIcon(new ImageIcon("src/resources/east.jpg"));
        		break;
        	case 1:
        		JavaControls.directionBox.setText("SE");
        		JavaControls.coordinateButton.setIcon(new ImageIcon("src/resources/se.jpg"));
        		break;
        	}
        }
        else if (dx == 0){
        	switch (dy){
        	case -1:
        		JavaControls.directionBox.setText("N");
        		JavaControls.coordinateButton.setIcon(new ImageIcon("src/resources/north.jpg"));
        		break;
        	case 1:
        		JavaControls.directionBox.setText("S");
        		JavaControls.coordinateButton.setIcon(new ImageIcon("src/resources/south.jpg"));
        		break;
        	}
        }
        else if (dx == -1){
        	switch (dy){
        	case -1:
        		JavaControls.directionBox.setText("NW");
        		JavaControls.coordinateButton.setIcon(new ImageIcon("src/resources/nw.jpg"));
        		break;
        	case 0:
        		JavaControls.directionBox.setText("W");
        		JavaControls.coordinateButton.setIcon(new ImageIcon("src/resources/west.jpg"));
        		break;
        	case 1:
        		JavaControls.directionBox.setText("SW");
        		JavaControls.coordinateButton.setIcon(new ImageIcon("src/resources/sw.jpg"));
        		break;
        	}
        }
	}
	
	/*
	 * Level update
	 * If Play mode is " 2Player ", BASIC level is displayed
	 * If Play mode is " 4Player ", INTERMEDIATE level is displayed
	 * If Play mode is " Multi Player ", ADVANCED level is displayed
	 * BASIC, INTERMEDIATE, ADVANCED are shown as texts in JButton in grid
	 * If the play mode is changed, the JButton text is updated.
	 */
	private void levelUpdater(){
		for ( int j = 0; j <= 15; j++){
			gridButton[12][j].setText(null);
			gridButton[12][j].setBackground(Color.white);
			gridButton[12][j].setForeground(Color.black);
		}
		
		if (Ball1.level.equals("basic")){ 
			gridButton[12][0].setText("B    A");
			gridButton[12][1].setText("S    I");
			gridButton[12][2].setText("C");	
		}
		else if (Ball1.level.equals("intermediate")){
			gridButton[12][0].setText("I   N");
			gridButton[12][1].setText("T   E");
			gridButton[12][2].setText("R   M");
			gridButton[12][3].setText("E   D");
			gridButton[12][4].setText("I   A");
			gridButton[12][5].setText("T   E");
		}
		else{
			gridButton[12][0].setText("A   D");
			gridButton[12][1].setText("V   A");
			gridButton[12][2].setText("N   C");
			gridButton[12][3].setText("E   D");
		}
		
		for ( int j = 0; j <= 15; j++){
			if (gridButton[12][j].getText() != null){
				gridButton[12][j].setBackground(Color.green);
				gridButton[12][j].setForeground(Color.red);
			}
		}
	}
	
	/**
	 * Update method for the Room
	 * Updates the Room and its components after certain intervals or after the method being called from elsewhere
	 * If the grid's button index matches the location value( x and y ) of any Item added in the arraylist, the icon of the Item is put in that button.
	 */
	public void update(){
		levelUpdater();
		directionUpdater();
		for (int i =0; i<=12; i++){
			for(int j = 0; j<=15; j++){
				gridButton[i][j].setIcon(null);
				for(Item actor : actorCollection){
					if (i==actor.getY() && j == actor.getX()){
						gridButton[i][j].setIcon(actor.getPhoto());
						if (actor == this.getObjects(Ball1.class).get(0)){
							ballCode = gridButton[i][j].index;
							if (j == 7 || j == 8){
								gridButton[i][j].setIcon(new ImageIcon("src/resources/ballBrick.png"));
							}
						}
					}
				}
			}
		}
		
		// Shows up the current ball's square position in square text field
		if (JavaControls.squareBox !=null){
			JavaControls.squareBox.setText(String.valueOf(ballCode)); 
		}
	}
	
	/**
	 * Adds the passed object to the "actorCollection" ArrayList, so to the Room.
	 * @param obj : The Item object
	 */
	public void addObject(Item obj){
		obj.addedToRoom(this);
		actorCollection.add(obj);
	}
			
	/**
	 * Removes the passed object from the ArrayList, so from the Room.
	 * @param obj : The Item object
	 */
	public void removeObject(Item obj){
		Iterator<Item> i = actorCollection.iterator();
        while (i.hasNext()) {
            Item actor = i.next();
            if ( actor == obj) {
                actorCollection.remove(obj);
                break;
            }
        }
	}
	
	/**
	 * Gives the list of objects
	 * @param className : The className of which objects are wanted 
	 * @return result : Puts all the objects of the passed className to one array and returns the array.
	 */
	public List<?> getObjects(Class<?> className)
    {
        List result = new ArrayList();
        Iterator<Item> i = actorCollection.iterator();
        while (i.hasNext()) {
            Item actor = i.next();
            if (className == null || className.getSimpleName().equals(actor.uniqueName)) {
                result.add(actor);
            }
        }
        
        return result;
    }
	
	/**
	 * Initially this method is empty
	 * This method is overridden in child classes to perform specific task for the child
	 */
	public void prepare(){
		
	}
	
	/**
	 * @return 15 : Width of the Room
	 */
	public int getWidth(){
		return 15;
	}
	
	/**
	 * @return 12 : Height of the Room
	 */
	public int getHeight(){
		return 12;
	}
	
	/**
	 * Resets the Room when Reset Button is pressed
	 * Sets the Items' position to their initial X and Y position and their rotation angle to 0.
	 * Removes other Items from the Room except those in Basic Level
	 * 
	 */
	public void resetRoom(){
		Iterator<Item> i = actorCollection.iterator();
        while (i.hasNext()) {
        	Item actor = i.next();
        	actor.setX(actor.xTemp);
        	actor.setY(actor.yTemp);
        	actor.setRotation(0);
       }
        Ball1 ball = (Ball1) Item.getRoom().getObjects(Ball1.class).get(0);
    	ball.level = "basic";
    	JavaControls.optionBox.setText("2 Player");
    	
    	if ( getObjects(Baby1.class).size() != 1){
    		Item baby1b = (Item) getObjects(Baby1.class).get(1);
        	Item baby1c = (Item) getObjects(Baby1.class).get(2);
        	Item baby2b = (Item) getObjects(Baby2.class).get(1);
        	Item baby2c = (Item) getObjects(Baby2.class).get(2);
        	
    		removeObject(baby1b);
    		removeObject(baby1c);
    		removeObject(baby2b);
    		removeObject(baby2c);
    	}
    	
    	if ( getObjects(Blocker.class).size() !=0){
    		List<Item> blockers = (List<Item>) getObjects(Blocker.class);
    		Iterator<Item> j = blockers.iterator();
            while (j.hasNext()) {
            	Item actor = j.next();
            	actorCollection.remove(actor);
           }

    	
    	}
		
    	update(); // Once reset, the update method of the Room is called to update the Room
    	
	}
}

/**
 * 
 * @author : Mandeep Koirala
 * This class extends JButton.. 
 * Each child of this class is assigned to the grid in Room
 * Gives index value to each button in the grid
 */
class GridButton extends JButton{
	public static int indexValue = 0;
	protected int index = 0;
	public GridButton(){
		this.index = ++indexValue;
		this.setBorder(BorderFactory.createEmptyBorder());
		this.setBackground(Color.white);
	}
}
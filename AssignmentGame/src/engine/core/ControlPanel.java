/**
Program: Greenfoot Simulation Engine: Assignment 2 Java
Filename: ControlPanel.java
@author: Mandeep Koirala
Course: BSc HONS Computing Year 1
Module: CSY1020 Problem Solving & Programming
Tutor: Lokesh Gupta
@version: 2.0
Date: 07/29/2017
*/

// package of the class
package engine.core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import engine.CBabyBallBounce;
import engine.Item;
import engine.Room;

/*
 * ControlPanel class: Used to do key press action on babies and..... mouse click actions on Items ( to show their info )
 * Implements KeyListener and MouseListener for the purpose
 */
public class ControlPanel implements KeyListener, MouseListener {
	
	public static String keyString; // The string value of the last key pressed.
	
	@Override
	public void keyPressed(KeyEvent e) {
		keyString = KeyEvent.getKeyText(e.getKeyCode()).toUpperCase(); // Assigning data to keyString
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// Loop through the Items added in the Room, if the source of mouse click matches the source of any item, calls showInfoBox() method of Item
		for (Item actor : Room.actorCollection){
			if(arg0.getSource() == Room.gridButton[actor.getY()][actor.getX()]){
				actor.showInfoBox();
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}

}

package org.usfirst.frc.team3786.robot.autonomous.map.display;

import java.awt.Color;

import javax.swing.JFrame;

import org.usfirst.frc.team3786.robot.autonomous.map.GameMap;

public class MapWindow extends JFrame{
	public GameMap mapToDisplay;
	public int height;
	public int width;
	public MapRender mapJPanel; 
	
	public MapWindow(GameMap map) {
		mapToDisplay = map;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 700);
		setBackground(Color.white);
		setVisible(true);
		
		mapJPanel = new MapRender(inchesToPixels(mapToDisplay.getWidth()), inchesToPixels(mapToDisplay.getHeight()));
		add(mapJPanel);
	}
	
	
	//Conversion Methods
	public int inchesToPixels(int distInInches) {
		return distInInches * 5;
	}
	public int PixelsToInches(int distInPixels) {
		return distInPixels / 5;
	}
	
	// Return Methods
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
}

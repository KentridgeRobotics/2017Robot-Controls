package org.usfirst.frc.team3786.robot.autonomous.map.display;

import java.awt.Color;

import javax.swing.JPanel;

public class MapRender extends JPanel{
	public int width;
	public int height;
	public int mapDisplayX;
	public int mapDisplayY;
	
	public MapRender(int w, int h) {
		width = w;
		height = h;
		setBackground(Color.lightGray);
		setForeground(Color.darkGray);
	}
	
	public void setPosition(int x, int y){
        mapDisplayX = x;
        mapDisplayY = y;
        if(getWidth() > this.getRootPane().getWidth()) {
            if (mapDisplayX < -getWidth() + this.getRootPane().getWidth()) {
                mapDisplayX = -getWidth() + this.getRootPane().getWidth();
            } else if (mapDisplayX > 0) {
                mapDisplayX = 0;
            }
        }
        if(getHeight() > this.getRootPane().getHeight()) {
            if (mapDisplayY < -getHeight() + this.getRootPane().getHeight()) {
                mapDisplayY = -getHeight() + this.getRootPane().getHeight();
            } else if (mapDisplayY > 0) {
                mapDisplayY = 0;
            }
        }
    }

	public void paintComponent() {
		
	}
	
	//returning Dimensions
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
}

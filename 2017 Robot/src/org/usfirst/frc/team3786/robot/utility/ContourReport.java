package org.usfirst.frc.team3786.robot.utility;



//Object Class to organize information from GripPipeline
//
public class ContourReport {
	private double _centerX;
	private double _centerY;
	private double _height;
	private double _width;
	
	//Constructor
	public ContourReport(double x, double y, double h, double w) {
		_centerX = x;
		_centerY = y;
		_height = h;
		_width = w;
	}
	
	
	//Return Values
	public double getCenterX() {
		return _centerX;
	}
	public double getCenterY() {
		return _centerY;
	}
	public double getHeight() {
		return _height;
	}
	public double getWidth() {
		return _width;
	}
}

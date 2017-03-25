package org.usfirst.frc.team3786.robot.vision;



//Object Class to organize information from GripPipeline
//
public class ContourReport {
	private double _centerX;
	private double _centerY;
	private double _height;
	private double _width;
	private double _area;
	
	//Constructor
	public ContourReport(double x, double y, double w, double h) {
		_centerX = x;
		_centerY = y;
		_width = w;
		_height = h;
		_area = w * h;
	}
	
	public String toString() {
		return "Center X: " + _centerX + ", Center Y: " + _centerY + ", Width: " + _width + ", Height: " + _height + ", Area: " + _area;
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
	public double getArea() {
		return _area;
	}
	
	public double getLeftX() {
		return _centerX - _width/2.0;
	}
	public double getTopY() {
		return _centerY - _height/2.0;
	}
	
	public double getRightX() {
		return _centerX + _width/2.0;
	}
	public double getBottomY() {
		return _centerY + _height/2.0;
	}
}

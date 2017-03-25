package org.usfirst.frc.team3786.robot.vision;

import java.util.Arrays;

import org.opencv.core.Rect;

//Object Class to organize information from GripPipeline
//
public class ContourReport implements Comparable<ContourReport> {
	@Override
	public int compareTo(ContourReport other) {
		if (other._area > this._area)
		{
			return -1;
		}
		else if (other._area < this._area)
		{
			return 1;
		}
		else {
			return 0;
		}
	}
	
	private double _centerX;
	private double _centerY;
	private double _height;
	private double _width;
	private double _area;
	
	//Constructor
	public ContourReport(Rect r)
	{
		this(
				r.x + (r.width / 2), 
				r.y + (r.height / 2),
				r.width,
				r.height);
	}
	
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
	
	public static void main(String[] args)
	{
		ContourReport small = new ContourReport(1.0, 1.0, 1.0, 1.0);
		ContourReport medium = new ContourReport(1.0, 1.0, 2.0, 2.0);
		ContourReport large = new ContourReport(1.0, 1.0, 3.0, 3.0);
		
		ContourReport[] reports = new ContourReport[3];
		reports[0] = medium;
		reports[1] = large;
		reports[2] = small;
		for (int i=0; i<reports.length; ++i)
		{
			System.err.println(reports[i]);
		}
		
		Arrays.sort(reports);
		
		if (reports[0] != small) {
			System.err.println("EXPECTED 0 to be small");
		}
		if (reports[1] != medium)
		{
			System.err.println("Expected 1 to be medium");
		}
		if (reports[2] != large)
		{
			System.err.println("Expected 2 to be large");
		}
		
		System.err.println("SORTED!!!");
		for (int i=0; i<reports.length; ++i)
		{
			System.err.println(reports[i]);
		}
	}
}

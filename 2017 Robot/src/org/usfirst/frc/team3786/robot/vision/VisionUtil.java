package org.usfirst.frc.team3786.robot.vision;

//Utility class to calculate distances and angles using measurements
//in Pixels and coordinates.
public class VisionUtil {
	
	//Calculate Distance using Height of Target
	public static double distanceEstimate(double pixel) {
		return 5.0 / Math.tan(Math.toRadians((pixel * 41.64)/ 480.0));
	}
	
	//Calculate Angle From Camera
	public static double angleToEstimate(double xCoor){
		return ((50.88035 * xCoor)/ 640.0) - (50.88035 / 2.0);
	}
	
	//Calculate Angle of the Target
	public static double angleOfTarget(double width, double height) {
		double actualWidth = (height * 2.0) / 5.0;
		return Math.toDegrees(Math.acos(width/actualWidth));
	}
	
}

package org.usfirst.frc.team3786.robot.vision;

import java.util.List;

import org.opencv.core.MatOfPoint;
import org.usfirst.frc.team3786.robot.config.CompetitionConfig;
import org.usfirst.frc.team3786.robot.subsystems.GearTargetFinder;

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

	public static WhichSide getPositionOfGearTarget() {
		GearTargetFinder gtf = CompetitionConfig.gearTargetFinder;
		List<MatOfPoint> matlist = gtf.acquireVisionInput();
		if (matlist == null)
		{
			System.err.println("ERROR: getPositionOfGearTarget: no matlist");
			return WhichSide.WHO_KNOWS;
		}
		List<ContourReport> contourList = gtf.extractContourReports(matlist);
		if (contourList == null)
		{
			System.err.println("ERROR: getPositionOfGearTarget: no contourList");
		}
		System.err.println("getPositionOfGearTarget finds:");
		for (ContourReport contour : contourList)
		{
			System.err.println("    contour: "+contour);
		}
		int contoursCenter = 0; 
		if (contourList.size() == 0)
		{
			return WhichSide.WHO_KNOWS;
		}
		for (ContourReport contour : contourList)
		{
			contoursCenter += contour.getCenterX();
		}
		contoursCenter /= contourList.size();
		
		if (contoursCenter <= 320) {
			return WhichSide.LEFT;
		}
		else {
			return WhichSide.RIGHT;
		}
	}
	
}

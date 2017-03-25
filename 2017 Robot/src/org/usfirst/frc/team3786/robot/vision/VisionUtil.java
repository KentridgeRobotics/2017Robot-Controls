package org.usfirst.frc.team3786.robot.vision;

import java.util.Collections;
import java.util.List;

import org.usfirst.frc.team3786.robot.config.RobotConfig;

//Utility class to calculate distances and angles using measurements
//in Pixels and coordinates.
public class VisionUtil {
	
	//Calculate Distance using Height of Target
	public static double distanceEstimate(double pixel) {
		return 5.0 / Math.tan(Math.toRadians((pixel * 41.64)/ ((double)RobotConfig.IMG_HEIGHT)));
	}
	
	//Calculate Angle From Camera
	public static double angleToEstimate(double xCoor){
		return ((50.88035 * xCoor)/ ((double)RobotConfig.IMG_WIDTH)) - (50.88035 / 2.0);
	}
	
	//Calculate Angle of the Target
	public static double angleOfTarget(double width, double height) {
		double actualWidth = (height * 2.0) / 5.0;
		return Math.toDegrees(Math.acos(width/actualWidth));
	}

	public static WhichSide getPositionOfGearTarget() {
		// No, this doesn't work at all. Who knows?
		return WhichSide.WHO_KNOWS;
	}
	
	public static List<TargetPosition> getTargetPositionToGearTarget() {
		List<TargetPosition> targetPositions = Collections.emptyList();
		return targetPositions;
	}
	
}

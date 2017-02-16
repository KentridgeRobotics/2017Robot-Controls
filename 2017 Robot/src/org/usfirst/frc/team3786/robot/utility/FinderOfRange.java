package org.usfirst.frc.team3786.robot.utility;

public class FinderOfRange {
	public static double rangeForVoltage(double voltage){
		
		double y = voltage;
		double x;
		
		x = (19 * Math.sqrt(5))/ ((45 * Math.sqrt(5)) - (Math.sqrt(10239)) -(950*y));
		
		return x;
			
		
	}
}

package org.usfirst.frc.team3786.robot.utility;

public class FinderOfRange {
	public static double rangeForVoltage(double voltage){
		
		double y = voltage;
		double x;
		
		x = (19 * Math.sqrt(5))/ ((45 * Math.sqrt(5)) - (Math.sqrt(10239 - 950.0 * y)));
		
		return x;
			
		
	}
	public static void main(String[] args) {
		System.err.println("r4v of 2.4= "+rangeForVoltage(2.4));
		System.err.println("r4v of 0.87="+rangeForVoltage(0.8));
	}
}

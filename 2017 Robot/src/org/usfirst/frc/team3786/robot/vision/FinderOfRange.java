package org.usfirst.frc.team3786.robot.vision;

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
		System.err.println("Are equal: " + (2.0 == Double.NaN));
		System.err.println("Are not equal: " + (2.0 != Double.NaN));
		System.err.println("NaN and Nan are equal? " + (Double.NaN == Double.NaN));
		System.err.println("Is NaN not a number? " +Double.isNaN(Double.NaN));
	}
}

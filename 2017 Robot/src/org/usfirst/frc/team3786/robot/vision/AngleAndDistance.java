package org.usfirst.frc.team3786.robot.vision;

class AngleAndDistance
{
	private final double angleInDegrees;
	private final double distanceInInches;
	private final double avgCenterX;
	private final double avgCenterY;

	public AngleAndDistance(double angleInDegrees, double distanceInInches, double avgCenterX, double avgCenterY)
	{
		this.angleInDegrees = angleInDegrees;
		this.distanceInInches = distanceInInches;
		this.avgCenterX = avgCenterX;
		this.avgCenterY = avgCenterY;
	}
	
	public double getAngleInDegrees()
	{
		return angleInDegrees;
	}
	public double getDistanceInInches()
	{
		return distanceInInches;
	}
	
	public double getAvgCenterX()
	{
		return avgCenterX;
	}
	public double getAvgCenterY()
	{
		return avgCenterY;
	}
}

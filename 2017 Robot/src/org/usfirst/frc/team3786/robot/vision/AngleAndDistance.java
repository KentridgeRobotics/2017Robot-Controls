package org.usfirst.frc.team3786.robot.vision;

class AngleAndDistance
{
	private final double angleInDegrees;
	private final double distanceInInches;

	public AngleAndDistance(double angleInDegrees, double distanceInInches)
	{
		this.angleInDegrees = angleInDegrees;
		this.distanceInInches = distanceInInches;
	}
	
	public double getAngleInDegrees()
	{
		return angleInDegrees;
	}
	public double getDistanceInInches()
	{
		return distanceInInches;
	}
}

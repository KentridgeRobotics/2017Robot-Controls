package org.usfirst.frc.team3786.robot.utility;


//Collection of Data to organize Target Position 
public class TargetPosition {
	private double _angleToTargetInDegrees;
	private double _distanceToTargetInInches;
	private double _angleOfTargetInDegrees;
	
	//Constructor
	public TargetPosition(double toTargetAngle, double distanceToTarget, double ofTargetAngle) {
		_angleToTargetInDegrees = toTargetAngle;
		_distanceToTargetInInches = distanceToTarget;
		_angleOfTargetInDegrees = ofTargetAngle;
	}	
	
	//return values
	public double getAngleOfTargetInDegrees() {
		return _angleOfTargetInDegrees;
	}
	public double getAngleToTargetInDegrees() {
		return _angleToTargetInDegrees;
	}
	public double getDistanceToTargetInInches() {
		return _distanceToTargetInInches;
	}
}

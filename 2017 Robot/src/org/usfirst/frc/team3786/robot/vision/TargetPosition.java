package org.usfirst.frc.team3786.robot.vision;


//Collection of Data to organize Target Position 
public class TargetPosition {
	private double _targetDirectionDegrees;
	private double _distanceToTargetInInches;
	private double _targetFaceAngleDegrees;
	
	//Constructor
	public TargetPosition(double toTargetAngle, double distanceToTarget, double ofTargetAngle) {
		_targetDirectionDegrees = toTargetAngle;
		_distanceToTargetInInches = distanceToTarget;
		_targetFaceAngleDegrees = ofTargetAngle;
	}	
	
	
	public String toString() {
		return "Direction to Target: " + _targetDirectionDegrees + "\nDistance to Target: " + _distanceToTargetInInches + "\nTarget face angle in Degrees: " + _targetFaceAngleDegrees;
	}
	
	//return values
	public double getTargetFaceAngleDegrees() {
		return _targetFaceAngleDegrees;
	}
	public double getTargetDirectionDegrees() {
		return _targetDirectionDegrees;
	}
	public double getDistanceToTargetInInches() {
		return _distanceToTargetInInches;
	}
}

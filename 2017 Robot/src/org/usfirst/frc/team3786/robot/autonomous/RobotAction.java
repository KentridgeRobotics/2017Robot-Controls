package org.usfirst.frc.team3786.robot.autonomous;

public class RobotAction {
	double angleToTurnDegrees;
	double distanceToDriveInches;
	public RobotAction(double toTurn, double toDrive) {
		angleToTurnDegrees = toTurn;
		distanceToDriveInches = toDrive;
	}
	
	public String toString() {
		if(angleToTurnDegrees != 0) {
			return "Turn " + angleToTurnDegrees + " Degrees";	
		} else {
			return "Drive " + distanceToDriveInches + " inches";
		}
		
	}
}

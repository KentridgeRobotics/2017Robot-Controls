package org.usfirst.frc.team3786.robot.autonomous;

import org.usfirst.frc.team3786.robot.commands.drive.AutonomousDrive;
import org.usfirst.frc.team3786.robot.config.RobotConfig;

public class RobotAction {
	double angleToTurnDegrees;
	double distanceToDriveInches;
	
	public static RobotAction createTurn(double toTurn)
	{
		return new RobotAction(toTurn, 0.0);
	}
	
	public static RobotAction createDrive(double toDrive)
	{
		return new RobotAction(0.0, toDrive);
	}
	
	private RobotAction(double toTurn, double toDrive) {
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
	
	public AutonomousDrive toAutonomousDrive () {
		if(angleToTurnDegrees != 0) {
			double wheelDegrees = angleToTurnDegrees * RobotConfig.wheelRotationDegreesPerRobotTurnDegree;
			return new AutonomousDrive (wheelDegrees, -wheelDegrees);
		} else {
			double wheelDegrees = distanceToDriveInches * RobotConfig.wheelDegreesPerInch;
			return new AutonomousDrive (wheelDegrees, wheelDegrees);
		}
	}
}

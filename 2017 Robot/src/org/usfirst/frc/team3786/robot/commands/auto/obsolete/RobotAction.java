package org.usfirst.frc.team3786.robot.commands.auto.obsolete;

import java.util.List;

import org.usfirst.frc.team3786.robot.commands.drive.AutonomousDrive;
import org.usfirst.frc.team3786.robot.config.RobotConfig;

public class RobotAction {
	double angleToTurnDegrees;
	double distanceToDriveInches;
	
	//Current List of Robot Actions
	public static List<RobotAction> currentListOfActions;
	
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
	
	public static void updatelistOfActions(List<RobotAction> list) {
		currentListOfActions = list;
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

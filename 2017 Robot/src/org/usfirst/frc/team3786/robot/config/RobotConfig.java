package org.usfirst.frc.team3786.robot.config;
/**
 * Abstract class to hold all the values needed for the electronics.
 * 
 * Prevents us from having to go and change values in multiple places if we move things.
 * 
 * @author Aaron Weber 2017
 *
 */
public abstract class RobotConfig {
	private static RobotConfig instance;
	
	public static RobotConfig getInstance() {
		if(instance == null)
			instance = new CompetitionConfig();
		return instance;
	}
	
	public abstract int getLeftDriveMotor();
	
	public abstract int getRightDriveMotor();
	
	public abstract int getGearArmMotor();
	
	public abstract int getWinchMotor();
	
	public abstract int getWinchDeployMotor();
		
}

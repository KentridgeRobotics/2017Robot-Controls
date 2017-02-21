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

	/** when turning both wheels in opposite directions, how many degrees
	 * of robot turn are in a degree of wheel turn
	 */
	public static final double lengthOfRobotInches = 30.0;
	public static final double wheelRotationDegreesPerRobotTurnDegree = (180.0/220.0);
	public static final double wheelDegreesPerInch = (19.5 * Math.PI) / 360.0;
	public static final double encoderRotationsPerWheelRotation = 8160.0 / 360.0;
	
	public abstract int getLeftDriveMotor();
	
	public abstract int getRightDriveMotor();
	
	public abstract int getGearArmMotor();
	
	public abstract int getWinchMotor();
	
	public abstract int getWinchDeployMotor();
		
}

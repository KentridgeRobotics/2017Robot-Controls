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
			instance = new PracticeConfig();
		return instance;
	}

	/** when turning both wheels in opposite directions, how many degrees
	 * of robot turn are in a degree of wheel turn
	 */
	public static final double lengthOfRobotInches = 30.0;
	public static final double wheelRotationDegreesPerRobotTurnDegree = (180.0/220.0);
	public static final double wheelDegreesPerInch = 360.0 / (19.5 * Math.PI);
	public static final double encoderRotationsPerWheelRotation = 8160.0 / 360.0;
	
	public static final int potentiometerTop = 5;
	public static final int potentiometerBottom = 585;
	
	//for problems caused due to not knowing if test robot is backwards or not
	public static final int leftWheelMultiplier = -1;
	public static final int rightWheelMultiplier = -1;
	
	public abstract int getLeftDriveMotor();
	
	public abstract int getRightDriveMotor();
	
	public abstract int getGearArmMotor();
	
	public abstract int getWinchMotor();
	
	public abstract int getWinchDeployMotor();
	
	public abstract double getTurnSpeed();
	
	public abstract double getDriveMaxVoltageRamp();
	
	public abstract double getDriveP();
		
}

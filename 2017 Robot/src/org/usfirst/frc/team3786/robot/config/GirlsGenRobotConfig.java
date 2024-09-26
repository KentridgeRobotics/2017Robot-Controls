package org.usfirst.frc.team3786.robot.config;

public class GirlsGenRobotConfig extends RobotConfig {
	
	private static final int LEFT_DRIVE_MOTOR = 1;
	
	private static final int RIGHT_DRIVE_MOTOR = 2;
	
	private static final int SHOOTER_MOTOR = 3; 
	
	private static final int WINCH_MOTOR = 5;
	
	private static final int WINCH_DEPLOY_MOTOR = 4;
	
	private static final int LOAD_POSITION = 335;

	@Override
	public int getLeftDriveMotor() {
		// TODO Auto-generated method stub
		return LEFT_DRIVE_MOTOR;
	}

	@Override
	public int getRightDriveMotor() {
		// TODO Auto-generated method stub
		return RIGHT_DRIVE_MOTOR;
	}

	@Override
	public int getShooterMotor() {
		// TODO Auto-generated method stub
		return SHOOTER_MOTOR;
	}

	@Override
	public int getWinchMotor() {
		// TODO Auto-generated method stub
		return WINCH_MOTOR;
	}

	@Override
	public int getWinchDeployMotor() {
		// TODO Auto-generated method stub
		return WINCH_DEPLOY_MOTOR;
	}

	@Override
	public double getTurnSpeed() {
		// TODO Auto-generated method stub
		return 0.25;
	}

	@Override
	public double getDriveMaxVoltageRamp() {
		// TODO Auto-generated method stub
		return 24.0;
	}

	@Override
	public double getDriveP() {
		// TODO Auto-generated method stub
		return 0.35;
	}

	@Override
	public double getGyroInversionMultiplier() {
		return 0;
	}
	@Override
	public double getGyroHeading() {
		// TODO Auto-generated method stub
		return super.getGyro().getHeading() * getGyroInversionMultiplier();
	}

	@Override
	public double getLoadPosition() {
		// TODO Auto-generated method stub
		return LOAD_POSITION;
	}

}

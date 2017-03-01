package org.usfirst.frc.team3786.robot.config;

import org.usfirst.frc.team3786.robot.subsystems.GearTargetFinder;

public class PracticeConfig extends RobotConfig {

	private static final int LEFT_DRIVE_MOTOR = 1;
	
	private static final int RIGHT_DRIVE_MOTOR = 2;
	
	private static final int GEAR_ARM_MOTOR = 3;
	
	private static final int WINCH_MOTOR = 4;
	
	private static final int WINCH_DEPLOY_MOTOR = 5;
	
	@Override
	public int getLeftDriveMotor() {
		return LEFT_DRIVE_MOTOR;
	}


	@Override
	public int getRightDriveMotor() {
		return RIGHT_DRIVE_MOTOR;
	}


	@Override
	public int getGearArmMotor() {
		return GEAR_ARM_MOTOR;
	}


	@Override
	public int getWinchMotor() {
		return WINCH_MOTOR;
	}


	@Override
	public int getWinchDeployMotor() {
		return WINCH_DEPLOY_MOTOR;
	}


	@Override
	public double getTurnSpeed() {
		// TODO Auto-generated method stub
		return 0.1;
	}


	@Override
	public double getDriveMaxVoltageRamp() {
		// TODO Auto-generated method stub
		return 18.0;
	}


	@Override
	public double getDriveP() {
		// TODO Auto-generated method stub
		return .1;
	}


	@Override
	public double getLeftEncoderTickFactor() {
		// TODO Auto-generated method stub
		return 0.8;
	}


	@Override
	public double getRightEncoderTickFactor() {
		// TODO Auto-generated method stub
		return 1.0;
	}
	

}

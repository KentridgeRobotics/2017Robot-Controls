package org.usfirst.frc.team3786.robot.config;

import org.usfirst.frc.team3786.robot.subsystems.GearTargetFinder;

import edu.wpi.cscore.UsbCamera;

public class CompetitionConfig extends RobotConfig {

	private static final int LEFT_DRIVE_MOTOR = 1;
	
	private static final int RIGHT_DRIVE_MOTOR = 8;
	
	private static final int GEAR_ARM_MOTOR = 3;
	
	public static UsbCamera visionCamera = new UsbCamera("Usb Camera 0", 0);
	
	public static GearTargetFinder gearTargetFinder = new GearTargetFinder(visionCamera);
	
	
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

}

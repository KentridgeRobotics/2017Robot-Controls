package org.usfirst.frc.team3786.robot.config;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TankDrive extends ControlConfig {
	private Joystick leftStick = new Joystick(0);
	private Joystick rightStick = new Joystick(1);
	
	private static double leftOut, rightOut;
	
	@Override
	public Joystick getLeftStick() {
		return leftStick;
	}
	@Override
	public Joystick getRightStick() {
		return rightStick;
	}
	@Override
	public double getLeftDrive() {
		leftOut = -(leftStick.getY());
		SmartDashboard.putNumber("Left Stick", leftOut);
		return leftOut * .5;
	}
	@Override
	public double getRightDrive() {
		rightOut = rightStick.getY();
		SmartDashboard.putNumber("Right Stick", rightOut);
		return rightOut *.5;
	}

}

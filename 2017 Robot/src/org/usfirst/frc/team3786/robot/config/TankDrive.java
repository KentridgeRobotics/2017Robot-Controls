package org.usfirst.frc.team3786.robot.config;

import org.usfirst.frc.team3786.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TankDrive extends UIConfig {
	private Joystick leftStick = new Joystick(0);
	private Joystick rightStick = new Joystick(1);
	private JoystickButton testButton = new JoystickButton(leftStick, 3);
	private JoystickButton openButton = new JoystickButton(leftStick, 1);
	private JoystickButton closeButton = new JoystickButton(leftStick, 2);

	
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
		SmartDashboard.putNumber("Left Encoder", DriveTrain.getInstance().getLeftEncoder());
		SmartDashboard.putNumber("Left Velocity", DriveTrain.getInstance().getLeftVelocity());
		return Math.pow(leftOut, 2) * (Math.abs(leftOut)/leftOut);
	}
	@Override
	public double getRightDrive() {
		rightOut = rightStick.getY();
		SmartDashboard.putNumber("Right Stick", rightOut);
		return Math.pow(rightOut, 2) * (Math.abs(rightOut)/rightOut);
	}
	@Override
	public JoystickButton getTestButton() {
		return testButton;
	}
	@Override
	public JoystickButton getServoCloseButton() {
		return closeButton;
	}
	@Override
	public JoystickButton getServoOpenButton() {
		return openButton;
	}

}

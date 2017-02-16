package org.usfirst.frc.team3786.robot.config;

import org.usfirst.frc.team3786.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TankDrive extends UIConfig {
	private Joystick leftStick = new Joystick(0);
	private Joystick rightStick = new Joystick(1);
	private Joystick xbox = new Joystick(2);
	private JoystickButton testButton = new JoystickButton(xbox, 6);
	private JoystickButton openButton = new JoystickButton(xbox, 1);
	private JoystickButton gearArmTopButton = new JoystickButton(xbox, 2);
	private JoystickButton gearArmBottomButton = new JoystickButton(xbox, 4);
	private JoystickButton gearLoadedButton = new JoystickButton(xbox, 5);
	private JoystickButton loadPositionButton = new JoystickButton(xbox, 3);

	
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
	public Joystick getXbox() {
		return xbox;
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
	public JoystickButton getServoMoveButton() {
		return openButton;
	}
	@Override
	public JoystickButton getGearLoadedButton() {
		return gearLoadedButton;
	}
	@Override
	public JoystickButton getPegPositionButton() {
		return loadPositionButton;
	}
	@Override
	public double getPegPosition() {
		return 175;
	}
	@Override
	public JoystickButton getGearArmTopButton() {
		return gearArmTopButton;
	}
	@Override
	public JoystickButton getGearArmBottomButton() {
		return gearArmBottomButton;
	}

}

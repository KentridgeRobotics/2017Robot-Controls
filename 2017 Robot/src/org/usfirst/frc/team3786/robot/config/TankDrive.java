package org.usfirst.frc.team3786.robot.config;

import org.usfirst.frc.team3786.robot.commands.drive.Drive;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

public class TankDrive extends UIConfig {
	private Joystick leftStick = new Joystick(0);
	private Joystick rightStick = new Joystick(1);
	private Joystick xbox = new Joystick(2);
	private JoystickButton servoButton = new JoystickButton(xbox, 3);
	private JoystickButton gearArmTopButton = new JoystickButton(xbox, 4);
	private JoystickButton gearArmBottomButton = new JoystickButton(xbox, 1);
	private JoystickButton pegPositionButton = new JoystickButton(xbox, 2);
	private JoystickButton winchUpButton = new JoystickButton(xbox, 8);
	private JoystickButton winchDownButton = new JoystickButton(xbox, 7);
	private JoystickButton winchDeployMoveButton = new JoystickButton(xbox, 5);
	private JoystickButton winchDeployEnableButton = new JoystickButton(xbox, 6);
	private JoystickButton winchDeployReverseButton = new JoystickButton(leftStick, 7);
//	private final double PEG_POSITION = 165;


	
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
		//return Math.pow(leftOut, 2) * (Math.abs(leftOut)/leftOut);
		
		//rounding for velocity testing
		return /*Math.round*/(leftOut);
	}
	@Override
	public double getRightDrive() {
		rightOut = rightStick.getY();
		//return Math.pow(rightOut, 2) * (Math.abs(rightOut)/rightOut);
		return rightOut;
	}
	@Override
	public JoystickButton getServoMoveButton() {
		return servoButton;
	}
	@Override
	public JoystickButton getPegPositionButton() {
		return pegPositionButton;
	}
//	@Override
//	public double getPegPosition() {
//		return PEG_POSITION;
//	}
	@Override
	public JoystickButton getGearArmTopButton() {
		return gearArmTopButton;
	}
	@Override
	public JoystickButton getGearArmBottomButton() {
		return gearArmBottomButton;
	}
	@Override
	public JoystickButton getWinchUpButton() {
		return winchUpButton;
	}
	@Override
	public JoystickButton getWinchDownButton() {
		return winchDownButton;
	}
	@Override
	public JoystickButton getWinchDeployMoveButton() {
		return winchDeployMoveButton;
	}
	@Override
	public JoystickButton getWinchDeployEnableButton() {
		return winchDeployEnableButton;
	}
	@Override
	public JoystickButton getWinchDeployReverseButton() {
		return winchDeployReverseButton;
	}
	@Override
	public Command getDefaultDrive() {
		return Drive.getInstance();
	}


}

package org.usfirst.frc.team3786.robot.config;

import org.usfirst.frc.team3786.robot.commands.drive.DriveGyro2;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

public class ControllerGyroDrive extends UIConfig {
	private Joystick driveController = new Joystick(2);
	private Joystick xbox = new Joystick(0);
	private JoystickButton invertDriveButton = new JoystickButton(driveController, 1);
	private JoystickButton servoOpenButton = new JoystickButton(xbox, 3);
	private JoystickButton gearArmTopButton = new JoystickButton(xbox, 4);
	private JoystickButton gearArmBottomButton = new JoystickButton(xbox, 1);
	private JoystickButton servoCloseButton = new JoystickButton(xbox, 2);
	private JoystickButton winchUpButton = new JoystickButton(xbox, 8);
	private JoystickButton winchDownButton = new JoystickButton(xbox, 7);
	private JoystickButton winchDeployMoveButton = new JoystickButton(xbox, 5);
	private JoystickButton winchDeployEnableButton = new JoystickButton(xbox, 6);
	private JoystickButton winchDeployReverseButton = new JoystickButton(driveController, 8);
	private JoystickButton stopGyroButton = new JoystickButton(driveController, 10);
	
//	private double lastTimeMillis;
//	private double maxAngularVelocity = 360; //max angular velocity in deg/s
//	private double Pturn = 1;
	
//	public static void initTime() {
//		lastTimeMillis = System.currentTimeMillis();
//	}
	
	@Override
	public double getVelocity() {
		double result = (driveController.getRawAxis(3) - driveController.getRawAxis(2));
		if(invertDriveButton.get())
			result = -(Math.pow(result, 3));
		else
			result = (Math.pow(result, 3));
		
		return result;
	}
	@Override
	public double getTurn() {
		return Math.pow(driveController.getRawAxis(0), 3);
	}
	
	@Override
	public Joystick getLeftStick() {
		return driveController;
	}

	@Override
	public Joystick getRightStick() {
		return driveController;
	}

	@Override
	public Joystick getXbox() {
		return xbox;
	}

	@Override
	public double getLeftDrive() {
		return (getVelocity() - getTurn()) * .5;
	}

	@Override
	public double getRightDrive() {
		return -(getVelocity() + getTurn()) * .5;
	}
	
	@Override
	public JoystickButton getInvertDriveButton() {
		return invertDriveButton;
	}

	@Override
	public JoystickButton getServoOpenButton() {
		return servoOpenButton;
	}

	@Override
	public JoystickButton getServoCloseButton() {
		return servoCloseButton;
	}

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
		return DriveGyro2.getInstance();
	}
	@Override
	public JoystickButton getStopGyroButton() {
		return stopGyroButton;
	}

}

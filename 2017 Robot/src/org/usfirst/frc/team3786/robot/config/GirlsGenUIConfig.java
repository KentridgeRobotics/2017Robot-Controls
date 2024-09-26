package org.usfirst.frc.team3786.robot.config;

import org.usfirst.frc.team3786.robot.commands.drive.DriveGyro2;

//import org.usfirst.frc.team3786.robot.commands.drive.DriveGyro;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

public class GirlsGenUIConfig extends UIConfig {
	
	private Joystick leftStick = new Joystick(0);
	private Joystick xbox = new Joystick(2);
	//changes
//	private JoystickButton shoot = new JoystickButton(xbox, 4);
	private JoystickButton shoot = new JoystickButton(leftStick, 4);
	
	private JoystickButton unusedButtonOne = new JoystickButton(xbox, 1);
	
	
	private JoystickButton invertDriveButton = new JoystickButton(leftStick, 1);
	private JoystickButton servoOpenButton = new JoystickButton(leftStick, 10);
	private JoystickButton servoCloseButton = new JoystickButton(leftStick, 8);
	private JoystickButton winchUpButton = new JoystickButton(leftStick, 11);
	private JoystickButton winchDownButton = new JoystickButton(leftStick, 12);
	private JoystickButton winchDeployMoveButton = new JoystickButton(leftStick, 8);
	private JoystickButton winchDeployEnableButton = new JoystickButton(leftStick, 7);
	private JoystickButton winchDeployReverseButton = new JoystickButton(leftStick, 9);
	private JoystickButton stopGyroButton = new JoystickButton(xbox, 11);
	private JoystickButton limitBreakButton = new JoystickButton(xbox, 6);
	private JoystickButton limitEnableButton = new JoystickButton(xbox, 10);
	
//	private double lastTimeMillis;
//	private double maxAngularVelocity = 360; //max angular velocity in deg/s
//	private double Pturn = 1;
	
//	public static void initTime() {
//		lastTimeMillis = System.currentTimeMillis();
//	}
	
	@Override
	public double getVelocity() {
		double result;
		if(invertDriveButton.get())
			result = -(Math.pow(leftStick.getY(), 3));
		else
			result = (Math.pow(leftStick.getY(), 3));
		
		return result;
	}
	@Override
	public double getTurn() {
//		double currHeading = RobotConfig.getInstance().getGyroHeading();
//		double turn = 0;
//		double angularVelocity = -testX()/*leftStick.getX()*/ * maxAngularVelocity;
//		double deltaTime = System.currentTimeMillis() - lastTimeMillis;
//		lastTimeMillis = System.currentTimeMillis();
//		double deltaHeading = deltaTime * (angularVelocity / 1000); 
//		double targetHeading = currHeading + deltaHeading;
//		double headingError = targetHeading - currHeading;
//		
//		turn = headingError * Pturn;
//		
//		
//		
//		return turn;'
		return -leftStick.getX();
	}
	
	@Override
	public Joystick getLeftStick() {
		return leftStick;
	}

	@Override
	public Joystick getRightStick() {
		return leftStick;
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
	public JoystickButton getShootButton() {
		return shoot;
	}

	@Override
	public JoystickButton getUnusedButtonOne() {
		return unusedButtonOne;
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
		//return null;
	}
	@Override
	public JoystickButton getStopGyroButton() {
		return stopGyroButton;
	}
	@Override
	public JoystickButton getLimitBreakButton() {
		return limitBreakButton;
	}
	@Override
	public JoystickButton getLimitEnableButton() {
		return limitEnableButton;
	}

}

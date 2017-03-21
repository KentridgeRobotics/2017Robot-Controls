package org.usfirst.frc.team3786.robot.config;

import org.usfirst.frc.team3786.robot.commands.drive.Drive;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

public class ArcadeDrive extends UIConfig {
	private Joystick leftStick = new Joystick(0);
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
	
	private final double GAIN = 1.2;

	
	private double throttle() {
		return leftStick.getX();
	}

	private double turn() {
		return -leftStick.getY();
	}
	
	private double leftOut() {
		return throttle() + turn();
	}
	
	private double rightOut() {
		return throttle() - turn();
	}
	
	private double skim(double v) {
		if (v > 1.0)
			return -((v - 1.0) * GAIN);
		else if (v < -1.0)
			return -((v + 1.0) * GAIN);
		return 0;
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
		return leftOut() + skim(leftOut());
	}

	@Override
	public double getRightDrive() {
		return rightOut() + skim(rightOut());
	}
	
	@Override
	public JoystickButton getServoMoveButton() {
		return servoButton;
	}

	@Override
	public JoystickButton getPegPositionButton() {
		return pegPositionButton;
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
		return Drive.getInstance();
	}



}

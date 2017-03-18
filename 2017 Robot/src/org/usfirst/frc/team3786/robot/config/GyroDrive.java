package org.usfirst.frc.team3786.robot.config;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class GyroDrive extends UIConfig {
	
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
	
	private static double lastTimeMillis;
	
	public static void initTime() {
		lastTimeMillis = System.currentTimeMillis();
	}
	
	
	private double getVelocity() {
		return leftStick.getY();
	}
	
	private double getTurn() {
		double turn = 0;
		double angularVelocity = leftStick.getX();
		double deltaTime = System.currentTimeMillis() - lastTimeMillis;
		
		return turn;
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
		return 0;
	}

	@Override
	public double getRightDrive() {
		return 0;
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

}

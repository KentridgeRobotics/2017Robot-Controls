package org.usfirst.frc.team3786.robot.config;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class ArcadeDrive extends UIConfig {
	private Joystick leftStick = new Joystick(0);
	private Joystick xbox = new Joystick(2);
	private JoystickButton testButton = new JoystickButton(xbox, 6);
	private JoystickButton servoButton = new JoystickButton(xbox, 3);
	private JoystickButton gearArmTopButton = new JoystickButton(xbox, 4);
	private JoystickButton gearArmBottomButton = new JoystickButton(xbox, 1);
	private JoystickButton gearLoadedButton = new JoystickButton(xbox, 5);
	private JoystickButton pegPositionButton = new JoystickButton(xbox, 2);
	private JoystickButton winchUpButton = new JoystickButton(xbox, 8);
	private JoystickButton winchDownButton = new JoystickButton(xbox, 7);
	private final double PEG_POSITION = 165;
	
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
	public JoystickButton getTestButton() {
		return testButton;
	}

	@Override
	public JoystickButton getServoMoveButton() {
		return servoButton;
	}

	@Override
	public JoystickButton getGearLoadedButton() {
		return gearLoadedButton;
	}

	@Override
	public JoystickButton getPegPositionButton() {
		return pegPositionButton;
	}

	@Override
	public double getPegPosition() {
		return PEG_POSITION;
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


}

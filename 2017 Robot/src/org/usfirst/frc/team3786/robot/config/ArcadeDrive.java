package org.usfirst.frc.team3786.robot.config;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class ArcadeDrive extends UIConfig {
	private Joystick leftStick = new Joystick(0);
	private Joystick xbox = new Joystick(2);
	private JoystickButton testButton = new JoystickButton(xbox, 6);
	private JoystickButton servoButton = new JoystickButton(xbox, 1);
	private JoystickButton gearArmTopButton = new JoystickButton(xbox, 2);
	private JoystickButton gearArmBottomButton = new JoystickButton(xbox, 4);
	private JoystickButton gearLoadedButton = new JoystickButton(xbox, 5);
	private JoystickButton pegPositionButton = new JoystickButton(xbox, 3);
	private final double PEG_POSITION = 175;
	
	private final double GAIN = .5;

	
	private double throttle() {
		return leftStick.getY();
	}

	private double turn() {
		return leftStick.getX();
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
		return throttle() + turn() + skim(getRightDrive());
	}

	@Override
	public double getRightDrive() {
		return throttle() - turn() + skim(getLeftDrive());
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

}

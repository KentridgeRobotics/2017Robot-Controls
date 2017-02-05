package org.usfirst.frc.team3786.robot.config;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * Abstract class to store all the controls.
 * @author Aaron Weber 2017
 *
 */
public abstract class ControlConfig {
	private static ControlConfig instance;
	
	public static ControlConfig getInstance() {
		if(instance == null)
			instance = new TankDrive();
		return instance;
	}
	
	
	
	public abstract Joystick getLeftStick();
	
	public abstract Joystick getRightStick();
			
	public abstract double getLeftDrive();
	
	public abstract double getRightDrive();
	
	public abstract JoystickButton getTestButton();
	
	
}

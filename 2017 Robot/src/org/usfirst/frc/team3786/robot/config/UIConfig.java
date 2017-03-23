package org.usfirst.frc.team3786.robot.config;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Abstract class to store all the controls.
 * @author Aaron Weber 2017
 *
 */
public abstract class UIConfig {
	private static UIConfig instance;
	
	public static UIConfig getInstance() {
		if(instance == null)
			instance = new GyroDrive();
		return instance;
	}
	
	public abstract Command getDefaultDrive();
	
	public abstract Joystick getLeftStick();
	
	public abstract Joystick getRightStick();
	
	public double getTurn() {
		return 0.0;
	}
	
	public double getVelocity() {
		return 0.0;
	}
	
	public abstract Joystick getXbox();
			
	public abstract double getLeftDrive();
	
	public abstract double getRightDrive();
			
	public abstract JoystickButton getServoMoveButton();
		
	public abstract JoystickButton getPegPositionButton();
	
	//public abstract double getPegPosition();
	
	public abstract JoystickButton getGearArmTopButton();
	
	public abstract JoystickButton getGearArmBottomButton();
	
	public abstract JoystickButton getWinchUpButton();
	
	public abstract JoystickButton getWinchDownButton();
	
	public abstract JoystickButton getWinchDeployMoveButton();
	
	public abstract JoystickButton getWinchDeployEnableButton();
	
	public abstract JoystickButton getWinchDeployReverseButton();
	
		
	
}

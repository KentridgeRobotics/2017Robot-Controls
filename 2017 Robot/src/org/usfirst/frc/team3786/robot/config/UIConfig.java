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
			instance = new GirlsGenGyroDrive();
		return instance;
	}
	
	public abstract Command getDefaultDrive();
	
	public abstract Joystick getLeftStick();
	
	public abstract Joystick getRightStick();
	
	public abstract double getTurn();
	
	public abstract double getVelocity();
	
	public abstract Joystick getXbox();
			
	public abstract double getLeftDrive();
	
	public abstract double getRightDrive();
	
	public abstract JoystickButton getInvertDriveButton();
			
	public abstract JoystickButton getServoOpenButton();
		
	public abstract JoystickButton getServoCloseButton();
		
	public abstract JoystickButton getShootButton();
	
	public abstract JoystickButton getUnusedButtonOne();
	
	public abstract JoystickButton getWinchUpButton();
	
	public abstract JoystickButton getWinchDownButton();
	
	public abstract JoystickButton getWinchDeployMoveButton();
	
	public abstract JoystickButton getWinchDeployEnableButton();
	
	public abstract JoystickButton getWinchDeployReverseButton();
	
	public abstract JoystickButton getStopGyroButton();
	
	public abstract JoystickButton getLimitBreakButton();
	
	public abstract JoystickButton getLimitEnableButton();
	
		
	
}

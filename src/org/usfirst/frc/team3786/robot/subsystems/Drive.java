package org.usfirst.frc.team3786.robot.subsystems;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team3786.robot.commands.DriveCommand;

/**
 *
 */
public class Drive extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	
	private static Drive instance;
	
	public static Drive getInstance()
	{
		if (instance == null)
		{
			instance = new Drive();
		}
		return instance;
	}
	
	private CANTalon leftMotor;
	private CANTalon rightMotor;

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new DriveCommand());
	}
}

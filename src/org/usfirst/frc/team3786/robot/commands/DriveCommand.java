package org.usfirst.frc.team3786.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3786.robot.Robot;

/**
 *
 */
public class DriveCommand extends Command {
	Talon leftMotor, rightMotor;
	Joystick joystick;
	public DriveCommand() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.drive);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		joystick = new Joystick(0);
		leftMotor = new Talon(0); //change the value to the correct value for the location
		rightMotor = new Talon(1); //change the value to the correct value for the location
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		//gets the values and calculates the heavy math.
		double v,w;
		v = (1-Math.abs(joystick.getZ())*(joystick.getY()/1) + joystick.getY());
		w = (1-Math.abs(joystick.getY())*(joystick.getZ()/1) + joystick.getZ());
				
		//sets the speed of the motors for robot drive
		leftMotor.setSpeed((v+w)/2);
		rightMotor.setSpeed(-1*(v-w)/2);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}

package org.usfirst.frc.team3786.robot.commands;

import org.usfirst.frc.team3786.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MotorStop extends Command {

	public static MotorStop instance;
	
	public static MotorStop  getInstance() {
		if(instance == null)
			instance = new MotorStop();
		return instance;
	}

    public MotorStop() {
       requires(Robot.miniCIMTest);
    }
    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.miniCIMTest.setSpeed(0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

package org.usfirst.frc.team3786.robot.commands;

import org.usfirst.frc.team3786.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TestCommand extends Command {

    public TestCommand() {
    	requires(ShooterSubsystem.getInstance());
    	setTimeout(5);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	ShooterSubsystem.getInstance().fling(.55);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	//ShooterSubsystem.getInstance().fling(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

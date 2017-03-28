package org.usfirst.frc.team3786.robot.commands.drive;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StopGyroDrive extends Command {

    public StopGyroDrive() {
    	setTimeout(.01);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(DriveGyro2.getInstance().isRunning()) {
        	DriveGyro2.getInstance().cancel();
        	DriveNoGyro.getInstance().start();
    	}
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
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

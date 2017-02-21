package org.usfirst.frc.team3786.robot.commands.auto;

import org.usfirst.frc.team3786.robot.subsystems.GearArm;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoMoveDown extends Command {

    public AutoMoveDown() {
    	requires(GearArm.getInstance());
    	setTimeout(.35);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	GearArm.getInstance().setManualDrive();
    	GearArm.getInstance().setSpeed(-.2);
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
    	GearArm.getInstance().setSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

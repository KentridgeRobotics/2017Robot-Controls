package org.usfirst.frc.team3786.robot.commands.auto;

import org.usfirst.frc.team3786.robot.subsystems.GearArm;

import edu.wpi.first.wpilibj.command.Command;

/**
 * A command to drop off a gear on a peg.
 * Prereq: The robot must be in place first!!!! Use NoNameGearNavigator for that.
 */
public class NoNameGearDropper extends Command {

    public NoNameGearDropper() {
    	requires(GearArm.getInstance());
    	setTimeout(.2);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	GearArm.getInstance().open();
    	GearArm.getInstance().setSpeed(-.1);
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
    	GearArm.getInstance().setSpeed(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

package org.usfirst.frc.team3786.robot.commands.grabber;

import org.usfirst.frc.team3786.robot.subsystems.GearArm;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StartLimits extends Command {
	
	private static StartLimits instance;
	
	public static StartLimits getInstance() {
		if(instance == null)
			instance = new StartLimits();
		return instance;
	}

    public StartLimits() {
    	requires(GearArm.getInstance());
    	setTimeout(.1);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	GearArm.getInstance().limitedAccess();
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

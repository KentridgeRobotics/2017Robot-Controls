package org.usfirst.frc.team3786.robot.commands.grabber;

import org.usfirst.frc.team3786.robot.subsystems.GearArm;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ServoDance extends Command {
	
	private static ServoDance instance;
	
	public static ServoDance getInstance() {
		if(instance == null)
			instance = new ServoDance();
		return instance;
	}
	
	private boolean finished;


    public ServoDance() {
    	requires(GearArm.getInstance());
    	finished = false;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	finished = GearArm.getInstance().servoDance(3);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finished;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

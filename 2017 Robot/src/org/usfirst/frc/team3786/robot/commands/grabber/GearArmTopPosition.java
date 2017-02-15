package org.usfirst.frc.team3786.robot.commands.grabber;

import org.usfirst.frc.team3786.robot.subsystems.GearArm;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GearArmTopPosition extends Command {
	
	private static GearArmTopPosition instance;
	
	public static GearArmTopPosition getInstance() {
		if(instance == null)
			instance = new GearArmTopPosition();
		return instance;
	}


    public GearArmTopPosition() {
    	requires(GearArm.getInstance());
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	GearArm.getInstance().setPositionDrive();
    	GearArm.getInstance().setPosition(40);
    	//not working right now
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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

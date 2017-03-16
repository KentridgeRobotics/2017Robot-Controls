package org.usfirst.frc.team3786.robot.commands.drive;

import org.usfirst.frc.team3786.robot.config.UIConfig;
import org.usfirst.frc.team3786.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveVelocity extends Command {
	
	private static DriveVelocity instance;
	
	public static DriveVelocity getInstance() {
		if(instance == null)
			instance = new DriveVelocity();
		return instance;
	}

    public DriveVelocity() {
    	requires(DriveTrain.getInstance());
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.err.println("Gotta procede at high velocities");
    	DriveTrain.getInstance().setVelocity(UIConfig.getInstance().getLeftDrive(), UIConfig.getInstance().getRightDrive());
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

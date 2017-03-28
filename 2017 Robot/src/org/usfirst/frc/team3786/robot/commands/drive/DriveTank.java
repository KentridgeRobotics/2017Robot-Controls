package org.usfirst.frc.team3786.robot.commands.drive;

import org.usfirst.frc.team3786.robot.config.UIConfig;
import org.usfirst.frc.team3786.robot.subsystems.GyroDriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveTank extends Command {
	
	private static DriveTank instance;
	
	public static DriveTank getInstance() {
		if(instance == null)
			instance = new DriveTank();
		return instance;
	}

    public DriveTank() {
    	requires(GyroDriveSubsystem.getInstance());
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	GyroDriveSubsystem.getInstance().disable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	GyroDriveSubsystem.getInstance().tankDrive(UIConfig.getInstance().getLeftDrive(), UIConfig.getInstance().getRightDrive());
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

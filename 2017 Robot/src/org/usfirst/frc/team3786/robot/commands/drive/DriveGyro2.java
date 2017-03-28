package org.usfirst.frc.team3786.robot.commands.drive;

import org.usfirst.frc.team3786.robot.config.UIConfig;
import org.usfirst.frc.team3786.robot.subsystems.GyroDriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveGyro2 extends Command {
	
	private static DriveGyro2 instance;
	
	public static DriveGyro2 getInstance() {
		if(instance == null)
			instance = new DriveGyro2();
		return instance;
	}
	
//	private long lastLoopTime;
	
    public DriveGyro2() {
    	requires(GyroDriveSubsystem.getInstance());
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	GyroDriveSubsystem.getInstance().enable();
//    	lastLoopTime = System.currentTimeMillis();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	long now = System.currentTimeMillis();
//    	if(now >= lastLoopTime + 2000) {
//    		lastLoopTime = now;
//    		setPoint = - setPoint;
//    		GyroDriveSubsystem.getInstance().setSetpointRelative(setPoint);
//    	}
    	GyroDriveSubsystem.getInstance().setSetpointRelative(UIConfig.getInstance().getTurn() * 180);
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

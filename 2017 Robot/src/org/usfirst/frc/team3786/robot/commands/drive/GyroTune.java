package org.usfirst.frc.team3786.robot.commands.drive;

import org.usfirst.frc.team3786.robot.subsystems.GyroDriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GyroTune extends Command {
	
	private static GyroTune instance;
	
	public static GyroTune getInstance() {
		if(instance == null) 
			instance = new GyroTune();
		return instance;
	}
	
	long lastLoopTime;
	
	double setPoint = 30.0;

    public GyroTune() {
    	requires(GyroDriveSubsystem.getInstance());
    	GyroDriveSubsystem.getInstance().enable();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	lastLoopTime = System.currentTimeMillis();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	long now = System.currentTimeMillis();
    	if(now >= lastLoopTime + 2000) {
    		lastLoopTime = now;
    		setPoint =  -setPoint;
    		GyroDriveSubsystem.getInstance().setSetpointRelative(setPoint);
    	}

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

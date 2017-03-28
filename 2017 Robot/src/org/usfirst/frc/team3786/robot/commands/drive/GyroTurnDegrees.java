package org.usfirst.frc.team3786.robot.commands.drive;

import org.usfirst.frc.team3786.robot.subsystems.GyroDriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GyroTurnDegrees extends Command {
	
	private double turnAngle;
	
	private double tolerance = 5.0;
	
	private boolean isTeleop;

    public GyroTurnDegrees(double turn, boolean isTeleop) {
    	requires(GyroDriveSubsystem.getInstance());
    	turnAngle = turn;
    	this.isTeleop = isTeleop;
    	setTimeout(.2);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	GyroDriveSubsystem.getInstance().enable();
    	GyroDriveSubsystem.getInstance().setSetpointRelative(turnAngle);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (isTimedOut() && Math.abs(GyroDriveSubsystem.getInstance().getError()) <= tolerance);
    }

    // Called once after isFinished returns true
    protected void end() {
    	if(isTeleop)
    		DriveGyro2.getInstance().start();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

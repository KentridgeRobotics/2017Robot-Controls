package org.usfirst.frc.team3786.robot.commands.auto;

import org.usfirst.frc.team3786.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnDegrees extends Command {

	private double angle;
	
    public TurnDegrees(double ang) {
    	requires(DriveTrain.getInstance());
    	angle = ang;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	DriveTrain.getInstance().setPositionDrive();
    	double distance = ((24 * Math.PI) * (angle/360));
    	if(angle > 0) {
    		DriveTrain.getInstance().setPosition(-distance, -distance);
    	}
    	else
    		DriveTrain.getInstance().setPosition(distance, distance);
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

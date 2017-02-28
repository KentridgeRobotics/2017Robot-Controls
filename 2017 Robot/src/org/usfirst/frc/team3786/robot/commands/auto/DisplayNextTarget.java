package org.usfirst.frc.team3786.robot.commands.auto;

import java.util.List;

import org.usfirst.frc.team3786.robot.vision.TargetPosition;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DisplayNextTarget extends Command {

	public static double distance = -42.0;
	public static double direction = -17.0;
	public static double faceAngle = -19.0;
	
	
	private List<TargetPosition> targetPositions;
	
    public DisplayNextTarget(List<TargetPosition> targetPositions) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.targetPositions = targetPositions;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (targetPositions != null && targetPositions.size() > 0)
    	{
    		distance = targetPositions.get(0).getDistanceToTargetInInches();
    		direction = targetPositions.get(0).getTargetDirectionDegrees();
    		faceAngle = targetPositions.get(0).getTargetFaceAngleDegrees();
    	}
    	else {
    		distance = -999.0;
    		direction = -999.0;
    		faceAngle = -999.0;
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

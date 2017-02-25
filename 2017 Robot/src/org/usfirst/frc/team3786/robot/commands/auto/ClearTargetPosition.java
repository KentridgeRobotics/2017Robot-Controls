package org.usfirst.frc.team3786.robot.commands.auto;

import java.util.List;

import org.usfirst.frc.team3786.robot.vision.TargetPosition;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClearTargetPosition extends Command {

	List<TargetPosition> targetPositions;
	boolean isDone = false;
    public ClearTargetPosition(List<TargetPosition> targetPositions) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.targetPositions = targetPositions;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	targetPositions.remove(0);
    	isDone = true;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isDone;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

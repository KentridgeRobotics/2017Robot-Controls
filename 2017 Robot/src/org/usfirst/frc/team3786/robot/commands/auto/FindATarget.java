package org.usfirst.frc.team3786.robot.commands.auto;

import java.util.List;

import org.usfirst.frc.team3786.robot.config.CompetitionConfig;
import org.usfirst.frc.team3786.robot.subsystems.GearTargetFinder;
import org.usfirst.frc.team3786.robot.vision.ContourReport;
import org.usfirst.frc.team3786.robot.vision.TargetPosition;
import org.usfirst.frc.team3786.robot.vision.VisionUtil;
import org.usfirst.frc.team3786.robot.vision.WhichDirection;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FindATarget extends Command {
	private List<TargetPosition> targetPositions;
	boolean isDone = false;
	
    public FindATarget(List<TargetPosition> targetPositions) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.targetPositions = targetPositions;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	GearTargetFinder gtf = GearTargetFinder.getInstance();
    	List<TargetPosition> targetList = VisionUtil.getTargetPositionToGearTarget();
    	if (targetList.size() > 0)
    	{
    		targetPositions.addAll(targetList);
    		gtf.displayTargetPositions(targetList);
    		System.err.println("FOUND SOME TARGETS: "+targetPositions);
    		isDone = true;
    	}
    	
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

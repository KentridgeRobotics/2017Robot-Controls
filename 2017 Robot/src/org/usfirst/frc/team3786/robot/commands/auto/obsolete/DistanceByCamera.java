package org.usfirst.frc.team3786.robot.commands.auto.obsolete;

import java.util.List;

import org.usfirst.frc.team3786.robot.subsystems.GearTargetFinder;
import org.usfirst.frc.team3786.robot.vision.TargetPosition;
import org.usfirst.frc.team3786.robot.vision.VisionUtil;
import org.usfirst.frc.team3786.robot.vision.WhichDirection;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3786.robot.subsystems.GearTargetFinder;

/**
 *
 */
public class DistanceByCamera extends Command {
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double Range; 
    	GearTargetFinder targetFinder = GearTargetFinder.getInstance();
    	List<TargetPosition> targetList = VisionUtil.getTargetPositionToGearTarget();
    	TargetPosition position = targetList.get(0);
    	position.getDistanceToTargetInInches();
    	System.err.println(position);
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

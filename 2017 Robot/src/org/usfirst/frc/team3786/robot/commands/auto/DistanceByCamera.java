package org.usfirst.frc.team3786.robot.commands.auto;

import java.util.List;

import org.usfirst.frc.team3786.robot.subsystems.GearTargetFinder;
import org.usfirst.frc.team3786.robot.vision.TargetPosition;
import org.usfirst.frc.team3786.robot.vision.WhichDirection;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3786.robot.subsystems.GearTargetFinder;

/**
 *
 */
public class DistanceByCamera extends Command {

//    public DistanceByCamera(GearTargetFinder getInstance) {
//	} 
    GearTargetFinder TargetFinder = new GearTargetFinder();
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double Range; 
    	
    	List<TargetPosition> TargetList = TargetFinder.extractListOfTargetPosition(TargetFinder.findObjectiveContourReport(TargetFinder.extractContourReports(TargetFinder.acquireVisionInput()), WhichDirection.MIDDLE_LEFT));
    	TargetPosition Position = TargetList.get(0);
    	Position.getDistanceToTargetInInches();
    	System.err.println(Position);
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

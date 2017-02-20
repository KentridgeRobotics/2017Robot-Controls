package org.usfirst.frc.team3786.robot.commands.display;

import java.util.List;

import org.usfirst.frc.team3786.robot.autonomous.RobotAction;
import org.usfirst.frc.team3786.robot.autonomous.RobotActionGenerator;
import org.usfirst.frc.team3786.robot.config.CompetitionConfig;
import org.usfirst.frc.team3786.robot.vision.ContourReport;
import org.usfirst.frc.team3786.robot.vision.TargetPosition;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DisplayData extends Command {

    public DisplayData() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(CompetitionConfig.gearTargetFinder);
    
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	try {
    		List<ContourReport> contours = CompetitionConfig.gearTargetFinder.extractContourReports(CompetitionConfig.gearTargetFinder.runVisionThread());
    		CompetitionConfig.gearTargetFinder.displayContourReports(contours);
    		List<TargetPosition> targetPositions = CompetitionConfig.gearTargetFinder.extractListOfTargetPosition(contours);
    		CompetitionConfig.gearTargetFinder.displayTargetPositions(targetPositions);
    		List<RobotAction> robotActions = RobotActionGenerator.extractActions(targetPositions);
    		System.err.println("Size of robots actions: "+robotActions.size());
    		for (RobotAction action : robotActions)
    		{
    			System.err.println("Action: "+action);
    		}
    	}catch(Exception e) {
    		System.out.println("Exception: " + e);
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

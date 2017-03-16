package org.usfirst.frc.team3786.robot.commands.display;

import java.util.List;

import org.opencv.core.MatOfPoint;
import org.usfirst.frc.team3786.robot.autonomous.RobotAction;
import org.usfirst.frc.team3786.robot.autonomous.RobotActionGenerator;
import org.usfirst.frc.team3786.robot.config.CompetitionConfig;
import org.usfirst.frc.team3786.robot.subsystems.GearTargetFinder;
import org.usfirst.frc.team3786.robot.vision.ContourReport;
import org.usfirst.frc.team3786.robot.vision.TargetPosition;
import org.usfirst.frc.team3786.robot.vision.VisionUtil;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DisplayTargetSolution extends Command {

    public DisplayTargetSolution() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    
    }

    // Called just before this Command runs the first time
    protected void initialize() {

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	try {
    		//List<TargetPosition> targetPositions = VisionUtil.getTargetPositionToGearTarget();
    		//SmartDashboard.putNumber("TargetCount", targetPositions.size()); 
    		List<MatOfPoint> mats = GearTargetFinder.getInstance().acquireVisionInput();
    		if (mats != null)
    		{
    			SmartDashboard.putNumber("TargetCount", mats.size());
    		}
    		else {
    			SmartDashboard.putNumber("TargetCount", 12345667.0);
    		}
    	}
    	catch (Exception ex) {
    		System.err.println("ERROR in DISPLAYTARGETSOLUTION: "+ex);	
    		ex.printStackTrace(System.err);
    		SmartDashboard.putNumber("TargetCount", 8675309.0);
    	}
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

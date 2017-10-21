package org.usfirst.frc.team3786.robot.commands.auto;

import org.usfirst.frc.team3786.robot.subsystems.GyroDriveSubsystem;
import org.usfirst.frc.team3786.robot.vision.AngleAndDistance;
import org.usfirst.frc.team3786.robot.vision.NoNameRobotVision;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *  A command to drive straight toward the target. The target should be lined up "close
 *  enough", so we can see both target rectangles. Uses vision from NoNameRobotVision
 *  to line up and estimate distance, and uses rangefinder for the fine-grained distance.
 *  This should leave the robot in position to place a gear.
 *  
 */
public class NoNameGearNavigator extends Command {
	
	private AngleAndDistance displacement;
	
	private double tolerance = 5.0;
	
	private double slowDown = 50;
	private double stop = 35;
	
	private boolean done;
	
	
    public NoNameGearNavigator() {
    	requires(GyroDriveSubsystem.getInstance());
    	done = false;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
//    	NoNameRobotVision.startRobotVisionThread();
    	displacement = NoNameRobotVision.getInstance().getTargetAngleAndDistance();
    	//GyroDriveSubsystem.getInstance().setIsAuto(true);
    	GyroDriveSubsystem.getInstance().disable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// Get target position, if available. (If it's not available, use the last position. So you'll want to keep
    	// a TargetPosition member variable in this class.)
    	
    	AngleAndDistance newDisplacement = NoNameRobotVision.getInstance().getTargetAngleAndDistance();
    	if(newDisplacement != null)
    		displacement = newDisplacement;
    	
    	
//    	if(displacement != null)
//    		SmartDashboard.putNumber("Auto Distance", displacement.getDistanceInInches());
//    	else
//    		System.err.println("Still null");
    	
    	if(displacement != null) 
    	{
    	
	    	// Are we lined up on target (within tolerance)? If not, let's line up.
	//    	if(displacement.getAngleInDegrees() > tolerance)
	//    		GyroDriveSubsystem.getInstance().setSetpointRelative(displacement.getAngleInDegrees());
	    	
	    	// Are we close enough? If not, go forward, scale speed according to vision or rangefinder distance.
	    	// If we are close enough, then we're finished with this command.
	    	if(displacement.getDistanceInInches() <= stop) {
	    		System.err.println("Stopping at " + displacement.getDistanceInInches());
	    		//GyroDriveSubsystem.getInstance().autoDrive(0, 0);
	    		GyroDriveSubsystem.getInstance().manualDrive(0.0, 0.0);
	    		done = true;
	    	}
	    	else if(displacement.getDistanceInInches() <= slowDown) {
	    		System.err.println("Slowing down at " + displacement.getDistanceInInches());
	    		//GyroDriveSubsystem.getInstance().autoDrive(-.2, -.2);
	    		GyroDriveSubsystem.getInstance().manualDrive(.2, -.2);
	    	}
	    	else {
	    		System.err.println("Driving normally at " + displacement.getDistanceInInches());
	    		//GyroDriveSubsystem.getInstance().autoDrive(-.3, -.3);
	    		GyroDriveSubsystem.getInstance().manualDrive(.3, -.3);
	    	}
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return done;
    }

    // Called once after isFinished returns true
    protected void end() {
    	GyroDriveSubsystem.getInstance().autoDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

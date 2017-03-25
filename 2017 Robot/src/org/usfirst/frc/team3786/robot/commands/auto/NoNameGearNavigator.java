package org.usfirst.frc.team3786.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;

/**
 *  A command to drive straight toward the target. The target should be lined up "close
 *  enough", so we can see both target rectangles. Uses vision from NoNameRobotVision
 *  to line up and estimate distance, and uses rangefinder for the fine-grained distance.
 *  This should leave the robot in position to place a gear.
 *  
 */
public class NoNameGearNavigator extends Command {
    public NoNameGearNavigator() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// Get target position, if available. (If it's not available, use the last position. So you'll want to keep
    	// a TargetPosition member variable in this class.)
    	
    	// Are we lined up on target (within tolerance)? If not, let's line up.
    	
    	// Are we close enough? If not, go forward, scale speed according to vision or rangefinder distance.
    	
    	// If we are close enough, then we're finished with this command.
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

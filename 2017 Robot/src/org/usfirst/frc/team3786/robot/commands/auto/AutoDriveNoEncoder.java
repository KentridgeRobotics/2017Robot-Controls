package org.usfirst.frc.team3786.robot.commands.auto;

import org.usfirst.frc.team3786.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDriveNoEncoder extends Command {
	
	double leftSpeed, rightSpeed;
	/**
	 * 
	 * @param leftSpeed left wheel speed (positive to go forward)
	 * @param rightSpeed right wheel speed (negative to go forward)
	 * @param time Time to drive in seconds
	 */
    public AutoDriveNoEncoder(double leftSpeed, double rightSpeed, double time) {
    	requires(DriveTrain.getInstance());
    	setTimeout(time);
    	this.leftSpeed = leftSpeed;
    	this.rightSpeed = rightSpeed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	DriveTrain.getInstance().setSpeed(leftSpeed, rightSpeed);
    	DriveTrain.getInstance().setCoast();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	DriveTrain.getInstance().setSpeed(0, 0);
    	Timer.delay(.5);
    	DriveTrain.getInstance().setBrake();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

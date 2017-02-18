package org.usfirst.frc.team3786.robot.commands.drive;

import org.usfirst.frc.team3786.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonomousDrive extends Command {
	private double myLeftRotation;
	private double myRightRotation;
	private double leftEncoderTicks;
	private double rightEncoderTicks;
	private boolean isDone = false;
	
	static final double encoderRotationsPerWheelRotation = 17.0 / 3.0;
	//static final double wheelDegreesFwdAndBackPerRobotDegree = ?;
	// LeftRotation and RightRotation is the # degrees to go forwards (negative is backwards)
    public AutonomousDrive(double leftRotation, double rightRotation) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(DriveTrain.getInstance());
    	myLeftRotation = leftRotation;
    	myRightRotation = rightRotation;
    	leftEncoderTicks = leftRotation * encoderRotationsPerWheelRotation;
    	rightEncoderTicks = rightRotation * encoderRotationsPerWheelRotation;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	DriveTrain.getInstance().setPositionDrive();
    	DriveTrain.getInstance().setPosition(leftEncoderTicks, rightEncoderTicks);
    	// Call the methods in DriveTrain to set the mode and set position
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (leftEncoderTicks == DriveTrain.getInstance().getLeftEncoder()) {
    		if (rightEncoderTicks == DriveTrain.getInstance().getRightEncoder()) {
    			isDone = true;
    		}
    	}
    		
    	// Check on the DriveTrain's getLeftEncoder and getRightEncoder to see if they're at or past the set point.
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
    	System.err.println("Interrupted autonomous drive.");
    }
}

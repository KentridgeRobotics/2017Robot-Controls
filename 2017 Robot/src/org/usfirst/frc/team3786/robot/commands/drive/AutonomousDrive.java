package org.usfirst.frc.team3786.robot.commands.drive;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonomousDrive extends Command {
	private double myLeftRotation;
	private double myRightRotation;
	private double leftEncoderTicks;
	private double rightEncoderTicks;
	
	static final double encoderRotationsPerWheelRotation = 17.0 / 3.0;
	//static final double wheelDegreesFwdAndBackPerRobotDegree = ?;
	// LeftRotation and RightRotation is the # degrees to go forwards (negative is backwards)
    public AutonomousDrive(double leftRotation, double rightRotation) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	myLeftRotation = leftRotation;
    	myRightRotation = rightRotation;
    	leftEncoderTicks = leftRotation * encoderRotationsPerWheelRotation;
    	rightEncoderTicks = rightRotation * encoderRotationsPerWheelRotation;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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

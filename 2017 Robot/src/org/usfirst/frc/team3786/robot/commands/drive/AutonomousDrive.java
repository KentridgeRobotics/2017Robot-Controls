package org.usfirst.frc.team3786.robot.commands.drive;

import org.usfirst.frc.team3786.robot.config.RobotConfig;
import org.usfirst.frc.team3786.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonomousDrive extends Command {
	private double leftEncoderTicks;
	private double rightEncoderTicks;
	private double prevLeftEncoderTicks;
	private double prevRightEncoderTicks;
	
	private boolean isDone = false;
	
	//static final double wheelDegreesFwdAndBackPerRobotDegree = ?;
	// LeftRotation and RightRotation is the # degrees to go forwards (negative is backwards)
    public AutonomousDrive(double leftRotation, double rightRotation) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(DriveTrain.getInstance());
    	System.err.println("Starting autonomous drive: left=" + leftRotation + " right=" + rightRotation);
    	leftEncoderTicks = leftRotation * RobotConfig.encoderRotationsPerWheelRotation;
    	rightEncoderTicks = rightRotation * RobotConfig.encoderRotationsPerWheelRotation;
    	prevLeftEncoderTicks = 0.0;
    	prevRightEncoderTicks = 0.0;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	DriveTrain.getInstance().setPositionDrive();
    	DriveTrain.getInstance().zeroEncoders();
    	DriveTrain.getInstance().setPosition(leftEncoderTicks, rightEncoderTicks);
    	// Call the methods in DriveTrain to set the mode and set position
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	// Add some kind of windowed average to make sure we don't overshoot.
    	double tolerance = 40.0;
    	double currentLeftEncoder = DriveTrain.getInstance().getLeftEncoder();
    	double currentRightEncoder = DriveTrain.getInstance().getRightEncoder();
    	
    	if (Math.abs(leftEncoderTicks) <= (Math.abs(currentLeftEncoder) + tolerance)) {
    		if (Math.abs(rightEncoderTicks) <= (Math.abs(currentRightEncoder) + tolerance)) {
    			isDone = true;
    			return;
    		}
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
    	System.err.println("Interrupted autonomous drive.");
    }

	public static AutonomousDrive RotateRobot(double degrees) {
		return new AutonomousDrive(-degrees*RobotConfig.wheelRotationDegreesPerRobotTurnDegree, degrees*RobotConfig.wheelRotationDegreesPerRobotTurnDegree);
	}

	/**
	 * 
	 * @param distance
	 * @return
	 */
	public static AutonomousDrive DriveRobot(double distance) {
		return new AutonomousDrive(distance*RobotConfig.wheelDegreesPerInch, distance*RobotConfig.wheelDegreesPerInch);
	}
}

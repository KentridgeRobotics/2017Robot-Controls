package org.usfirst.frc.team3786.robot.commands.drive;


import java.util.List;

import org.usfirst.frc.team3786.robot.autonomous.RobotAction;
import org.usfirst.frc.team3786.robot.config.RobotConfig;
import org.usfirst.frc.team3786.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3786.robot.vision.TargetPosition;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonomousDrive extends Command {
	private double leftEncoderTicks;
	private double rightEncoderTicks;
	private double leftRotation;
	private double rightRotation;
	private boolean isDone = false;
	private boolean useActionFromList;
	private List<TargetPosition> targetPositions;

	//Drive from List of Current Robot Actions
	public AutonomousDrive() {
		useActionFromList = true;
	}
	
	public AutonomousDrive(List<TargetPosition> targetPositions) {
		this.targetPositions = targetPositions;
    	requires(DriveTrain.getInstance());
	}
	//static final double wheelDegreesFwdAndBackPerRobotDegree = ?;
	// LeftRotation and RightRotation is the # degrees to go forwards (negative is backwards)
	// Drive with Current Values
    public AutonomousDrive(double leftRotation, double rightRotation) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	useActionFromList = false;
    	requires(DriveTrain.getInstance());
    	this.leftRotation = leftRotation;
    	this.rightRotation = rightRotation;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(useActionFromList) {
    		if(RobotAction.currentListOfActions.size() > 0) {
				RobotAction.currentListOfActions.get(0).toAutonomousDrive();
				RobotAction.currentListOfActions.remove(0);
			} else {
				System.err.println("No Current Actions to do");
			}
    	}
    	else if (targetPositions != null) {
    		TargetPosition targetPosition = targetPositions.get(0);
    		leftRotation = -targetPosition.getDistanceToTargetInInches() * RobotConfig.wheelDegreesPerInch * RobotConfig.AreYouFeelingLuckyPunk;
    		rightRotation = leftRotation;
    	}
    	System.err.println("Starting autonomous drive: left=" + leftRotation + " right=" + rightRotation);
    	leftEncoderTicks = leftRotation * RobotConfig.encoderRotationsPerWheelRotation * RobotConfig.getInstance().getLeftEncoderTickFactor();
    	rightEncoderTicks = rightRotation * RobotConfig.encoderRotationsPerWheelRotation * RobotConfig.getInstance().getRightEncoderTickFactor();
    	setTimeout(.35);
    	DriveTrain.getInstance().setPositionDrive();
    	DriveTrain.getInstance().zeroEncoders();
    	DriveTrain.getInstance().setPosition(leftEncoderTicks, rightEncoderTicks);
    	// Call the methods in DriveTrain to set the mode and set position
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	// Add some kind of windowed average to make sure we don't overshoot.
    	double tolerance = 20.0;
    	double currentLeftEncoder = DriveTrain.getInstance().getLeftEncoder();
    	double currentRightEncoder = DriveTrain.getInstance().getRightEncoder();
    	//System.err.println("LIMIT = "+ Math.abs(rightEncoderTicks) + " curr = "+(Math.abs(currentRightEncoder) + tolerance));
//    	if (Math.abs(leftEncoderTicks) <= (Math.abs(currentLeftEncoder) - tolerance)) {
//    		if (Math.abs(rightEncoderTicks) <= (Math.abs(currentRightEncoder) + tolerance)) {
//    			isDone = true;
//    			System.err.println("ALL DONE WITH AUTONOMOUS DRIVE");
//    			return;
//    		}
 //   	}
    	if(isTimedOut() && Math.abs(DriveTrain.getInstance().getRightVelocity()) <= .6)
    		isDone = true;
  
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
		return new AutonomousDrive(RobotConfig.leftWheelMultiplier *-degrees*RobotConfig.wheelRotationDegreesPerRobotTurnDegree, RobotConfig.rightWheelMultiplier* degrees*RobotConfig.wheelRotationDegreesPerRobotTurnDegree);
	}

	/**
	 * 
	 * @param distance
	 * @return
	 */
	public static AutonomousDrive DriveRobot(double distance) {
		return new AutonomousDrive(RobotConfig.leftWheelMultiplier * distance*RobotConfig.wheelDegreesPerInch, RobotConfig.rightWheelMultiplier* distance*RobotConfig.wheelDegreesPerInch);
	}
}

package org.usfirst.frc.team3786.robot.commands.auto;

import java.util.List;

import org.usfirst.frc.team3786.robot.Robot;
import org.usfirst.frc.team3786.robot.config.RobotConfig;
import org.usfirst.frc.team3786.robot.subsystems.BNO055;
import org.usfirst.frc.team3786.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3786.robot.vision.TargetPosition;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GyroTurn extends Command {
	
	//private static double _tolerance = 0.2;
	
	private double startX;
	private double endX;
	private double leftSpeed;
	private double rightSpeed;
	private double angle;
	private boolean isDone;
	private boolean isBackwards;
	private List<TargetPosition> targetPositions = null;
	
    public GyroTurn(double ang) {
    	requires(DriveTrain.getInstance());
    	angle = ang * RobotConfig.getInstance().getGyroInversionMultiplier();
    }

    public GyroTurn(List<TargetPosition> targetPositions) {
    	requires(DriveTrain.getInstance());
    	this.targetPositions = targetPositions;
    }
    // Called just before this Command runs the first time
    protected void initialize() {
    	if ((targetPositions != null) && (targetPositions.size() != 0))
    	{
    		angle = targetPositions.get(0).getTargetDirectionDegrees() * RobotConfig.getInstance().getGyroInversionMultiplier();
    	}
    	System.err.println("Initiailized turn " + angle);
    	DriveTrain.getInstance().setSpeedDrive();
    	DriveTrain.getInstance().setBrake();
    	if(angle > 0) {
    		leftSpeed = -.25;
    		rightSpeed = .25;
    		isBackwards = false;
    	}
    	else {
    		leftSpeed = .25;
    		rightSpeed = -.25;
    		isBackwards = true;
    	}    	
    	DriveTrain.getInstance().setSpeed(-leftSpeed, rightSpeed);
    	startX = RobotConfig.getInstance().GetGyro().getHeading();
    	endX = startX + angle;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	System.out.println("Calibrated " + RobotConfig.getInstance().GetGyro().isCalibrated() + " | Start X " + startX + " | End X " + endX +" | Current X " + RobotConfig.getInstance().GetGyro().getHeading());
    	if((RobotConfig.getInstance().GetGyro().getHeading() >= endX && !isBackwards) || (RobotConfig.getInstance().GetGyro().getHeading() <= endX && isBackwards)) {
    		isDone = true;
    		DriveTrain.getInstance().setSpeed(0, 0);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isDone;
    }

    // Called once after isFinished returns true
    protected void end() {
    	DriveTrain.getInstance().setSpeed(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

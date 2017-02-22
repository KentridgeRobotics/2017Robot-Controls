package org.usfirst.frc.team3786.robot.commands.auto;

import org.usfirst.frc.team3786.robot.config.RobotConfig;
import org.usfirst.frc.team3786.robot.subsystems.BNO055;
import org.usfirst.frc.team3786.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GyroTurn extends Command {
	
	private static double _tolerance = 0.2;
	
	private double startX;
	private double endX;
	private double leftSpeed;
	private double rightSpeed;
	private double angle;
	private boolean isDone;
	//private double tolerance = 5;

    public GyroTurn(double ang) {
    	requires(DriveTrain.getInstance());
    	angle = ang;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	DriveTrain.getInstance().setSpeedDrive();
    	DriveTrain.getInstance().setBrake();
    	if(angle < 0) {
    		leftSpeed = -.25;
    		rightSpeed = .25;
    	}
    	else {
    		leftSpeed = .25;
    		rightSpeed = -.25;
    	}    	
    	DriveTrain.getInstance().setSpeed(-leftSpeed, rightSpeed);
    	startX = RobotConfig.gyro.getVector()[0];
    	endX = startX + angle;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(RobotConfig.gyro.getVector()[0] >= endX)
    		isDone = true;
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

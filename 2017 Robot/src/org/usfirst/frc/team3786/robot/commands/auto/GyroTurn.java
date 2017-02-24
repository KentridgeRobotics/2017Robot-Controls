package org.usfirst.frc.team3786.robot.commands.auto;

import org.usfirst.frc.team3786.robot.config.RobotConfig;
import org.usfirst.frc.team3786.robot.subsystems.BNO055;
import org.usfirst.frc.team3786.robot.subsystems.DriveTrain;

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
	
	BNO055 gyro;

    public GyroTurn(double ang) {
    	requires(DriveTrain.getInstance());
    	angle = ang;
    	gyro = BNO055.getInstance(BNO055.opmode_t.OPERATION_MODE_IMUPLUS,
  			   BNO055.vector_type_t.VECTOR_EULER);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
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
    	startX = gyro.getHeading();
    	endX = startX + angle;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println("Calibrated " + gyro.isCalibrated() + " | Start X " + startX + " | End X " + endX +" | Current X " + gyro.getHeading());
    	if((gyro.getHeading() >= endX && !isBackwards) || (gyro.getHeading() <= endX && isBackwards)) {
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
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

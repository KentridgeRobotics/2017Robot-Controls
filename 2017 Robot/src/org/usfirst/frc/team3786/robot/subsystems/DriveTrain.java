package org.usfirst.frc.team3786.robot.subsystems;

import org.usfirst.frc.team3786.robot.commands.drive.Drive;
import org.usfirst.frc.team3786.robot.config.RobotConfig;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {
	
	public static DriveTrain instance;
	
	public static DriveTrain getInstance() {
		if(instance == null)
			instance = new DriveTrain();
		return instance;
	}
	
	CANTalon leftDriveMotor;
	CANTalon rightDriveMotor;
	
	public DriveTrain() {
		leftDriveMotor = new CANTalon(RobotConfig.getInstance().getLeftDriveMotor());
		rightDriveMotor = new CANTalon(RobotConfig.getInstance().getRightDriveMotor());

	}
	
    
    
    public void setSpeed(double leftSpeed, double rightSpeed) {
    	leftDriveMotor.set(leftSpeed);
    	rightDriveMotor.set(rightSpeed);
    }
    
    public void setPosition(double leftPos, double rightPos) {
    	leftDriveMotor.setEncPosition(0);
    	leftDriveMotor.set(leftPos);
    	rightDriveMotor.setEncPosition(0);
    	rightDriveMotor.set(rightPos);

    }
    
    public int getLeftEncoder() {
    	return leftDriveMotor.getEncPosition();
    }
    public int getRightEncoder() {
    	return rightDriveMotor.getEncPosition();
    }
    
    public int getLeftVelocity() {
    	return leftDriveMotor.getEncVelocity();
    }
    public int getRightVelocity() {
    	return rightDriveMotor.getEncVelocity();
    }
    public void setPositionDrive() {
		leftDriveMotor.changeControlMode(TalonControlMode.Position);
		leftDriveMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		leftDriveMotor.reverseSensor(true);
		leftDriveMotor.configNominalOutputVoltage(+0f, -0f);
		leftDriveMotor.configPeakOutputVoltage(+6f, -6f);
		leftDriveMotor.setF(0.0);
		leftDriveMotor.setP(0.25);
		leftDriveMotor.setI(0.0);
		leftDriveMotor.setD(0.0);
		rightDriveMotor.changeControlMode(TalonControlMode.Position);
		rightDriveMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		rightDriveMotor.configNominalOutputVoltage(+0f, -0f);
		rightDriveMotor.configPeakOutputVoltage(+6f, -6f);
		rightDriveMotor.setF(0.0);
		rightDriveMotor.setP(0.25);
		rightDriveMotor.setI(0.0);
		rightDriveMotor.setD(0.0);

    }
    
    public double getLeftMotorOutput() {
    	return leftDriveMotor.getOutputVoltage();
    }
    public double getRightMotorOutput() {
    	return rightDriveMotor.getOutputVoltage();
    }

    
    //this doesn't seem to be working, need to look into it
    public void setSpeedDrive() {
    	leftDriveMotor.changeControlMode(TalonControlMode.PercentVbus);
    	rightDriveMotor.changeControlMode(TalonControlMode.PercentVbus);
    }
    
    public void initDefaultCommand() {
    	setDefaultCommand(Drive.getInstance());  	
    }
}


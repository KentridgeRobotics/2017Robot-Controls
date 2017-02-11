package org.usfirst.frc.team3786.robot.subsystems;

import org.usfirst.frc.team3786.robot.commands.drive.Drive;
import org.usfirst.frc.team3786.robot.config.RobotConfig;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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

		
		//PID position stuff
		
//		leftDriveMotor.changeControlMode(TalonControlMode.Position);
//		leftDriveMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
//		leftDriveMotor.reverseSensor(true);
//		leftDriveMotor.configNominalOutputVoltage(+0f, -0f);
//		leftDriveMotor.configPeakOutputVoltage(+12f, -12f);
//		leftDriveMotor.setF(0.0);
//		leftDriveMotor.setP(0.25);
//		leftDriveMotor.setI(0.0);
//		leftDriveMotor.setD(0.0);
	}
	
    
    
    public void setSpeed(double leftSpeed, double rightSpeed) {
    	leftDriveMotor.set(leftSpeed);
    	rightDriveMotor.set(rightSpeed);
    }
    
    public void setPosition(double pos) {
    	leftDriveMotor.setEncPosition(0);
    	leftDriveMotor.set(pos);
    }
    
    public int getLeftEncoder() {
    	return leftDriveMotor.getEncPosition();
    }
    
    public int getLeftVelocity() {
    	return leftDriveMotor.getEncVelocity();
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
		//leftDriveMotor.configEncoderCodesPerRev(360);
    	//leftDriveMotor.setPosition(0);
		//leftDriveMotor.setEncPosition(0);
    }
    
    public double getMotorOutput() {
    	return leftDriveMotor.getOutputVoltage();
    }
    
    //this doesn't seem to be working, need to look into it
    public void setSpeedDrive() {
    	leftDriveMotor.changeControlMode(TalonControlMode.Speed);
    	rightDriveMotor.changeControlMode(TalonControlMode.Speed);
    }
    
    public void initDefaultCommand() {
    	setDefaultCommand(Drive.getInstance());  	
    }
}


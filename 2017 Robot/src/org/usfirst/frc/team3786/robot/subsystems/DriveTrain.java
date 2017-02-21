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
	
	private enum DriveType {
		POSITION, SPEED
	}
	
	private DriveType _currentType;
	
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
		//leftDriveMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		//rightDriveMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);


		_currentType = DriveType.SPEED;

	}
	
    
    
    public void setSpeed(double leftSpeed, double rightSpeed) {
    	if(_currentType == DriveType.SPEED) {
        	leftDriveMotor.set(leftSpeed);
        	rightDriveMotor.set(rightSpeed);
    	}
    }
    public void setPosition(double leftPos, double rightPos) {
    	if(_currentType == DriveType.POSITION) {
        	//leftDriveMotor.setEncPosition(0);
        	leftDriveMotor.set(-leftPos);
        	//rightDriveMotor.setEncPosition(0);
        	rightDriveMotor.set(rightPos);
    	}

    }
    
    public void stopLeftMotor() {
    	leftDriveMotor.disableControl();
    }
    public void enableLeftMotor() {
    	leftDriveMotor.enableControl();
    }
    public void stopRightMotor() {
    	rightDriveMotor.disableControl();
    }
    public void enableRightMotor() {
    	rightDriveMotor.enableControl();
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
    
    public void zeroEncoders() {
    	leftDriveMotor.setEncPosition(0);
    	rightDriveMotor.setEncPosition(0);
    }
    
    public void setPositionDrive() {
    	//if(_currentType != DriveType.POSITION) {
    	
    	_currentType = DriveType.POSITION;
    	
    	leftDriveMotor.enableBrakeMode(true);
		leftDriveMotor.changeControlMode(TalonControlMode.Position);
		//leftDriveMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		//leftDriveMotor.reverseSensor(true);
		leftDriveMotor.configNominalOutputVoltage(+0f, -0f);
		leftDriveMotor.configPeakOutputVoltage(+12f, -12f);
		
		leftDriveMotor.setF(0.0);
		leftDriveMotor.setP(0.1);
		leftDriveMotor.setI(0.0);
		leftDriveMotor.setD(0.0);
		
		//leftDriveMotor.setProfile(0);
		
		leftDriveMotor.configEncoderCodesPerRev(360);
		
		rightDriveMotor.enableBrakeMode(true);
		rightDriveMotor.changeControlMode(TalonControlMode.Position);
		//rightDriveMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		rightDriveMotor.configNominalOutputVoltage(+0f, -0f);
		rightDriveMotor.configPeakOutputVoltage(+12f, -12f);
		
		rightDriveMotor.setF(0.0);
		rightDriveMotor.setP(0.1);
		rightDriveMotor.setI(0.0);
		rightDriveMotor.setD(0.0);
		
		//rightDriveMotor.setProfile(0);
		
		rightDriveMotor.configEncoderCodesPerRev(360);
		
    	//}

    }
    
    public double getLeftMotorOutput() {
    	return leftDriveMotor.getOutputVoltage();
    }
    public double getRightMotorOutput() {
    	return rightDriveMotor.getOutputVoltage();
    }
    
    public String getDriveType() {
    	return _currentType.toString();
    }
        
    public void setSpeedDrive() {
    	_currentType = DriveType.SPEED;
    	leftDriveMotor.changeControlMode(TalonControlMode.PercentVbus);
    	leftDriveMotor.enableBrakeMode(false);
    	rightDriveMotor.changeControlMode(TalonControlMode.PercentVbus);
    	rightDriveMotor.enableBrakeMode(false);
    }
    
    public void initDefaultCommand() {
    	setDefaultCommand(Drive.getInstance());  	
    }
}


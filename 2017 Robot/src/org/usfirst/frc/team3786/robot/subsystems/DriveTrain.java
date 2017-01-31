package org.usfirst.frc.team3786.robot.subsystems;

import org.usfirst.frc.team3786.robot.commands.drive.Drive;
import org.usfirst.frc.team3786.robot.config.RobotConfig;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

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
    	SmartDashboard.putNumber("Left Encoder", getLeftEncoder());
    	leftDriveMotor.set(pos);
    }
    
    public int getLeftEncoder() {
    	return leftDriveMotor.getEncPosition();
    }
    
    public int getLeftVelocity() {
    	return leftDriveMotor.getEncVelocity();
    }
    
    public void initDefaultCommand() {
    	setDefaultCommand(Drive.getInstance());  	
    }
}


package org.usfirst.frc.team3786.robot.subsystems;

import org.usfirst.frc.team3786.robot.commands.drive.Drive;
import org.usfirst.frc.team3786.robot.config.RobotConfig;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *LIMIT
 */
public class DriveTrain extends Subsystem {
	
	public static DriveTrain instance;
	
	private enum DriveType {
		POSITION, SPEED
	}
	
	private DriveType _currentType;
	
//	SendableChooser<Double> pValue = new SendableChooser<Double>();
//	SendableChooser<Double> dValue = new SendableChooser<Double>();

	
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
		leftDriveMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		rightDriveMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);

		
//		pValue.addDefault(".5", .5);
//		pValue.addObject(".4", .4);
//		pValue.addObject(".3", .3);
//		pValue.addObject(".2", .2);
//		pValue.addObject(".1", .1);
//		
//		dValue.addDefault(".0001", .0001);
//		dValue.addObject(".0005", .0005);
//		dValue.addObject(".001", .001);
//		dValue.addObject(".005", .005);
//		dValue.addObject(".1", .1);
//		
//		SmartDashboard.putData("P Value", pValue);
//		SmartDashboard.putData("D Value", dValue);
//		
//		SmartDashboard.putNumber("Set P Value", .5);
//		SmartDashboard.putNumber("Set D Value", .6);
//		SmartDashboard.putData("Left Motor", leftDriveMotor);
//		SmartDashboard.putData("Right Motor", rightDriveMotor);
		



		_currentType = DriveType.SPEED;

	}
	
    public void giveValues() {
    	SmartDashboard.putNumber("P Value:", leftDriveMotor.getP());
    	SmartDashboard.putNumber("D Value:", leftDriveMotor.getD());
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
    
//    public void setPD() {
//    	leftDriveMotor.setP(SmartDashboard.getNumber("Set P Value", .5));
//    	rightDriveMotor.setP(SmartDashboard.getNumber("Set P Value", .5));
//    	leftDriveMotor.setD(SmartDashboard.getNumber("Set D Value", .6));
//    	rightDriveMotor.setD(SmartDashboard.getNumber("Set D Value", .6));
//    }
    
    public void zeroEncoders() {
    	leftDriveMotor.setEncPosition(0);
    	rightDriveMotor.setEncPosition(0);
    }
        
//    public void setPositionDrive() {
//    	//if(_currentType != DriveType.POSITION) {
//    	
//    	_currentType = DriveType.POSITION;
//    	
//    	zeroEncoders();
//    	
//    	leftDriveMotor.enableBrakeMode(true);
//		leftDriveMotor.changeControlMode(TalonControlMode.Position);
//		//leftDriveMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
//		leftDriveMotor.reverseSensor(true);
//		//leftDriveMotor.reverseOutput(true);
//		leftDriveMotor.configNominalOutputVoltage(+0f, -0f);
//		leftDriveMotor.configPeakOutputVoltage(+12f, -12f);
//		
//		leftDriveMotor.setF(0.0);
//		//leftDriveMotor.setP(SmartDashboard.getNumber("PValue", .5));
//		leftDriveMotor.setP(.05);
//		leftDriveMotor.setI(0.0);
//		leftDriveMotor.setD(0.0);
//		//leftDriveMotor.setD(SmartDashboard.getNumber("DValue", .5));
//		leftDriveMotor.enableControl();
//		
//		//leftDriveMotor.setProfile(0);
//	
//		//leftDriveMotor.configEncoderCodesPerRev(360);
//		
//		rightDriveMotor.reverseSensor(true);
//		rightDriveMotor.enableBrakeMode(true);
//		rightDriveMotor.changeControlMode(TalonControlMode.Position);
//		//rightDriveMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
//		rightDriveMotor.configNominalOutputVoltage(+0f, -0f);
//		rightDriveMotor.configPeakOutputVoltage(+12f, -12f);
//		
//		rightDriveMotor.setF(0.0);
//		//rightDriveMotor.setP(SmartDashboard.getNumber("PValue", .5));
//		rightDriveMotor.setP(.05);
//		rightDriveMotor.setI(0.0);
//		rightDriveMotor.setD(0.0);
//		//rightDriveMotor.setD(SmartDashboard.getNumber("DValue", .5));
//		rightDriveMotor.enableControl();
//		
//		//rightDriveMotor.setProfile(0);
//		
//		//rightDriveMotor.configEncoderCodesPerRev(360);
//		
//    	//}
//
//    }
    
    public void setP(double p) {
    	leftDriveMotor.setP(p);
    	rightDriveMotor.setP(p);
    }
    public void setI(double i) {
    	leftDriveMotor.setI(i);
    	rightDriveMotor.setI(i);
    }
    public void setD(double d) {
    	leftDriveMotor.setD(d);
    	rightDriveMotor.setD(d);
    }
    
    public void getLoopError() {
    	System.out.println("Left: " + leftDriveMotor.getClosedLoopError());
    	System.out.println("Right: " + rightDriveMotor.getClosedLoopError());
    }
    public void setPositionDrive() {
    	//if(_currentType != DriveType.POSITION) {
    	
    	_currentType = DriveType.POSITION;
    	
    	zeroEncoders();
    	
    	leftDriveMotor.reverseSensor(true);
    	leftDriveMotor.enableBrakeMode(true);
    	leftDriveMotor.changeControlMode(TalonControlMode.Position);
		leftDriveMotor.configNominalOutputVoltage(+0f, -0f);
		leftDriveMotor.configPeakOutputVoltage(+12f, -12f);
		
    	//leftDriveMotor.setPID(SmartDashboard.getNumber("PValue", .5), 0.0, SmartDashboard.getNumber("DValue", .6), 0, 200, 24.0, 0);
		leftDriveMotor.setPID(RobotConfig.getInstance().getDriveP(), 0.0, .75, 0, 200, RobotConfig.getInstance().getDriveMaxVoltageRamp(), 0);
		leftDriveMotor.enableControl();
		
		rightDriveMotor.reverseSensor(true);
		rightDriveMotor.enableBrakeMode(true);
		rightDriveMotor.changeControlMode(TalonControlMode.Position);
		rightDriveMotor.configNominalOutputVoltage(+0f, -0f);
		rightDriveMotor.configPeakOutputVoltage(+12f, -12f);

		
    	//rightDriveMotor.setPID(SmartDashboard.getNumber("PValue", .5), 0.0, SmartDashboard.getNumber("DValue", .6), 0, 200, 24.0, 0);
		rightDriveMotor.setPID(RobotConfig.getInstance().getDriveP(), 0.0, .75, 0, 200, RobotConfig.getInstance().getDriveMaxVoltageRamp(), 0);
		rightDriveMotor.enableControl();
				
    	//}

    }

    
    public double getLeftMotorOutput() {
    	return leftDriveMotor.getOutputVoltage();
    }
    public double getRightMotorOutput() {
    	return rightDriveMotor.getOutputVoltage();
    }
    public double getLeftCurrent() {
    	return leftDriveMotor.getOutputCurrent();
    }
    public double getRightCurrent() {
    	return rightDriveMotor.getOutputCurrent();
    }
    
    public String getDriveType() {
    	return _currentType.toString();
    }
    
    public void setBrake() {
    	leftDriveMotor.enableBrakeMode(true);
    	rightDriveMotor.enableBrakeMode(true);
    }
    
    public void setCoast() {
    	leftDriveMotor.enableBrakeMode(false);
    	rightDriveMotor.enableBrakeMode(false);
    }
        
    public void setSpeedDrive() {
    	_currentType = DriveType.SPEED;
    	leftDriveMotor.changeControlMode(TalonControlMode.PercentVbus);
    	//leftDriveMotor.enableBrakeMode(false);
    	leftDriveMotor.enableBrakeMode(true);
    	rightDriveMotor.changeControlMode(TalonControlMode.PercentVbus);
    	//rightDriveMotor.enableBrakeMode(false);
    	rightDriveMotor.enableBrakeMode(true);
    }
    
    public void initDefaultCommand() {
    	setDefaultCommand(Drive.getInstance());  	
    }
}


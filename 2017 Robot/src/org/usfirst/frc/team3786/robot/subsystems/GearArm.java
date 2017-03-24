package org.usfirst.frc.team3786.robot.subsystems;

import org.usfirst.frc.team3786.robot.commands.grabber.MoveGearArmManual;
import org.usfirst.frc.team3786.robot.config.RobotConfig;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GearArm extends Subsystem {
	
	private static GearArm instance;
	
	private enum DriveType {
		POSITION, MANUAL
	}
	private static DriveType _currentType;
	
	private boolean isClosed;
	
	
	public static GearArm getInstance() {
		if(instance == null)
			instance = new GearArm();
		return instance;
	}

	CANTalon windowMotor;
	Servo servoLeft;
	Servo servoRight;
	
	
	
	public GearArm() {
		windowMotor = new CANTalon(RobotConfig.getInstance().getGearArmMotor());
		windowMotor.enableLimitSwitch(true, true);
		windowMotor.setFeedbackDevice(FeedbackDevice.AnalogPot);
		windowMotor.changeControlMode(TalonControlMode.Position);
		windowMotor.configNominalOutputVoltage(+0f, -0f);
		windowMotor.configPeakOutputVoltage(+12f, -12f);
		windowMotor.reverseSensor(true);
		windowMotor.setF(0.0);
		windowMotor.setP(2.0);
		windowMotor.setI(0.0);
		windowMotor.setD(0.0);
		
		servoLeft = new Servo(0);
		servoRight = new Servo(1);
		if(servoLeft.getPosition() > .5)
			isClosed = false;
		else
			isClosed = true;	

	}
	
	
	//arm methods
	public void setSpeed(double speed) {
		if(_currentType == DriveType.MANUAL)
			windowMotor.set(speed);
	}
	
	
	public void setPosition(double pos) {		
		if(_currentType == DriveType.POSITION)
			windowMotor.set(pos);
	}
	
	public double getVoltage() {
		return windowMotor.getOutputVoltage();
	}
		
	public int getPosition() {
		return windowMotor.getAnalogInPosition();
	}
	public void setPositionDrive() {
		_currentType = DriveType.POSITION;
		windowMotor.changeControlMode(TalonControlMode.Position);
	}
	public void setManualDrive() {
		_currentType = DriveType.MANUAL;
		windowMotor.changeControlMode(TalonControlMode.PercentVbus);
	}
	public String getMode() {
		return _currentType.toString();
	}
	
	public boolean getTopLimitSwitch() {
		return windowMotor.isFwdLimitSwitchClosed();
	}
	public boolean getBottomLimitSwitch() {
		return windowMotor.isRevLimitSwitchClosed();
	}
	
	//servo methods
    public void open() {
    	servoLeft.set(1.0);
    	servoRight.set(0.0);
    	isClosed = false;
    }
    public void close() {
    	servoLeft.set(.33);
    	servoRight.set(.67);
    	isClosed = true;
    }
    public boolean servoDance(int reps) {
    	for(int i = 0; i < reps; i++) {
        	servoLeft.set(.53);
        	servoRight.set(.87);
        	Timer.delay(.5);
        	servoLeft.set(.13);
        	servoRight.set(.47);
        	Timer.delay(.5);
    	}
    	servoLeft.set(.33);
    	servoRight.set(.67);
    	return true;
    }
    
    public boolean getIsClosed() {
    	return isClosed;
    }

	
	
    public void initDefaultCommand() {
    	setDefaultCommand(MoveGearArmManual.getInstance());
    }
}


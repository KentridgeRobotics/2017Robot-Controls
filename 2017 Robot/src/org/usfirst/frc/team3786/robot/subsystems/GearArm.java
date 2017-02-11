package org.usfirst.frc.team3786.robot.subsystems;

import org.usfirst.frc.team3786.robot.commands.grabber.MoveGearArmPosition;
import org.usfirst.frc.team3786.robot.config.RobotConfig;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

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
	
	private boolean isLoaded;
	
	public static GearArm getInstance() {
		if(instance == null)
			instance = new GearArm();
		return instance;
	}

	CANTalon windowMotor;
	
	
	public GearArm() {
		isLoaded = false;
		windowMotor = new CANTalon(RobotConfig.getInstance().getGearArmMotor());
		windowMotor.enableLimitSwitch(true, true);
		windowMotor.setFeedbackDevice(FeedbackDevice.AnalogPot);
		windowMotor.changeControlMode(TalonControlMode.Position);
		windowMotor.configNominalOutputVoltage(+0f, -0f);
		windowMotor.configPeakOutputVoltage(+12f, -12f);
		windowMotor.setF(0.0);
		windowMotor.setP(0.1);
		windowMotor.setI(0.0);
		windowMotor.setD(0.0);

	}
	
	public void setSpeed(double speed) {
		if(_currentType == DriveType.MANUAL)
			windowMotor.set(speed);
	}
	
	public void setPosition(double pos) {
		if(_currentType == DriveType.POSITION)
			windowMotor.set(pos);
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
	public boolean getIsLoaded() {
		return isLoaded;
	}
	public void setIsLoaded(boolean b) {
		isLoaded = b;
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(MoveGearArmPosition.getInstance());
    }
}


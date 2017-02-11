package org.usfirst.frc.team3786.robot.subsystems;

import org.usfirst.frc.team3786.robot.commands.grabber.MoveGearArm;
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
	
	public static GearArm getInstance() {
		if(instance == null)
			instance = new GearArm();
		return instance;
	}

	CANTalon windowMotor;
	
	public GearArm() {
		windowMotor = new CANTalon(RobotConfig.getInstance().getGearArmMotor());
		windowMotor.enableLimitSwitch(true, true);
		windowMotor.setFeedbackDevice(FeedbackDevice.AnalogPot);
		windowMotor.changeControlMode(TalonControlMode.Position);
		windowMotor.configNominalOutputVoltage(+0f, -0f);
		windowMotor.configPeakOutputVoltage(+6f, -6f);
		windowMotor.setF(0.0);
		windowMotor.setP(0.1);
		windowMotor.setI(0.0);
		windowMotor.setD(0.0);

	}
	
//	public void setSpeed(double speed) {
//		windowMotor.changeControlMode(TalonControlMode.Speed);
//		windowMotor.set(speed);
//	}
	
	public void setPosition(double pos) {
		windowMotor.set(pos);
	}
	
	public int getPosition() {
		return windowMotor.getAnalogInPosition();
	}
	
	
	
    public void initDefaultCommand() {
    	setDefaultCommand(MoveGearArm.getInstance());
    }
}

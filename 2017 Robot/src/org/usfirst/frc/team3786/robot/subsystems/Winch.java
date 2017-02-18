package org.usfirst.frc.team3786.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team3786.robot.config.RobotConfig;

import com.ctre.CANTalon;

/**
 *
 */
public class Winch extends Subsystem {
	
	private static Winch instance;
	
	public static Winch getInstance() {
		if(instance == null)
			instance = new Winch();
		return instance;
	}
	
	CANTalon winchMotor;
	
	public Winch() {
		winchMotor = new CANTalon(RobotConfig.getInstance().getWinchMotor());
	}
	
	public void setSpeed(double pos) {
		winchMotor.set(pos);
	}
		

    public void initDefaultCommand() {
    }
}


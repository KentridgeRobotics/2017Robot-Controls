package org.usfirst.frc.team3786.robot.subsystems;

import org.usfirst.frc.team3786.robot.commands.drive.Drive;
import org.usfirst.frc.team3786.robot.config.RobotConfig;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class MiniCIM extends Subsystem {
	public static MiniCIM instance;
	public static MiniCIM getInstance() {
		if(instance == null)
			instance = new MiniCIM();
		return instance;
	}
	CANTalon miniDriver = new CANTalon(RobotConfig.getInstance().getLeftDriveMotor());
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(Drive.getInstance());
    }
    
    public void setSpeed(double speed) {
    	miniDriver.set(speed);
    }
}


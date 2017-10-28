package org.usfirst.frc.team3786.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team3786.robot.config.RobotConfig;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.VictorSP;

/**
 * What it Does
 *  - Gets the motor set to 100%
 *  
 *
 */
public class GirlsGenShooterSubsystem extends Subsystem {
	private static GirlsGenShooterSubsystem instance;
	
	private CANTalon flingerMotor;

	
	
	public static GirlsGenShooterSubsystem getInstance() {
		if(instance == null)
		{
			instance = new GirlsGenShooterSubsystem();
		}
		return instance;
	}
	
	
	private GirlsGenShooterSubsystem() {
		flingerMotor = new CANTalon(RobotConfig.getInstance().getShooterMotor());
		fling(0.0);
	}

	
    public void initDefaultCommand() {
    }
    
    public void fling(double flingForce) {
    	System.err.println("Flinging: "+flingForce);
    	flingerMotor.set(flingForce);
    }
}


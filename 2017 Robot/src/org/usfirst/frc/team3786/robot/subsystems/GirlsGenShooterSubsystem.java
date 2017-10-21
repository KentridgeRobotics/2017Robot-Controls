package org.usfirst.frc.team3786.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team3786.robot.config.RobotConfig;

import edu.wpi.first.wpilibj.VictorSP;

/**
 * What it Does
 *  - Gets the motor set to 100%
 *  
 *
 */
public class GirlsGenShooterSubsystem extends Subsystem {
	private static GirlsGenShooterSubsystem instance;
	
	private final VictorSP flingerMotor;
	
	
	
	public static GirlsGenShooterSubsystem getInstance() {
		if(instance == null)
		{
			instance = new GirlsGenShooterSubsystem();
		}
		return instance;
	}
	
	
	private GirlsGenShooterSubsystem() {
		flingerMotor = new VictorSP(RobotConfig.getInstance().getShooterMotor());
	}

	
    public void initDefaultCommand() {
    }
    
    public void fling(double flingForce) {
    	flingerMotor.set(flingForce);
    }
}


package org.usfirst.frc.team3786.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Has 2 motors for shooting the balls, and 1 servo for swiping out the balls in the hopper.
 */
public class ShooterSubsystem extends Subsystem {
	private static ShooterSubsystem instance;
	
	public static ShooterSubsystem getInstance()
	{
		if (instance == null)
		{
			instance = new ShooterSubsystem();
		}
		return instance;
	}
	
	private final Servo swiper;
	private final VictorSP flingerMotor;
	
	private ShooterSubsystem()
	{
		swiper = new Servo(2); // Make sure this is right
		flingerMotor = new VictorSP(3);
	}
	
    
	
	
    public void initDefaultCommand() {
    }
    
    public void swipe(boolean forward)
    {
    	// Swiper no swiping!
    	if (forward)
    		swiper.setPosition(1.0);
    	else
    		swiper.setPosition(0.0);
    	
    }
    
    public void fling(double flingForce)
    {
    	flingerMotor.set(flingForce);
    	
    }
    
}


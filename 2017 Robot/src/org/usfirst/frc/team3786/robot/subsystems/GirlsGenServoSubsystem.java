package org.usfirst.frc.team3786.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GirlsGenServoSubsystem extends Subsystem {

	public static GirlsGenServoSubsystem getInstance() {
		if(instance == null)
		{
			instance = new GirlsGenServoSubsystem();
		}
		return instance;
	}

	private static GirlsGenServoSubsystem instance;
	
	private GirlsGenServoSubsystem() {
		swiper = new Servo(0); // Make sure this is right
	}
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void startSwipe() {
    	swiper.set(.5);
    }
    
    public void swipe(boolean forward)
    {
    	// Swiper no swiping!
    	if (forward)
    		swiper.setPosition(.9);
    	else
    		swiper.setPosition(.1);
    	
    }
	private final Servo swiper;

}


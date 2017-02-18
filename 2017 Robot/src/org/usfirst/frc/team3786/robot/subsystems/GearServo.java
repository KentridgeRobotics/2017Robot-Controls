package org.usfirst.frc.team3786.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GearServo extends Subsystem {
	
	private static GearServo instance;
	
	private boolean isClosed;
		
	public static GearServo getInstance() {
    	if(instance == null)
    		instance = new GearServo();
    	return instance;
    }
	
	Servo servo;
	
	public GearServo() {
		servo = new Servo(0);
		if(servo.getPosition() > .5)
			isClosed = false;
		else
			isClosed = true;
	}

    public void open() {
    	servo.set(.75);
    	isClosed = false;
    }
    public void close() {
    	servo.set(0);
    	isClosed = true;
    }
    
    public boolean getIsClosed() {
    	return isClosed;
    }
    
    public void initDefaultCommand() {
    }

}


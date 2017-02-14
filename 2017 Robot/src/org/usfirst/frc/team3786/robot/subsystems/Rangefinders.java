package org.usfirst.frc.team3786.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Rangefinders extends Subsystem {
	
	private static Rangefinders instance;
	
	public static Rangefinders getInstance() {
		if(instance == null) 
			instance = new Rangefinders();
		return instance;
	}
	
	AnalogInput testSharp;
	
	public Rangefinders() {
		testSharp = new AnalogInput(1);
	}
	
	public double getTestVoltage() {
		return testSharp.getVoltage();
	}
	
    public void initDefaultCommand() {

    }
}


package org.usfirst.frc.team3786.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ServoTest extends Subsystem {
	
	public static ServoTest instance;
	
	Servo hitec = new Servo(0);

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public static ServoTest getInstance() {
    	if(instance == null)
    		instance = new ServoTest();
    	return instance;
    }
    public void open() {
    	hitec.set(0);
    }
    public void close() {
    	hitec.set(-.5);
    }
}


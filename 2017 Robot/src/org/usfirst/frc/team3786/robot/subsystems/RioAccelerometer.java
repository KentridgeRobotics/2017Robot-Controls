package org.usfirst.frc.team3786.robot.subsystems;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;

/**
 *
 */
public class RioAccelerometer extends Subsystem {
	
	private static RioAccelerometer instance;
	
	public static RioAccelerometer getInstance() {
		if(instance == null)
			instance = new RioAccelerometer();
		return instance;
	}
	
	private BuiltInAccelerometer accel;
	
	public RioAccelerometer() {
		accel = new BuiltInAccelerometer();
	}
	
	public double getX() {
		return accel.getX();
	}
	
	public double getY() {
		return accel.getY();
	}
	
	public double getZ() {
		return accel.getZ();
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}


package org.usfirst.frc.team3786.robot.subsystems;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;

/**
 *
 */
public class RobotAccelerometer extends Subsystem {
	Accelerometer myAccelerometer;
	public RobotAccelerometer(Accelerometer accelerometer) {
		myAccelerometer = accelerometer;
	}
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public double getX()
    {
    	return myAccelerometer.getX();
    }
    public double getY() {
    	return myAccelerometer.getY();
    }
    public double getZ() {
    	return myAccelerometer.getZ();
    }
    public void setRange(Accelerometer.Range range) {
    	myAccelerometer.setRange(range);
    }
}


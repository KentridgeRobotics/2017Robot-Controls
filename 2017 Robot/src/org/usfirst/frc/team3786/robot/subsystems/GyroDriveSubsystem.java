package org.usfirst.frc.team3786.robot.subsystems;

import org.usfirst.frc.team3786.robot.config.RobotConfig;
import org.usfirst.frc.team3786.robot.config.UIConfig;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class GyroDriveSubsystem extends PIDSubsystem {
	
	private static GyroDriveSubsystem instance;
	
	public static GyroDriveSubsystem getInstance() {
		if(instance == null)
			instance = new GyroDriveSubsystem();
		return instance;
	}
	
	private CANTalon leftDriveMotor;
	private CANTalon rightDriveMotor;
	
//	private double angularVelocity;
//	private double maxAngularVelocity = 360.0;
		
	private double tolerance = 0.0;

    // Initialize your subsystem here
    public GyroDriveSubsystem() {
    	super("Gyro PID", 0.005, 0.0, 0.0);
    	leftDriveMotor = new CANTalon(RobotConfig.getInstance().getLeftDriveMotor());
    	rightDriveMotor = new CANTalon(RobotConfig.getInstance().getRightDriveMotor());
    	
    	getPIDController().setAbsoluteTolerance(tolerance);
    	
    	getPIDController().setOutputRange(-0.75, 0.75);
    	
    	this.setSetpoint(RobotConfig.getInstance().getGyroHeading());
    	
    	
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    }

    public void initDefaultCommand() {

    }
    
//    public void drive(double leftSpeed, double rightSpeed) {
//    	leftDriveMotor.set(leftSpeed);
//    	rightDriveMotor.set(rightSpeed);
//    }

    protected double returnPIDInput() {
    	return RobotConfig.getInstance().getGyroHeading();
    }

    protected void usePIDOutput(double output) {
    	SmartDashboard.putNumber("PID Output", output);
    	SmartDashboard.putNumber("PID Error", this.getPIDController().getError());
    	leftDriveMotor.set(-(UIConfig.getInstance().getVelocity() - output));
    	rightDriveMotor.set((UIConfig.getInstance().getVelocity() + output));
    }
    
    public void setBrake() {
    	leftDriveMotor.enableBrakeMode(true);
    	rightDriveMotor.enableBrakeMode(true);
    }
    
    public void setCoast() {
    	leftDriveMotor.enableBrakeMode(false);
    	rightDriveMotor.enableBrakeMode(false);
    }

}
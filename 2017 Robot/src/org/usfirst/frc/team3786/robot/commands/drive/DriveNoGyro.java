package org.usfirst.frc.team3786.robot.commands.drive;

import org.usfirst.frc.team3786.robot.config.UIConfig;
import org.usfirst.frc.team3786.robot.subsystems.GyroDriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveNoGyro extends Command {
	
	private static DriveNoGyro instance;
	
	public static DriveNoGyro getInstance() {
		if(instance == null)
			instance = new DriveNoGyro();
		return instance;
	}
	
	private double GAIN = 1.2;

    public DriveNoGyro() {
    	requires(GyroDriveSubsystem.getInstance());
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	GyroDriveSubsystem.getInstance().disable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double throttle = UIConfig.getInstance().getTurn();
    	double turn = UIConfig.getInstance().getVelocity();
    	
    	double leftOut = throttle - turn;
    	double rightOut = throttle + turn;
    	
    	GyroDriveSubsystem.getInstance().manualDrive((leftOut + skim(leftOut)), (rightOut + skim(rightOut)));
    	System.err.println("Gyro Disabled");
    }
    
	private double skim(double v) {
		if (v > 1.0)
			return -((v - 1.0) * GAIN);
		else if (v < -1.0)
			return -((v + 1.0) * GAIN);
		return 0;
	}


    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

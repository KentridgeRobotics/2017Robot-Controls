package org.usfirst.frc.team3786.robot.commands.shooter;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3786.robot.subsystems.GirlsGenShooterSubsystem;

/**
 *
 */
public class GirlsGenShooter extends Command {
	private static GirlsGenShooter shoot_instance = null;
	private static GirlsGenShooter stop_instance = null;
	private final boolean doShoot;
	
    public GirlsGenShooter(boolean doShoot) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(GirlsGenShooterSubsystem.getInstance());
    	this.doShoot = doShoot;
    }
    
    public static GirlsGenShooter getShootInstance() {
    	if (shoot_instance == null) {
    		shoot_instance = new GirlsGenShooter(true);
    	}
    	return shoot_instance;
    }

    public static GirlsGenShooter getStopInstance() {
    	if (stop_instance == null) {
    		stop_instance = new GirlsGenShooter(false);
    	}
    	return stop_instance;
    }
    // Called just before this Command runs the first time
    protected void initialize() {
    	System.err.println("GirlGenShooter initialized");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double speed;
    	if (doShoot)
    		speed = 1.0;
    	else
    		speed = 0.0;

    	GirlsGenShooterSubsystem.getInstance().fling(speed);
      		
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

package org.usfirst.frc.team3786.robot.commands.shooter;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3786.robot.subsystems.GirlsGenShooterSubsystem;

/**
 *
 */
public class GirlsGenShooter extends Command {
	boolean isDone;
	int count = 0;
	static final int maxCount = 50;
	private static GirlsGenShooter instance = null;
	
    public GirlsGenShooter() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(GirlsGenShooterSubsystem.getInstance());
    }
    
    public static GirlsGenShooter getInstance() {
    	if (instance == null) {
    		instance = new GirlsGenShooter();
    	}
    	return instance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.err.println("GirlGenShooter initialized");
    	isDone = false;
    	count = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	++count;
    	GirlsGenShooterSubsystem.getInstance().fling(1.0);
  
    	if (count > maxCount) {
    		isDone = true;
    	}
    		
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isDone;
    }

    // Called once after isFinished returns true
    protected void end() {
    	GirlsGenShooterSubsystem.getInstance().fling(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

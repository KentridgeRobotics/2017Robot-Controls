package org.usfirst.frc.team3786.robot.commands.shooter;

import org.usfirst.frc.team3786.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 * What it does:
 * - Spin up the motor
 * - Swipe forward
 * - Swipe backward
 * Let's see if it works!
 */
public class ShooterCommand extends Command {
	long startTime;
	boolean isDone;
	
	private static final long initialRevTime = 2000;
	private static final long shootFirstSideTime = 3000;
	private static final long shootSecondSideTime = 3000;
	
    public ShooterCommand() {
    	requires(ShooterSubsystem.getInstance());
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	startTime = System.currentTimeMillis();
    	isDone = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	long now = System.currentTimeMillis();
    	long elapsedTime = now - startTime;
    	if (elapsedTime < initialRevTime)
    	{
    		ShooterSubsystem.getInstance().fling(1.0);
    	}
    	else if (elapsedTime < shootFirstSideTime + initialRevTime)
    	{
    		ShooterSubsystem.getInstance().fling(1.0);
    		ShooterSubsystem.getInstance().swipe(true);
    	}
    	else if (elapsedTime < shootSecondSideTime + shootFirstSideTime + initialRevTime)
    	{
    		ShooterSubsystem.getInstance().fling(1.0);
    		ShooterSubsystem.getInstance().swipe(false);
    		
    	}
    	else
    	{
    		isDone = true;
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isDone;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

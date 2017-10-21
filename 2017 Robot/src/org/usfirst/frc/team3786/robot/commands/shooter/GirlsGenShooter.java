package org.usfirst.frc.team3786.robot.commands.shooter;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3786.robot.subsystems.GirlsGenShooterSubsystem;

/**
 *
 */
public class GirlsGenShooter extends Command {
	boolean isDone;
	boolean shoot;

    public GirlsGenShooter() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(GirlsGenShooterSubsystem.getInstance());
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	isDone = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(shoot) {
    		GirlsGenShooterSubsystem.getInstance().fling(1);
    	}
    	else {
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

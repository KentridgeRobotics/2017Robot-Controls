package org.usfirst.frc.team3786.robot.commands.shooter;

import org.usfirst.frc.team3786.robot.subsystems.GirlsGenServoSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GirlsGenShooterServo extends Command {

	final boolean whichWay;
    public GirlsGenShooterServo(boolean whichWay) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(GirlsGenServoSubsystem.getInstance());
    	this.whichWay = whichWay;
    }

    private static GirlsGenShooterServo open_instance;
    private static GirlsGenShooterServo close_instance;
    
    public static GirlsGenShooterServo getOpenInstance() {
    	if (open_instance == null) {
    		open_instance = new GirlsGenShooterServo(true);
    	}
    	return open_instance;
    }

    public static GirlsGenShooterServo getCloseInstance() {
    	if (close_instance == null) {
    		close_instance = new GirlsGenShooterServo(false);
    	}
    	return close_instance;
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	GirlsGenServoSubsystem.getInstance().startSwipe();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	GirlsGenServoSubsystem.getInstance().swipe(whichWay);
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

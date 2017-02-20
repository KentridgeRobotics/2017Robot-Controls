package org.usfirst.frc.team3786.robot.commands.test;

import org.usfirst.frc.team3786.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ZeroEncoders extends Command {
	
	private static ZeroEncoders instance;
	
	public static ZeroEncoders getInstance() {
		if(instance == null)
			instance = new ZeroEncoders();
		return instance;
	}

    public ZeroEncoders() {
    	requires(DriveTrain.getInstance());
    	//setTimeout(.1);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	DriveTrain.getInstance().zeroEncoders();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	DriveTrain.getInstance().setSpeed(-.25, .25);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

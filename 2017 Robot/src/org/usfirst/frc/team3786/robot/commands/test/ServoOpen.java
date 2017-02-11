package org.usfirst.frc.team3786.robot.commands.test;

import org.usfirst.frc.team3786.robot.Robot;
import org.usfirst.frc.team3786.robot.subsystems.ServoTest;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ServoOpen extends Command {
	public static ServoOpen instance;
	
	public static ServoOpen getInstance() {
		if(instance == null)
			instance = new ServoOpen();
		return instance;
	}

	
    public ServoOpen() {
		requires(ServoTest.getInstance());
		setTimeout(.9);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	ServoTest.getInstance().open();
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

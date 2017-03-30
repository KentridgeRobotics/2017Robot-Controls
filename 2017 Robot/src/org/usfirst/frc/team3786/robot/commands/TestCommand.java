package org.usfirst.frc.team3786.robot.commands;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TestCommand extends Command {
	
	Servo twoTest;
	Servo threeTest;

    public TestCommand() {
    	twoTest = new Servo(2);
    	threeTest = new Servo(3);
    	setTimeout(1.0);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	twoTest.set(1.0);
    	threeTest.set(1.0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	twoTest.set(0.0);
    	threeTest.set(0.0);

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

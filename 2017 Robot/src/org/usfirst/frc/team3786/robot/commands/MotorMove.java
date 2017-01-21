package org.usfirst.frc.team3786.robot.commands;

import org.usfirst.frc.team3786.robot.Robot;
import org.usfirst.frc.team3786.robot.subsystems.MiniCIM;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MotorMove extends Command {

	public static MotorMove instance;
	
	public static MotorMove  getInstance() {
		if(instance == null)
			instance = new MotorMove();
		return instance;
	}

    public MotorMove() {
       requires(MiniCIM.getInstance());
       setTimeout(.9);
    }
    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	MiniCIM.getInstance().setSpeed(.1);
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

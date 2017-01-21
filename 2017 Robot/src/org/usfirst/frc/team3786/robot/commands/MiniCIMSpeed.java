package org.usfirst.frc.team3786.robot.commands;

import org.usfirst.frc.team3786.robot.config.ControlConfig;
import org.usfirst.frc.team3786.robot.subsystems.MiniCIM;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MiniCIMSpeed extends Command {
	
	public static MiniCIMSpeed instance;
	
	public static MiniCIMSpeed  getInstance() {
		if(instance == null)
			instance = new MiniCIMSpeed();
		return instance;
	}

    public MiniCIMSpeed() {
       requires(MiniCIM.getInstance());
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	MiniCIM.getInstance().setSpeed(ControlConfig.getInstance().getLeftStick().getY() * -.25);
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

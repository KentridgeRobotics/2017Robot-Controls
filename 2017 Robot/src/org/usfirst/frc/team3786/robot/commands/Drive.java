package org.usfirst.frc.team3786.robot.commands;

import org.usfirst.frc.team3786.robot.config.ControlConfig;
import org.usfirst.frc.team3786.robot.config.OI;
import org.usfirst.frc.team3786.robot.subsystems.MiniCIM;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Drive extends Command {
	private static Drive instance;
	public static Drive getInstance() {
		if(instance == null)
			instance = new Drive();
		return instance;
	}
	public Drive() {
		// Use requires() here to declare subsystem dependencies
		requires(MiniCIM.getInstance());
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		MiniCIM.getInstance().setSpeed(ControlConfig.getInstance().getLeftStick().getY() * -.25);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}

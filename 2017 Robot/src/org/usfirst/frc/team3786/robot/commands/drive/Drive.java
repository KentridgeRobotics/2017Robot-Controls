package org.usfirst.frc.team3786.robot.commands.drive;

import org.usfirst.frc.team3786.robot.config.UIConfig;
import org.usfirst.frc.team3786.robot.subsystems.DriveTrain;

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
		requires(DriveTrain.getInstance());
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		DriveTrain.getInstance().setSpeed(UIConfig.getInstance().getLeftDrive(), UIConfig.getInstance().getRightDrive());
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

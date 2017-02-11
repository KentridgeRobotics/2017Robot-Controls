package org.usfirst.frc.team3786.robot.commands.grabber;

import org.usfirst.frc.team3786.robot.config.UIConfig;
import org.usfirst.frc.team3786.robot.subsystems.GearArm;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveGearArmManual extends Command {
	
	private static MoveGearArmManual instance;
	
	public static MoveGearArmManual getInstance() {
		if(instance == null)
			instance = new MoveGearArmManual();
		return instance;
	}

    public MoveGearArmManual() {
    	requires(GearArm.getInstance());
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	GearArm.getInstance().setManualDrive();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		    if(UIConfig.getInstance().getLeftStick().getPOV(0) == 0)
				GearArm.getInstance().setSpeed(.1);
			else if(UIConfig.getInstance().getLeftStick().getPOV(0) == 180)
				GearArm.getInstance().setSpeed(-.1);
			else
				GearArm.getInstance().setSpeed(0);

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

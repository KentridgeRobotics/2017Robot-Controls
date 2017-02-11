package org.usfirst.frc.team3786.robot.commands.grabber;

import org.usfirst.frc.team3786.robot.config.UIConfig;
import org.usfirst.frc.team3786.robot.subsystems.GearArm;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class MoveGearArmPosition extends Command {

	private static MoveGearArmPosition instance;
	
	public static MoveGearArmPosition getInstance() {
		if(instance == null) 
			instance = new MoveGearArmPosition();
		return instance;
	}
	
    public MoveGearArmPosition() {
    	requires(GearArm.getInstance());
    }

    protected void initialize() {
    	GearArm.getInstance().setPositionDrive();
		GearArm.getInstance().setPosition(500);
    }

    protected void execute() {

    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}

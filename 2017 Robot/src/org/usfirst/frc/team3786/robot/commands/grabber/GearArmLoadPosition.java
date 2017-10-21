package org.usfirst.frc.team3786.robot.commands.grabber;

import org.usfirst.frc.team3786.robot.config.RobotConfig;
import org.usfirst.frc.team3786.robot.config.UIConfig;
import org.usfirst.frc.team3786.robot.subsystems.GearArm;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class GearArmLoadPosition extends Command {

	private static GearArmLoadPosition instance;
	
	public static GearArmLoadPosition getInstance() {
		if(instance == null) 
			instance = new GearArmLoadPosition();
		return instance;
	}
	
    public GearArmLoadPosition() {
    	requires(GearArm.getInstance());
    	setTimeout(1.75);
    }

    protected void initialize() {
    	GearArm.getInstance().setPositionDrive();
    	if(GearArm.getInstance().getPosition() > RobotConfig.getInstance().getLoadPosition())
    		GearArm.getInstance().setPosition(-RobotConfig.getInstance().getLoadPosition()+20);
    	else
    		GearArm.getInstance().setPosition(-RobotConfig.getInstance().getLoadPosition() - 35);
    }

    protected void execute() {

    }

    protected boolean isFinished() {
        return (UIConfig.getInstance().getXbox().getPOV(0) == 0 || UIConfig.getInstance().getXbox().getPOV(0) == 180);
    }

    protected void end() {
    	MoveGearArmManual.getInstance();
    }

    protected void interrupted() {
    }
}

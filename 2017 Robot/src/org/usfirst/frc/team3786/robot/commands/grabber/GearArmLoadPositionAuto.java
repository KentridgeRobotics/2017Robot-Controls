package org.usfirst.frc.team3786.robot.commands.grabber;

import org.usfirst.frc.team3786.robot.config.UIConfig;
import org.usfirst.frc.team3786.robot.subsystems.GearArm;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class GearArmLoadPositionAuto extends Command {

	private static GearArmLoadPositionAuto instance;
	
	public static GearArmLoadPositionAuto getInstance() {
		if(instance == null) 
			instance = new GearArmLoadPositionAuto();
		return instance;
	}
	
    public GearArmLoadPositionAuto() {
    	requires(GearArm.getInstance());
    	setTimeout(1.75);
    }

    protected void initialize() {
    	GearArm.getInstance().open();
    	GearArm.getInstance().setPositionDrive();
    	if(GearArm.getInstance().getPosition() > UIConfig.getInstance().getPegPosition())
    		GearArm.getInstance().setPosition(-UIConfig.getInstance().getPegPosition()+20);
    	else
    		GearArm.getInstance().setPosition(-UIConfig.getInstance().getPegPosition() - 35);
    }

    protected void execute() {

    }

    protected boolean isFinished() {
        return (isTimedOut() && GearArm.getInstance().getVoltage() < 1.1);
    }

    protected void end() {
    	//ServoMove.getInstance();
    }

    protected void interrupted() {
    }
}

package org.usfirst.frc.team3786.robot.commands.auto;

import org.usfirst.frc.team3786.robot.commands.grabber.GearArmLoadPositionAuto;
import org.usfirst.frc.team3786.robot.config.UIConfig;
import org.usfirst.frc.team3786.robot.subsystems.GearArm;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoMoveDown extends Command {
	
	
private static GearArmLoadPositionAuto instance;
	
	public static GearArmLoadPositionAuto getInstance() {
		if(instance == null) 
			instance = new GearArmLoadPositionAuto();
		return instance;
	}
	
    public AutoMoveDown() {
    	requires(GearArm.getInstance());
    	setTimeout(1.75);
    }

    protected void initialize() {
    	GearArm.getInstance().open();
    	GearArm.getInstance().setPositionDrive();
    	GearArm.getInstance().setPosition(-325);
//    	if(GearArm.getInstance().getPosition() > UIConfig.getInstance().getPegPosition())
//    		GearArm.getInstance().setPosition(-UIConfig.getInstance().getPegPosition()+20);
//    	else
//    		GearArm.getInstance().setPosition(-UIConfig.getInstance().getPegPosition() - 35);
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

//    public AutoMoveDown() {
//    	requires(GearArm.getInstance());
//    	setTimeout(.6);
//    }
//
//    // Called just before this Command runs the first time
//    protected void initialize() {
//    	GearArm.getInstance().setManualDrive();
//    	GearArm.getInstance().setSpeed(-.2);
//    }
//
//    // Called repeatedly when this Command is scheduled to run
//    protected void execute() {
//    }
//
//    // Make this return true when this Command no longer needs to run execute()
//    protected boolean isFinished() {
//        return isTimedOut();
//    }
//
//    // Called once after isFinished returns true
//    protected void end() {
//    	GearArm.getInstance().setSpeed(0);
//    }
//
//    // Called when another command which requires one or more of the same
//    // subsystems is scheduled to run
//    protected void interrupted() {
//    }
}

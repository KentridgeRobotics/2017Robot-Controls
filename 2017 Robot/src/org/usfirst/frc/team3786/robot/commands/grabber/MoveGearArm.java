package org.usfirst.frc.team3786.robot.commands.grabber;

import org.usfirst.frc.team3786.robot.config.ControlConfig;
import org.usfirst.frc.team3786.robot.subsystems.GearArm;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class MoveGearArm extends Command {

	private static MoveGearArm instance;
	
	public static MoveGearArm getInstance() {
		if(instance == null) 
			instance = new MoveGearArm();
		return instance;
	}
	
    public MoveGearArm() {
    	requires(GearArm.getInstance());
    }

    // Called just before this Command runs the first time
    protected void initialize() {
		//GearArm.getInstance().setPosition(500);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	if(ControlConfig.getInstance().getLeftStick().getPOV(0) == 0)
//    		GearArm.getInstance().setSpeed(1);
//    	else if(ControlConfig.getInstance().getLeftStick().getPOV(0) == 180)
//    		GearArm.getInstance().setSpeed(-1);
//    	else
//    		GearArm.getInstance().setSpeed(0);
   
    	GearArm.getInstance().setPosition(500);
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

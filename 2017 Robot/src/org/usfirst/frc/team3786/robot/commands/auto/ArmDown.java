package org.usfirst.frc.team3786.robot.commands.auto;

import org.usfirst.frc.team3786.robot.subsystems.GearArm;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArmDown extends Command {

    public ArmDown() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(GearArm.getInstance());
    	setTimeout(2.75);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Timer.delay(1);
    	GearArm.getInstance().setPositionDrive();
    	GearArm.getInstance().setPosition(-500);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (isTimedOut() && GearArm.getInstance().getVoltage() < 1.1);
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

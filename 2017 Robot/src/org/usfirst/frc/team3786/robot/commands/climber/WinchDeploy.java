package org.usfirst.frc.team3786.robot.commands.climber;

import org.usfirst.frc.team3786.robot.subsystems.Winch;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class WinchDeploy extends Command {
	
	private enum Mode {
		MOVE, ENABLE, DISABLE
	}
	
	private Mode instanceMode;
	
	private static WinchDeploy moveInstance;
	private static WinchDeploy enableInstance;
	private static WinchDeploy disableInstance;
	
	public static WinchDeploy getMoveInstance() {
		if(moveInstance == null)
			moveInstance = new WinchDeploy(Mode.MOVE);
		return moveInstance;
	}
	
	public static WinchDeploy getEnableInstance() {
		if(enableInstance == null)
			enableInstance = new WinchDeploy(Mode.ENABLE);
		return enableInstance;
	}
	
	public static WinchDeploy getDisableInstance() {
		if(disableInstance == null)
			disableInstance = new WinchDeploy(Mode.DISABLE);
		return disableInstance;
	}

	private static boolean isEnabled;


    public WinchDeploy(Mode m) {
    	requires(Winch.getInstance());
    	instanceMode = m;
    	isEnabled = false;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(instanceMode == Mode.ENABLE)
    		isEnabled = true;
    	else if(instanceMode == Mode.DISABLE)
    		isEnabled = false;
    	
    	if(isEnabled)
    		System.out.println("Now moving");
    	else
    		System.out.println("Not moving");
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

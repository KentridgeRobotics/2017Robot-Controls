package org.usfirst.frc.team3786.robot.commands.climber;

import org.usfirst.frc.team3786.robot.subsystems.Winch;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class WinchDeploy extends Command {
	
	private enum Mode {
		MOVE, STOP
	}
	
	private Mode instanceMode;
	
	private static WinchDeploy moveInstance;
	private static WinchDeploy stopInstance;
	
	public static WinchDeploy getMoveInstance() {
		if(moveInstance == null)
			moveInstance = new WinchDeploy(Mode.MOVE);
		return moveInstance;
	}
	
	public static WinchDeploy getStopInstance() {
		if(stopInstance == null)
			stopInstance = new WinchDeploy(Mode.STOP);
		return stopInstance;
	}

	
    public WinchDeploy(Mode m) {
    	requires(Winch.getInstance());
    	instanceMode = m;
    	//isEnabled = false;
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {    	
    	if(instanceMode == Mode.MOVE)
    		System.out.println("Move");
    	if(instanceMode == Mode.STOP)
    		System.out.println("Stop");
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

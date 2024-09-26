package org.usfirst.frc.team3786.robot.commands.climber;

import org.usfirst.frc.team3786.robot.subsystems.Winch;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class WinchDeploy extends Command {
	
	private enum Mode {
		MOVE, STOP, REVERSE
	}
	
	private Mode instanceMode;
	
	private static WinchDeploy moveInstance;
	private static WinchDeploy stopInstance;
	private static WinchDeploy reverseInstance;
	
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
	
	public static WinchDeploy getReverseInstance() {
		if(reverseInstance == null)
			reverseInstance = new WinchDeploy(Mode.REVERSE);
		return reverseInstance;
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
    	if(instanceMode == Mode.MOVE) {
    		System.out.println("Move");
    		Winch.getInstance().setDeploySpeed(.75);
    	}
    	if(instanceMode == Mode.STOP) {
    		System.out.println("Stop");
    		Winch.getInstance().setDeploySpeed(0);
    	}
    	if(instanceMode == Mode.REVERSE) {
    		System.out.println("Reverse");
    		Winch.getInstance().setDeploySpeed(-.75);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

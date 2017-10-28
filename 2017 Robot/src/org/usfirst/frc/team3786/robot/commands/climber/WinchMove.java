package org.usfirst.frc.team3786.robot.commands.climber;

import org.usfirst.frc.team3786.robot.subsystems.Winch;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class WinchMove extends Command {

	private static WinchMove upInstance;
	private static WinchMove downInstance;
	private static WinchMove stopInstance;
	
	private enum Mode {
		UP, DOWN, STOP
	}
	
	private Mode _driveMode;
	
	public static WinchMove getUpInstance() {
		if(upInstance == null) 
			upInstance = new WinchMove(Mode.UP);
		return upInstance;
	}
	
	public static WinchMove getDownInstance() {
		if(downInstance == null)
			downInstance = new WinchMove(Mode.DOWN);
		return downInstance;
	}
	
	public static WinchMove getStopInstance() {
		if(stopInstance == null) 
			stopInstance = new WinchMove(Mode.STOP);
		return stopInstance;
	}
	
	
    public WinchMove(Mode m) {
    	requires(Winch.getInstance());
    	_driveMode = m;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(_driveMode == Mode.UP)
    		Winch.getInstance().setSpeed(-0.1);
    	else if (_driveMode == Mode.DOWN)
    		Winch.getInstance().setSpeed(0.1);
    	else
    		Winch.getInstance().setSpeed(0);

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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

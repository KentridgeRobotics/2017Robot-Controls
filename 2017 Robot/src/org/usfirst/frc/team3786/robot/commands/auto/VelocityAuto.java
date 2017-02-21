package org.usfirst.frc.team3786.robot.commands.auto;

import org.usfirst.frc.team3786.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class VelocityAuto extends Command {
	
	private boolean isDone;
	
	private int leftPos;
	private int rightPos;

    public VelocityAuto(int lPos, int rPos) {
    	requires(DriveTrain.getInstance());
    	isDone = false;
    	leftPos = lPos;
    	rightPos = lPos;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	DriveTrain.getInstance().zeroEncoders();
    	DriveTrain.getInstance().setSpeedDrive();
    	DriveTrain.getInstance().setBrake();
    	int leftMultiply = 1;
    	int rightMultiply = 1;
    	if(leftPos > 0)
    		leftMultiply = -1;
    	if(rightPos < 0)
    		rightMultiply = -1;
    	DriveTrain.getInstance().setSpeed(.75 * leftMultiply, .75 * rightMultiply);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Math.abs(DriveTrain.getInstance().getLeftEncoder()) > Math.abs(leftPos) && Math.abs(DriveTrain.getInstance().getRightEncoder()) > Math.abs(rightPos))
    		isDone = true;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isDone;
    }

    // Called once after isFinished returns true
    protected void end() {
    	DriveTrain.getInstance().setSpeed(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

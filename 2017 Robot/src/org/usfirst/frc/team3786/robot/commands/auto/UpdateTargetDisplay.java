package org.usfirst.frc.team3786.robot.commands.auto;

import java.util.List;

import org.usfirst.frc.team3786.robot.autonomous.RobotAction;
import org.usfirst.frc.team3786.robot.autonomous.RobotActionGenerator;
import org.usfirst.frc.team3786.robot.vision.TargetPosition;
import org.usfirst.frc.team3786.robot.vision.VisionUtil;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class UpdateTargetDisplay extends Command {

	private List<TargetPosition> targetPositions;
	private static UpdateTargetDisplay instance;
	private int maxActionCount = 0;
	public static UpdateTargetDisplay getInstance() {
		if (instance == null)
		{
			instance = new UpdateTargetDisplay();
		}
		return instance;
	}
	public int getTargetCount()
	{
		return targetPositions.size();
	}
	
	private double getDirectionTarget0() {
		if (targetPositions.size() > 0)
		{
			return targetPositions.get(0).getTargetDirectionDegrees();
		}
		else
		{
			return 8675309.0;
		}
	}
	
	private double getDistanceTarget0() {
		if (targetPositions.size() > 0)
		{
			return targetPositions.get(0).getDistanceToTargetInInches();
		}
		else
		{
			return -8675309.0;
		}
	}
	
    public UpdateTargetDisplay() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	targetPositions = VisionUtil.getTargetPositionToGearTarget();
    	if ((targetPositions != null) && (targetPositions.size() > 0)) {
    		SmartDashboard.putNumber("Target count", getTargetCount());
    		SmartDashboard.putNumber("TargetDirection", getDirectionTarget0());
    		SmartDashboard.putNumber("TargetDistance", getDistanceTarget0());
    	}
    	List<RobotAction> robotActions = RobotActionGenerator.extractActions(targetPositions);
    	int index = 0;
    	if (robotActions != null)
    		if (robotActions.size() > maxActionCount)
    		{
    			maxActionCount = robotActions.size();
    		}
    	
    	for (; index < maxActionCount; ++index)
    	{
    		String str;
    		if (index < robotActions.size())
    		{
    			str = robotActions.get(index).toString();
    		}
    		else {
    			str = "";
    		}
    		SmartDashboard.putString("RobotAction"+index, str);
    	}
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

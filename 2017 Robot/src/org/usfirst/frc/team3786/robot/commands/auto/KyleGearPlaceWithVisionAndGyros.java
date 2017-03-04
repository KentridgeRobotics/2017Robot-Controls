package org.usfirst.frc.team3786.robot.commands.auto;

import java.util.List;

import org.usfirst.frc.team3786.robot.config.RobotConfig;
import org.usfirst.frc.team3786.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3786.robot.subsystems.Rangefinders;
import org.usfirst.frc.team3786.robot.vision.TargetPosition;
import org.usfirst.frc.team3786.robot.vision.VisionUtil;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Kahl Smitty
 */
public class KyleGearPlaceWithVisionAndGyros extends Command {

	private boolean isDone = false;
	private final boolean useVision;
	private final static double ANGLE_TOLERANCE = 0.5;
	private int executeCountBeforeVisionFix = 0;
	private static final int MAX_EXECUTES_BEFORE_VISION_FIX = 10;
	private boolean hasDriven = false;
	private boolean isTurning = false;
	private double startTheta;
	private double endTheta;
	private final boolean turnByVisionAlone;
	
	public KyleGearPlaceWithVisionAndGyros(boolean useVision, boolean turnByVisionAlone) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);

		requires(Rangefinders.getInstance());
		this.useVision = useVision;
		this.turnByVisionAlone = turnByVisionAlone;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		executeCountBeforeVisionFix = 0;
		isDone = false;
		hasDriven = false;
		isTurning = false;
		startTheta = 0.0;
		endTheta = 0.0;
    	DriveTrain.getInstance().setSpeedDrive();
    	DriveTrain.getInstance().setBrake();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Rangefinders Range1 = Rangefinders.getInstance();
		TargetPosition targetPosition = null;
		boolean isTargetPositionExact = false;

		double RangeMult = (Range1.getRange() * .01);
		double VoltsMult = (Range1.getTestVoltage() / 3.0);
		double Range = Range1.getRange();
		double volts = Range1.getTestVoltage();
		--executeCountBeforeVisionFix;
		System.err.println(volts);
		if (useVision && executeCountBeforeVisionFix <= 0) {
			List<TargetPosition> targetPositions = VisionUtil.getTargetPositionToGearTarget();
			if (targetPositions.size() == 1) {
				targetPosition = targetPositions.get(0);
				isTargetPositionExact = false;
			} else if (targetPositions.size() == 2) {
				// We must have 2 positions, so we're going straight in!
				double targetAngle = (targetPositions.get(0).getTargetDirectionDegrees()
						+ targetPositions.get(1).getTargetDirectionDegrees()) * 0.5;
				double targetDistance = (targetPositions.get(0).getDistanceToTargetInInches()
						+ targetPositions.get(1).getDistanceToTargetInInches()) * 0.5;
				targetPosition = new TargetPosition(targetAngle, targetDistance, 0.0);
				isTargetPositionExact = true;
				startTheta = RobotConfig.getInstance().getGyroHeading();
				endTheta = startTheta + targetPosition.getTargetDirectionDegrees();
				if (!turnByVisionAlone)
				{
					isTurning = true;
					executeCountBeforeVisionFix = MAX_EXECUTES_BEFORE_VISION_FIX;
					return;
				}
			} 
		}
		
		if (isTurning)
		{
			boolean goingUp = (endTheta > startTheta);
			double currTheta = RobotConfig.getInstance().getGyroHeading();

			if (goingUp && currTheta >= endTheta) {
				isTurning = false;
				DriveTrain.getInstance().setSpeed(0, 0);
			}
			else if (!goingUp && currTheta <= endTheta)
			{
				isTurning = false;
				DriveTrain.getInstance().setSpeed(0, 0);
			}
			else if (currTheta >= endTheta)
			{
				// let's count down
				DriveTrain.getInstance().setSpeed(-0.1, -0.1);
			}
			else {
				// counting up!
				DriveTrain.getInstance().setSpeed(0.1, 0.1);
			}
			
		}
		
		if (turnByVisionAlone && useVision && (targetPosition != null) && isTargetPositionExact) {
			// We have a target position, and it's "exact" (we can see both
			// vision target rectangles). SO MAYBE LET'S TURN!
			if (Math.abs(targetPosition.getTargetDirectionDegrees()) >= ANGLE_TOLERANCE) {
				// We need to turn!
				if (targetPosition.getTargetDirectionDegrees() < 0.0) {
					DriveTrain.getInstance().setSpeed(-0.1, -0.1);
				}
				else {
					DriveTrain.getInstance().setSpeed(0.1, 0.1);
				}
			}
			else {
				executeCountBeforeVisionFix = MAX_EXECUTES_BEFORE_VISION_FIX;
			}

		} else if (volts < .8) {
			// No vision, no need to turn. Let's drive!
			DriveTrain.getInstance().setSpeed(.2, -.2);
			hasDriven = true;
		}
		// if (volts >= .6 && volts <= .8 ){
		//
		// DriveTrain.getInstance().setSpeed(VoltsMult, -VoltsMult );
		//
		// }
		// if(volts < .6){
		//
		// DriveTrain.getInstance().setSpeed(.25, -.25);
		// }
		else if (volts >= .8 && hasDriven == true) {
			// Or just stop.
			DriveTrain.getInstance().setSpeed(0, 0);
			isDone = true;
		}

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return isDone;
	}

	// Called once after isFinished returns true
	protected void end() {
		isDone = false;
		hasDriven = false;
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}

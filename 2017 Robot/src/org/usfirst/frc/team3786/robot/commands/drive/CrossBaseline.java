package org.usfirst.frc.team3786.robot.commands.drive;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.MatOfPoint;
import org.usfirst.frc.team3786.robot.config.CompetitionConfig;
import org.usfirst.frc.team3786.robot.subsystems.GearTargetFinder;
import org.usfirst.frc.team3786.robot.vision.ContourReport;

import edu.wpi.first.wpilibj.command.CommandGroup;

enum WhichSide
{
	LEFT,
	RIGHT,
	WHO_KNOWS
}
/**
 *
 */
public class CrossBaseline extends CommandGroup {
	static final double robotRotationsPerWheelRotation = (220.0/180.0);
	static final double robotDegreesPerDistance = (5.9528082612573013834882986734396);

	public AutonomousDrive RotateRobot (double degrees) {
		return new AutonomousDrive(-degrees*robotRotationsPerWheelRotation, degrees*robotRotationsPerWheelRotation);
	}

	public AutonomousDrive DriveRobot (double distance) {
		return new AutonomousDrive(distance*robotDegreesPerDistance, distance*robotDegreesPerDistance);
	}

	WhichSide getPositionOfGearTarget() {
		GearTargetFinder gtf = CompetitionConfig.gearTargetFinder;
		ArrayList<MatOfPoint> matlist = gtf.runVisionThread();
		List<ContourReport> contourList = gtf.extractContourReports(matlist);
		int contoursCenter = 0; 
		if (contourList.size() == 0)
		{
			return WhichSide.WHO_KNOWS;
		}
		for (ContourReport contour : contourList)
		{
			contoursCenter += contour.getCenterX();
		}
		contoursCenter /= contourList.size();
		
		if (contoursCenter <= 320) {
			return WhichSide.LEFT;
		}
		else {
			return WhichSide.RIGHT;
		}
	}
	
	
	public CrossBaseline() {
		// Add Commands here:
		// e.g. addSequential(new Command1());
		//      addSequential(new Command2());
		// these will run in order.

		// To run multiple commands at the same time,
		// use addParallel()
		// e.g. addParallel(new Command1());
		//      addSequential(new Command2());
		// Command1 and Command2 will run in parallel.

		// A command group will require all of the subsystems that each member
		// would require.
		// e.g. if Command1 requires chassis, and Command2 requires arm,
		// a CommandGroup containing them would require both the chassis and the
		// arm.

		WhichSide whichSide = getPositionOfGearTarget();
		addSequential(DriveRobot (2.0));

		if (whichSide == WhichSide.RIGHT) {
			// The targets are mostly off to the right, so we should turn left
			addSequential(RotateRobot (-45.0));
		}
		else if (whichSide == WhichSide.LEFT) {
			// The targets are mostly off to the left, so we should turn right
			addSequential(RotateRobot (45.0));
		}
		else {
			// Who knows where the targets are? Maybe the way ahead is clear. YOLO.
		}
		addSequential(DriveRobot (10.0));
	}
	
}

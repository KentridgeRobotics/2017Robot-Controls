package org.usfirst.frc.team3786.robot.commands.drive;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.MatOfPoint;
import org.usfirst.frc.team3786.robot.config.CompetitionConfig;
import org.usfirst.frc.team3786.robot.subsystems.GearTargetFinder;
import org.usfirst.frc.team3786.robot.utility.ContourReport;

import edu.wpi.first.wpilibj.command.CommandGroup;

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

	double getPositionOfGearTarget() {
		GearTargetFinder gtf = CompetitionConfig.gearTargetFinder;
		ArrayList<MatOfPoint> matlist = gtf.runVisionThread();
		List<ContourReport> contourList = gtf.extractContourReports(matlist);
		
		return 0.0;
		//Come back here
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

		double gearTargetPosition = getPositionOfGearTarget();
		addSequential(DriveRobot (2.0));

		if (gearTargetPosition > 0) {
			addSequential(RotateRobot (-45.0));

		}
		else {
			addSequential(RotateRobot (45.0));
		}
		addSequential(DriveRobot (10.0));
	}
}

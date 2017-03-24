package org.usfirst.frc.team3786.robot.commands.auto.obsolete;

import java.util.ArrayList;
import java.util.List;

import org.usfirst.frc.team3786.robot.commands.auto.GyroTurn;
import org.usfirst.frc.team3786.robot.commands.drive.AutonomousDrive;
import org.usfirst.frc.team3786.robot.config.RobotConfig;
import org.usfirst.frc.team3786.robot.vision.TargetPosition;
import org.usfirst.frc.team3786.robot.vision.VisionUtil;
import org.usfirst.frc.team3786.robot.vision.WhichSide;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.ConditionalCommand;


/**
 *
 */
public class CrossBaseline extends CommandGroup {
	public static final double inchesToBaseline = 12.0 * 7.0 + 9.25;
	static final double inchesToDrive = 2.0 * inchesToBaseline / Math.sqrt(3.0);
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

		// Go forward far enough to not bump into the back wall when we rotate
		//addSequential(AutonomousDrive.DriveRobot(60.0));
		//addSequential(AutonomousDrive.DriveRobot(-RobotConfig.lengthOfRobotInches));
		// Rotate
		List<TargetPosition> targetList = new ArrayList<TargetPosition>();
		addSequential(new FindATarget(targetList));
		addSequential(new DisplayNextTarget(targetList));
		addSequential(new GyroTurn(targetList));
		
		addSequential(new AutonomousDrive(targetList));
		addSequential(new ClearTargetPosition(targetList));

		
		addSequential(new FindATarget(targetList));
		addSequential(new DisplayNextTarget(targetList));
//		addSequential(new GyroTurn(targetList));
		addSequential(new ClearTargetPosition(targetList));
		
//		addSequential(new AutonomousDrive(targetList));
//		addSequential(new FindATarget(targetList));
//		addSequential(new DisplayNextTarget(targetList));
//		addSequential(new GyroTurn(targetList));
//		addSequential(new ClearTargetPosition(targetList));
		
//		addSequential(new AutonomousDrive(targetList));

		
		//addSequential(new MaybeTurn(new GyroTurn(45.0), new GyroTurn(45.0)));
		//addSequential(AutonomousDrive.DriveRobot(60.0));
/*		addSequential(new GyroTurn(-180.0));
		addSequential(AutonomousDrive.DriveRobot(30.0));
		addSequential(new GyroTurn(180.0));
		addSequential(AutonomousDrive.DriveRobot(30.0));
	*/	
		//addSequential(new TurnDegrees(90.0));
		// onTrue condition is to turn left, onFalse is to turn right.
//		addSequential(new MaybeTurn(new TurnDegrees(-90.0), new TurnDegrees(90.0)));
		
		// Drive forward until we're across the baseline
		//addSequential(AutonomousDrive.DriveRobot(inchesToDrive));
	}
	
	class MaybeTurn extends ConditionalCommand
	{
		
		public MaybeTurn(Command onTrue, Command onFalse) {
			super(onTrue, onFalse);
		}

		// Returns true if the target is to the RIGHT!
		@Override
		protected boolean condition() {
			WhichSide whichSide = VisionUtil.getPositionOfGearTarget();
			System.err.println("Position of gear target: "+whichSide);
			if (whichSide == WhichSide.RIGHT)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		
	}
}

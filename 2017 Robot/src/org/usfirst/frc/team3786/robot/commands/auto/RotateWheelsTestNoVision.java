package org.usfirst.frc.team3786.robot.commands.auto;

import org.usfirst.frc.team3786.robot.commands.drive.AutonomousDrive;
import org.usfirst.frc.team3786.robot.commands.grabber.GearArmLoadPositionAuto;
import org.usfirst.frc.team3786.robot.commands.grabber.ServoMoveAuto;
import org.usfirst.frc.team3786.robot.config.RobotConfig;
import org.usfirst.frc.team3786.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3786.robot.subsystems.Rangefinders;
import org.usfirst.frc.team3786.robot.vision.FinderOfRange;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RotateWheelsTestNoVision extends CommandGroup {

    public RotateWheelsTestNoVision() {	
    	
		requires(Rangefinders.getInstance());
    	
		
    	
    	 
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
    	//addSequential(GearArmLoadPositionAuto.getInstance());
    	//addSequential(new AutonomousDrive(360.0, 360.0));
    	
					// try number 2
		
		addSequential (new ServoClose());
		addSequential (new KyleGearPlace());
		addSequential (new ArmDown());
		addSequential (new KyleGearPlaceCloser());
		addSequential (new ServoOpen());
		addParallel (new AutoDriveNoEncoder(-.25,.25, 2));
		addSequential(new ArmDownMore());
		addSequential(new ArmUp());
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
    	 //DriveTrain.getInstance().setSpeed(.25, .25);
    	//addSequential(new AutoMoveDown());
    	//addSequential (new KyleGearPlace());
    	//addSequential(ServoMoveAuto.getInstance());
    	
    	//addSequential(AutonomousDrive.DriveRobot(61.0));
    	
    	
//    	addParallel(new AutonomousDrive(-10, -10));
    	 			//addSequential(new DistanceByCamera());
    	//addSequential(new AutonomousDrive(180, 180));//drives robot backward
    	//addSequential(new AutonomousDrive(-1000.0, -1000.0));
    }
}

package org.usfirst.frc.team3786.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoPlaceAGear extends CommandGroup {

    public AutoPlaceAGear() {
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
    	
    	
    	// 0. Run the vision process to get a list of MatOfPoints
    	
    	// 1. Get the ContourReports
    	
    	// 2. Convert the ContourReports into TargetPositions
    	
    	// 3. Convert TargetPositions into RobotActions
    	
    	// 4. For each RobotAction, either turn or drive.
    	
    	// 5. See if we're close enough.
    	
    	// 6. If we're close enough, place a gear, otherwise start the process again at step 1.
    }
}

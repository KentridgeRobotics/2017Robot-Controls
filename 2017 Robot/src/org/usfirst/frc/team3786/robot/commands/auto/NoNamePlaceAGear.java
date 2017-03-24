package org.usfirst.frc.team3786.robot.commands.auto;

import org.usfirst.frc.team3786.robot.vision.WhichSide;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * If WhichSide is left or right:
 * 1. Go forward (about to baseline)
 * 2. Turn 60 degrees right (if WhichSide is left) or left (if right)
 *
 * Then, no matter what WhichSide is given, navigate to the target using vision, gyro, and rangefinder
 * Finally, place the gear.
 */
public class NoNamePlaceAGear extends CommandGroup {

    public NoNamePlaceAGear(WhichSide whichSide) {
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
    	
    	// Step 1. Maybe go forward.
    	if (whichSide == WhichSide.LEFT || whichSide == WhichSide.RIGHT)
    	{
    		// Go forward, about to baseline
    	}
    	
    	// Step 2: Maybe turn 60 degrees
    	if (whichSide == WhichSide.LEFT)
    	{
    		// turn right
    	}
    	else if (whichSide == WhichSide.RIGHT)
    	{
    		// turn left
    	}
    	
    	// Step 3: Go straight to the gear target.
    	
    	
    	// Step 4: Place the gear.
    }
}

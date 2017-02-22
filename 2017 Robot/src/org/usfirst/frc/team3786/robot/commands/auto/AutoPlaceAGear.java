package org.usfirst.frc.team3786.robot.commands.auto;

import java.util.List;

import org.usfirst.frc.team3786.robot.autonomous.RobotAction;
import org.usfirst.frc.team3786.robot.subsystems.MatOfPoint;
import org.usfirst.frc.team3786.robot.vision.VisionUtil;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoPlaceAGear extends CommandGroup {

    public AutoPlaceAGear() {
        addSequential(new Command());
        addSequential(new Command());
        
        List<RobotAction> listOfAction = VisionUtil.getActionToGearTarget();
        

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
    	
    	// Commands we want to run
    	// Command 1. Run the vision process to get a list of MatOfPoints, get the ContourReports, 
    	// convert the ContourReports into TargetPositions, and convert the TargetPositions 
    	// into RobotActions
    	
    	// Command 2. For each RobotAction, either turn or drive.
    	    	
    	// Command 3. A ConditionalCommand. If the condition is true, then
    	// run a new instance of AutoPlaceAGear.
    	
    	// 4. If we're close enough, place a gear, otherwise start the process again at step 1.
    }
    
    
}

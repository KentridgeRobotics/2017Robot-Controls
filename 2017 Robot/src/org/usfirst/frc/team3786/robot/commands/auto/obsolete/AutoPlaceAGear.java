package org.usfirst.frc.team3786.robot.commands.auto.obsolete;

import java.util.List;

import org.usfirst.frc.team3786.robot.autonomous.RobotAction;
import org.usfirst.frc.team3786.robot.autonomous.RobotActionGenerator;
import org.usfirst.frc.team3786.robot.commands.auto.NavigateWithOneTarget;
import org.usfirst.frc.team3786.robot.commands.auto.NavigateWithTwoTargets;
import org.usfirst.frc.team3786.robot.vision.TargetPosition;
import org.usfirst.frc.team3786.robot.vision.VisionUtil;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.ConditionalCommand;

/**
 *
 */
public class AutoPlaceAGear extends CommandGroup {
    public AutoPlaceAGear() {        
        
    	
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
    	
    	// Commands we want to runMatOfPoints, get the ContourReports, 
    	// convert the ContourReports into TargetPositions, and convert the TargetPositions
    	// Command 1. Run the vision process to get a list of  
    	// into RobotActions
    	
    	// Command 2. For each RobotAction, either turn or drive.
    	    	
    	// Command 3. A ConditionalCommand. If the condition is true, then
    	// run a new instance of AutoPlaceAGear.
    	
    	// 4. If we're close enough, place a gear, otherwise start the process again at step 1.
        addSequential(new doContinueDrive(new NavigateWithTwoTargets(), new NavigateWithOneTarget(), this));
    }
    
    class doContinueDrive extends ConditionalCommand {

    		AutoPlaceAGear autoPlaceAGear;
			public doContinueDrive(Command onTrue, Command onFalse, AutoPlaceAGear gearCommand) {
				super(onTrue, onFalse);
				// TODO Auto-generated constructor stub
				autoPlaceAGear = gearCommand;
			}

			@Override
			protected boolean condition() {
				// TODO Auto-generated method stub
				List<TargetPosition> tarPos = VisionUtil.getTargetPositionToGearTarget();
				if(tarPos.size() == 2) {
					RobotAction.updatelistOfActions(RobotActionGenerator.extractListOfActionFromTwoTargetPosition(tarPos));
					return true;
				} else if(tarPos.size() == 1) {
					RobotAction.updatelistOfActions(RobotActionGenerator.extractListOfActionsFromSingleTargetPosition(tarPos));
					return false;
				} else {
					autoPlaceAGear.cancel();
					return false;
				}
			}
        	
        }
    
}

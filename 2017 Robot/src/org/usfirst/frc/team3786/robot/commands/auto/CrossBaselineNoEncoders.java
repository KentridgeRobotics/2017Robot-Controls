package org.usfirst.frc.team3786.robot.commands.auto;

import org.usfirst.frc.team3786.robot.vision.VisionUtil;
import org.usfirst.frc.team3786.robot.vision.WhichSide;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrossBaselineNoEncoders extends CommandGroup {

    public CrossBaselineNoEncoders() {
    	
    	//not tested yet, might need to fix speeds and angles
    	
    	WhichSide side = VisionUtil.getPositionOfGearTarget();
    	if(side == WhichSide.LEFT) {
    		addSequential(new GyroTurn(45));
    		addSequential(new AutoDriveNoEncoder(-.5, .5, 3));
    	}
    	else if(side == WhichSide.RIGHT) {
    		addSequential(new GyroTurn(-45));
    		addSequential(new AutoDriveNoEncoder(.5, -.5, 3));    		
    	}
    }
}

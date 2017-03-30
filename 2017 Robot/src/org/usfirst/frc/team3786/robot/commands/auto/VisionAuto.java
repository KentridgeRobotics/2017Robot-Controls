package org.usfirst.frc.team3786.robot.commands.auto;

import org.usfirst.frc.team3786.robot.commands.grabber.ServoClose;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class VisionAuto extends CommandGroup {

    public VisionAuto() {
//    	addSequential(new ServoClose());
    	addSequential(new NoNameGearNavigator());
//    	addSequential(new NoNameGearDropper());
    }
}

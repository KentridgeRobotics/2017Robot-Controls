package org.usfirst.frc.team3786.robot.commands.auto;

import org.usfirst.frc.team3786.robot.commands.test.EncoderPositionTest;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoTest extends CommandGroup {

    public AutoTest() {
    	addSequential(EncoderPositionTest.getInstance());
    }
}

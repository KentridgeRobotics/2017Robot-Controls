package org.usfirst.frc.team3786.robot.commands.auto;

import org.usfirst.frc.team3786.robot.commands.drive.AutonomousDrive;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GyroTurnTest extends CommandGroup {

    public GyroTurnTest() {
    	//addSequential(new AutonomousDrive(360,360));
    	addSequential(new GyroTurn(45));
    	//addSequential(new GyroTurn(-180));
    }
}

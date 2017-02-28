package org.usfirst.frc.team3786.robot.commands.auto;

import org.usfirst.frc.team3786.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3786.robot.subsystems.Rangefinders;

import edu.wpi.first.wpilibj.command.Command;

/**
 *@author Kahl Smitty
 */
public class KyleGearPlaceCloser extends Command {

	private boolean isDone = false;
	private boolean hasDriven = false;
    public KyleGearPlaceCloser() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    	requires(Rangefinders.getInstance());
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }
    
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    Rangefinders Range1 = Rangefinders.getInstance();
   
    
    double RangeMult = (Range1.getRange() * .01);
    double VoltsMult = (Range1.getTestVoltage()/3);
    double Range = Range1.getRange();
    double volts = Range1.getTestVoltage();
    
    
    System.err.println(volts);
    
    if (volts < 1)
    {
    	DriveTrain.getInstance().setSpeed(.2, -.2);
    	hasDriven = true;
    }
//    if (volts >= .6 && volts <= .8 ){
//		 
//		 DriveTrain.getInstance().setSpeed(VoltsMult, -VoltsMult  );
//		 
//    }
//    if(volts < .6){
//    
//    	DriveTrain.getInstance().setSpeed(.25, -.25);
//    }
    if(volts >= 1 && hasDriven == true)
    {
    	DriveTrain.getInstance().setSpeed(0,0);
    isDone = true;
    }
    
    }
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isDone;
    }

    // Called once after isFinished returns true
    protected void end() {
    	isDone = false;
    	hasDriven = false;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

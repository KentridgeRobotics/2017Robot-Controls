package org.usfirst.frc.team3786.robot.commands.drive;

import org.usfirst.frc.team3786.robot.config.RobotConfig;
import org.usfirst.frc.team3786.robot.config.UIConfig;
import org.usfirst.frc.team3786.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveGyro extends Command {
	public static DriveGyro instance;
	
	public static DriveGyro getInstance() {
		if(instance == null)
			instance = new DriveGyro();
		return instance;
	}
	
	private long lastTimeMillis;
	private double maxAngularVelocity = 360; //max angular velocity in deg/s
	private double pTurn = 1;
	double targetHeading;
	
    public DriveGyro() {
    	requires(DriveTrain.getInstance());
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	lastTimeMillis = System.currentTimeMillis();
    	targetHeading = RobotConfig.getInstance().getGyroHeading();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		double currHeading = RobotConfig.getInstance().getGyroHeading();
		double turn = 0;
		double angularVelocity = UIConfig.getInstance().getTurn() * maxAngularVelocity;
		long nowTime = System.currentTimeMillis();
		long deltaTime = nowTime - lastTimeMillis;
		lastTimeMillis = nowTime;
		double deltaHeading = deltaTime * (angularVelocity / 1000.0); 
		targetHeading += deltaHeading;
		double headingError = targetHeading - currHeading;
		
		turn = headingError * pTurn;
		
		DriveTrain.getInstance().setSpeed((UIConfig.getInstance().getVelocity() - turn), -(UIConfig.getInstance().getVelocity() + turn));

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

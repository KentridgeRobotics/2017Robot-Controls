
package org.usfirst.frc.team3786.robot;

import org.usfirst.frc.team3786.robot.commands.auto.AutoTest;
import org.usfirst.frc.team3786.robot.commands.auto.DoNothing;
import org.usfirst.frc.team3786.robot.commands.display.DisplayData;
import org.usfirst.frc.team3786.robot.commands.drive.Drive;
import org.usfirst.frc.team3786.robot.commands.grabber.GearArmTopPosition;
import org.usfirst.frc.team3786.robot.commands.grabber.GearLoaded;
import org.usfirst.frc.team3786.robot.commands.grabber.MoveGearArmManual;
import org.usfirst.frc.team3786.robot.commands.grabber.MoveGearArmPosition;
import org.usfirst.frc.team3786.robot.commands.grabber.ServoMove;
import org.usfirst.frc.team3786.robot.commands.test.ServoClose;
import org.usfirst.frc.team3786.robot.config.Camera;
import org.usfirst.frc.team3786.robot.config.UIConfig;
import org.usfirst.frc.team3786.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3786.robot.subsystems.GearArm;
import org.usfirst.frc.team3786.robot.subsystems.Gyroscope;
import org.usfirst.frc.team3786.robot.subsystems.Rangefinders;
import org.usfirst.frc.team3786.robot.subsystems.GearServo;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public Camera camera;
	public static DisplayData displayData;
	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		//NetworkTable.setClientMode();
		//NetworkTable.setIPAddress("10.37.86.88");
		//Camera.getInstance();
		Drive.getInstance();
		//these two are really just for testing servo stuff right now
		UIConfig.getInstance().getServoMoveButton().whenPressed(ServoMove.getInstance());
		UIConfig.getInstance().getGearLoadedButton().whenPressed(GearLoaded.getInstance());
		UIConfig.getInstance().getPegPositionButton().whenPressed(MoveGearArmPosition.getInstance());
		//UIConfig.getInstance().getGearArmTopButton().whenPressed(GearArmTopPosition.getInstance());
		
		chooser.addDefault("Do Nothing", new DoNothing());
		chooser.addObject("Auto Test", new AutoTest());
		
		//Gyroscope.getInstance();
		//UsbCamera cam = CameraServer.getInstance().startAutomaticCapture();
		//cam.setResolution(1280, 720);
		//chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", chooser);
		displayData = new DisplayData();
		SmartDashboard.putData("Display Data", displayData);
		//SmartDashboard.putBoolean("Connected", !Gyroscope.getInstance().isConnected());
		
		SmartDashboard.putData(Scheduler.getInstance());
		
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		autonomousCommand = chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Left Encoder", DriveTrain.getInstance().getLeftEncoder());
		SmartDashboard.putNumber("Left Voltage", DriveTrain.getInstance().getLeftMotorOutput());
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		DriveTrain.getInstance().setSpeedDrive();
		
		

		
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Potentiometer", GearArm.getInstance().getPosition());
		//Camera.getInstance().pollCamera();
		SmartDashboard.putNumber("Gyro X", Gyroscope.getInstance().getX());
		SmartDashboard.putBoolean("Gear is loaded", GearArm.getInstance().getIsLoaded());
		SmartDashboard.putString("Gear Arm Drive Mode", GearArm.getInstance().getMode());
		SmartDashboard.putNumber("Test Rangefinder Voltage", Rangefinders.getInstance().getTestVoltage());
		SmartDashboard.putNumber("Window Motor Voltage", GearArm.getInstance().getVoltage());
		
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}

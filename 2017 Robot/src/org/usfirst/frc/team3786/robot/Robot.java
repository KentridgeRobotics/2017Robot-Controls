
package org.usfirst.frc.team3786.robot;

import org.opencv.core.Mat;
import org.usfirst.frc.team3786.robot.commands.auto.AutoDriveNoEncoder;
import org.usfirst.frc.team3786.robot.commands.auto.DoNothing;
import org.usfirst.frc.team3786.robot.commands.auto.GyroTurnTest;
import org.usfirst.frc.team3786.robot.commands.auto.RotateWheelsTest;
import org.usfirst.frc.team3786.robot.commands.auto.RotateWheelsTestNoVision;
import org.usfirst.frc.team3786.robot.commands.auto.TurnDegrees;
import org.usfirst.frc.team3786.robot.commands.auto.UpdateTargetDisplay;
import org.usfirst.frc.team3786.robot.commands.auto.obsolete.CrossBaseline;
import org.usfirst.frc.team3786.robot.commands.auto.obsolete.DisplayNextTarget;
import org.usfirst.frc.team3786.robot.commands.auto.obsolete.DistanceByCamera;
import org.usfirst.frc.team3786.robot.commands.auto.obsolete.GoForward;
import org.usfirst.frc.team3786.robot.commands.climber.DeployTrigger;
import org.usfirst.frc.team3786.robot.commands.climber.WinchDeploy;
import org.usfirst.frc.team3786.robot.commands.climber.WinchMove;
import org.usfirst.frc.team3786.robot.commands.display.DisplayTargetSolution;
import org.usfirst.frc.team3786.robot.commands.drive.Drive;
import org.usfirst.frc.team3786.robot.commands.grabber.GearArmBottomPosition;
import org.usfirst.frc.team3786.robot.commands.grabber.GearArmTopPosition;
import org.usfirst.frc.team3786.robot.commands.grabber.GearArmLoadPosition;
import org.usfirst.frc.team3786.robot.commands.grabber.ServoMove;
import org.usfirst.frc.team3786.robot.commands.test.ZeroEncoders;
import org.usfirst.frc.team3786.robot.config.Camera;
import org.usfirst.frc.team3786.robot.config.GyroDrive;
import org.usfirst.frc.team3786.robot.config.RobotConfig;
import org.usfirst.frc.team3786.robot.config.UIConfig;
import org.usfirst.frc.team3786.robot.subsystems.BNO055;
import org.usfirst.frc.team3786.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3786.robot.subsystems.GearArm;
import org.usfirst.frc.team3786.robot.subsystems.Rangefinders;
import org.usfirst.frc.team3786.robot.subsystems.Winch;
import org.usfirst.frc.team3786.robot.vision.FinderOfRange;
import org.usfirst.frc.team3786.robot.vision.NoNameRobotVision;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
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

	//public Camera camera;
//	public static DisplayData displayData;
	//private static BNO055 imu;
	Command autonomousCommand;
	Thread robotVisionThread = null;
	SendableChooser<Command> newChooser;
	//public static UsbCamera usbCamera;
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		Drive.getInstance();
		UIConfig.getInstance().getServoMoveButton().whenPressed(ServoMove.getInstance());
		//Gear arm commands that use potentiometor and/or limits, 
		//Disabled because potentiometer and limits are not working on the robot 
		/*
		UIConfig.getInstance().getPegPositionButton().whenPressed(GearArmLoadPosition.getInstance());
		UIConfig.getInstance().getGearArmTopButton().whenPressed(GearArmTopPosition.getInstance());
		UIConfig.getInstance().getGearArmBottomButton().whenPressed(GearArmBottomPosition.getInstance());
		*/
		UIConfig.getInstance().getWinchDownButton().whileHeld(WinchMove.getDownInstance());
		UIConfig.getInstance().getWinchDownButton().whenReleased(WinchMove.getStopInstance());
		UIConfig.getInstance().getWinchUpButton().whileHeld(WinchMove.getUpInstance());
		UIConfig.getInstance().getWinchUpButton().whenReleased(WinchMove.getStopInstance());
		DeployTrigger.getInstance().whileActive(WinchDeploy.getMoveInstance());
		DeployTrigger.getInstance().whenInactive(WinchDeploy.getStopInstance());
		UIConfig.getInstance().getWinchDeployReverseButton().whileHeld(WinchDeploy.getReverseInstance());
		UIConfig.getInstance().getWinchDeployReverseButton().whenReleased(WinchDeploy.getStopInstance());
		
		JoystickButton testButton = new JoystickButton(UIConfig.getInstance().getLeftStick(), 11);
		testButton.whenPressed(new DisplayTargetSolution());
		RobotConfig.getInstance().initialize();	
		newChooser = new SendableChooser<Command>();
		newChooser.addObject("Update target display", UpdateTargetDisplay.getInstance());
		newChooser.addObject("Do Nothing", new DoNothing());
		//newChooser.addObject("Rotate wheels", new RotateWheelsTest());
		//newChooser.addObject("Gyro Test", new GyroTurnTest());
		//newChooser.addObject("Cross Baseline", new CrossBaseline());
		newChooser.addDefault("Go Forward", new AutoDriveNoEncoder(.5,-.5, 5));
		//newChooser.addObject("Distance by Cam", new DistanceByCamera());
		newChooser.addObject("Gear Auto with vision", new RotateWheelsTest());
		newChooser.addObject("Gear Auto no vision", new RotateWheelsTestNoVision());
		
		//SmartDashboard.putData("Auto mode", autoChooser);
		//UIConfig.getInstance().getTestButton().whenPressed(displayData);
		//SmartDashboard.putBoolean("Connected", !Gyroscope.getInstance().isConnected());
		
		SmartDashboard.putData(Scheduler.getInstance());
		SmartDashboard.putData("Auto Mode", newChooser);
		robotVisionThread = NoNameRobotVision.getRobotVisionThread();
		
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
		//DriveTrain.getInstance().giveValues();
		//DriveTrain.getInstance().setPD();
		//SmartDashboard.putData("Auto mode", newChooser);
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
		autonomousCommand = newChooser.getSelected();
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
		
		SmartDashboard.putNumber("Potentiometer", GearArm.getInstance().getPosition());
		
		SmartDashboard.putNumber("Left Encoder", DriveTrain.getInstance().getLeftEncoder());
		SmartDashboard.putNumber("Left Velocity", DriveTrain.getInstance().getLeftVelocity());
		SmartDashboard.putNumber("Left Voltage", DriveTrain.getInstance().getLeftMotorOutput());
		
		SmartDashboard.putNumber("Right Encoder", DriveTrain.getInstance().getRightEncoder());
		SmartDashboard.putNumber("Right Velocity", DriveTrain.getInstance().getRightVelocity());
		SmartDashboard.putNumber("Right Voltage", DriveTrain.getInstance().getRightMotorOutput());

		SmartDashboard.putString("Drive Train Mode:", DriveTrain.getInstance().getDriveType());
		
		SmartDashboard.putNumber("Target distance", DisplayNextTarget.distance);
		SmartDashboard.putNumber("Target direction", DisplayNextTarget.direction);
		SmartDashboard.putNumber("Target face angle", DisplayNextTarget.faceAngle);
		
		
		//System.err.println("Gyro Heading" + RobotConfig.gyro.getHeading());
		
		//DriveTrain.getInstance().getLoopError();
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
//		DriveTrain.getInstance().setVelocityDrive();
		DriveTrain.getInstance().setCoast();
		GearArm.getInstance().setManualDrive();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Potentiometer", GearArm.getInstance().getPosition());
		SmartDashboard.putNumber("Gyro X", RobotConfig.getInstance().getGyro().getVector()[0]);
		SmartDashboard.putNumber("Gyro Y", RobotConfig.getInstance().getGyro().getVector()[1]);
		SmartDashboard.putNumber("Gyro Z", RobotConfig.getInstance().getGyro().getVector()[2]);
//		
//		SmartDashboard.putBoolean("Gyro Calibration", imu.isCalibrated());
		
		SmartDashboard.putNumber("Left Encoder", DriveTrain.getInstance().getLeftEncoder());
		SmartDashboard.putNumber("Left Velocity", DriveTrain.getInstance().getLeftVelocity());
		SmartDashboard.putNumber("Left Voltage", DriveTrain.getInstance().getLeftMotorOutput());
		SmartDashboard.putNumber("Left Closed Loop Error", DriveTrain.getInstance().getLeftLoopError());
		SmartDashboard.putNumber("Left Stick Out", UIConfig.getInstance().getLeftDrive());
		
		SmartDashboard.putNumber("Right Encoder", DriveTrain.getInstance().getRightEncoder());
		SmartDashboard.putNumber("Right Velocity", DriveTrain.getInstance().getRightVelocity());
		SmartDashboard.putNumber("Right Voltage", DriveTrain.getInstance().getRightMotorOutput());
		
		SmartDashboard.putNumber("Left Current", DriveTrain.getInstance().getLeftCurrent());
		SmartDashboard.putNumber("Right Current", DriveTrain.getInstance().getRightCurrent());

		
		
		SmartDashboard.putString("Drive Train Mode:", DriveTrain.getInstance().getDriveType());
		SmartDashboard.putBoolean("Servo is closed", GearArm.getInstance().getIsClosed());
		SmartDashboard.putString("Gear Arm Drive Mode", GearArm.getInstance().getMode());
		SmartDashboard.putNumber("Test Rangefinder Voltage", Rangefinders.getInstance().getTestVoltage());
		SmartDashboard.putNumber("Test Rangefinder Distance", FinderOfRange.rangeForVoltage(Rangefinders.getInstance().getTestVoltage()));
		SmartDashboard.putNumber("Window Motor Voltage", GearArm.getInstance().getVoltage());
		SmartDashboard.putBoolean("Top Limit", GearArm.getInstance().getTopLimitSwitch());
		SmartDashboard.putBoolean("Bottom Limit", GearArm.getInstance().getBottomLimitSwitch());
		SmartDashboard.putBoolean("Winch fwd limit", Winch.getInstance().getForwardLimitSwitch());
		SmartDashboard.putBoolean("Winch rev limit", Winch.getInstance().getReverseLimitSwitch());
		
		SmartDashboard.putNumber("Left Out", UIConfig.getInstance().getLeftDrive());
		SmartDashboard.putNumber("Right Out", UIConfig.getInstance().getRightDrive());

		
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}

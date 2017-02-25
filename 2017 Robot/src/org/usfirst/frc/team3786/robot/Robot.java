
package org.usfirst.frc.team3786.robot;

import org.opencv.core.Mat;
import org.usfirst.frc.team3786.robot.commands.auto.CrossBaseline;
import org.usfirst.frc.team3786.robot.commands.auto.DisplayNextTarget;
import org.usfirst.frc.team3786.robot.commands.auto.DoNothing;
import org.usfirst.frc.team3786.robot.commands.auto.GoForward;
import org.usfirst.frc.team3786.robot.commands.auto.GyroTurnTest;
import org.usfirst.frc.team3786.robot.commands.auto.RotateWheelsTest;
import org.usfirst.frc.team3786.robot.commands.auto.TurnDegrees;
import org.usfirst.frc.team3786.robot.commands.auto.VelocityAuto;
import org.usfirst.frc.team3786.robot.commands.climber.WinchMove;
import org.usfirst.frc.team3786.robot.commands.display.DisplayData;
import org.usfirst.frc.team3786.robot.commands.drive.Drive;
import org.usfirst.frc.team3786.robot.commands.grabber.GearArmBottomPosition;
import org.usfirst.frc.team3786.robot.commands.grabber.GearArmTopPosition;
import org.usfirst.frc.team3786.robot.commands.grabber.GearArmLoadPosition;
import org.usfirst.frc.team3786.robot.commands.grabber.ServoMove;
import org.usfirst.frc.team3786.robot.commands.test.ZeroEncoders;
import org.usfirst.frc.team3786.robot.config.Camera;
import org.usfirst.frc.team3786.robot.config.RobotConfig;
import org.usfirst.frc.team3786.robot.config.UIConfig;
import org.usfirst.frc.team3786.robot.subsystems.BNO055;
import org.usfirst.frc.team3786.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3786.robot.subsystems.GearArm;
import org.usfirst.frc.team3786.robot.subsystems.Rangefinders;
import org.usfirst.frc.team3786.robot.vision.FinderOfRange;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
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

	public Camera camera;
	public static DisplayData displayData;
	//private static BNO055 imu;
	Command autonomousCommand;
	SendableChooser<Command> newChooser;
	
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		Drive.getInstance();
		UIConfig.getInstance().getServoMoveButton().whenPressed(ServoMove.getInstance());
		UIConfig.getInstance().getPegPositionButton().whenPressed(GearArmLoadPosition.getInstance());
		UIConfig.getInstance().getGearArmTopButton().whenPressed(GearArmTopPosition.getInstance());
		UIConfig.getInstance().getGearArmBottomButton().whenPressed(GearArmBottomPosition.getInstance());
		UIConfig.getInstance().getWinchDownButton().whileHeld(WinchMove.getDownInstance());
		UIConfig.getInstance().getWinchDownButton().whenReleased(WinchMove.getStopInstance());
		UIConfig.getInstance().getWinchUpButton().whileHeld(WinchMove.getUpInstance());
		UIConfig.getInstance().getWinchUpButton().whenReleased(WinchMove.getStopInstance());
		UIConfig.getInstance().getTestButton().whenPressed(ZeroEncoders.getInstance());
		RobotConfig.getInstance().initialize();
		newChooser = new SendableChooser<Command>();
		//newChooser.addDefault("Test", new DoNothing());
		//newChooser.addDefault("Rotate wheels", new RotateWheelsTest());
		//newChooser.addDefault("Gyro Test", new GyroTurnTest());
		newChooser.addDefault("Cross Baseline", new CrossBaseline());
		newChooser.addObject("Go Forward", new GoForward());
		//newChooser.addObject("Velocity Test", new VelocityAuto(8160, 8160));
		newChooser.addObject("Turn 90", new TurnDegrees(90));
		
		//SmartDashboard.putData("Auto mode", autoChooser);
		SmartDashboard.putData("Auto Mode", newChooser);
		displayData = new DisplayData();
		System.err.println("Adding display data");
		SmartDashboard.putData("Display Data", displayData);
		//UIConfig.getInstance().getTestButton().whenPressed(displayData);
		//SmartDashboard.putBoolean("Connected", !Gyroscope.getInstance().isConnected());
		
		SmartDashboard.putData(Scheduler.getInstance());
		//SmartDashboard.putData(DriveTrain.getInstance());
		
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
		SmartDashboard.putData(Scheduler.getInstance());
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
		
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Potentiometer", GearArm.getInstance().getPosition());
		//Camera.getInstance().pollCamera();
		SmartDashboard.putNumber("Gyro X", RobotConfig.getInstance().GetGyro().getVector()[0]);
		SmartDashboard.putNumber("Gyro Y", RobotConfig.getInstance().GetGyro().getVector()[1]);
		SmartDashboard.putNumber("Gyro Z", RobotConfig.getInstance().GetGyro().getVector()[2]);
//		
//		SmartDashboard.putBoolean("Gyro Calibration", imu.isCalibrated());
		
		SmartDashboard.putNumber("Left Encoder", DriveTrain.getInstance().getLeftEncoder());
		SmartDashboard.putNumber("Left Velocity", DriveTrain.getInstance().getLeftVelocity());
		SmartDashboard.putNumber("Left Voltage", DriveTrain.getInstance().getLeftMotorOutput());
		
		SmartDashboard.putNumber("Right Encoder", DriveTrain.getInstance().getRightEncoder());
		SmartDashboard.putNumber("Right Velocity", DriveTrain.getInstance().getRightVelocity());
		SmartDashboard.putNumber("Right Voltage", DriveTrain.getInstance().getRightMotorOutput());

		
		
		SmartDashboard.putString("Drive Train Mode:", DriveTrain.getInstance().getDriveType());
		SmartDashboard.putBoolean("Servo is closed", GearArm.getInstance().getIsClosed());
		SmartDashboard.putString("Gear Arm Drive Mode", GearArm.getInstance().getMode());
		SmartDashboard.putNumber("Test Rangefinder Voltage", Rangefinders.getInstance().getTestVoltage());
		SmartDashboard.putNumber("Test Rangefinder Distance", FinderOfRange.rangeForVoltage(Rangefinders.getInstance().getTestVoltage()));
		SmartDashboard.putNumber("Window Motor Voltage", GearArm.getInstance().getVoltage());
		SmartDashboard.putBoolean("Top Limit", GearArm.getInstance().getTopLimitSwitch());
		SmartDashboard.putBoolean("Bottom Limit", GearArm.getInstance().getBottomLimitSwitch());
		
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}

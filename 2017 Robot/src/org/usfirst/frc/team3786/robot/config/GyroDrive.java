package org.usfirst.frc.team3786.robot.config;

//import org.usfirst.frc.team3786.robot.commands.drive.DriveGyro;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

public class GyroDrive extends UIConfig {
	
	private Joystick leftStick = new Joystick(0);
	private Joystick xbox = new Joystick(2);
	private JoystickButton servoButton = new JoystickButton(xbox, 3);
	private JoystickButton gearArmTopButton = new JoystickButton(xbox, 4);
	private JoystickButton gearArmBottomButton = new JoystickButton(xbox, 1);
	private JoystickButton pegPositionButton = new JoystickButton(xbox, 2);
	private JoystickButton winchUpButton = new JoystickButton(xbox, 8);
	private JoystickButton winchDownButton = new JoystickButton(xbox, 7);
	private JoystickButton winchDeployMoveButton = new JoystickButton(xbox, 5);
	private JoystickButton winchDeployEnableButton = new JoystickButton(xbox, 6);
	private JoystickButton winchDeployReverseButton = new JoystickButton(leftStick, 7);
	
	private double lastTimeMillis;
	private double maxAngularVelocity = 360; //max angular velocity in deg/s
	private double Pturn = 1;
	
//	public static void initTime() {
//		lastTimeMillis = System.currentTimeMillis();
//	}
	
	@Override
	public double getVelocity() {
		return -leftStick.getY();
	}
	@Override
	public double getTurn() {
//		double currHeading = RobotConfig.getInstance().getGyroHeading();
//		double turn = 0;
//		double angularVelocity = -testX()/*leftStick.getX()*/ * maxAngularVelocity;
//		double deltaTime = System.currentTimeMillis() - lastTimeMillis;
//		lastTimeMillis = System.currentTimeMillis();
//		double deltaHeading = deltaTime * (angularVelocity / 1000); 
//		double targetHeading = currHeading + deltaHeading;
//		double headingError = targetHeading - currHeading;
//		
//		turn = headingError * Pturn;
//		
//		
//		
//		return turn;'
		return leftStick.getX();
	}
	
	private double testX() {
		if(leftStick.getRawButton(1))
			return (30.0/360.0);
		else
			return 0.0;
	}
	private double testY() {
		return -leftStick.getRawAxis(3);
	}

	@Override
	public Joystick getLeftStick() {
		return leftStick;
	}

	@Override
	public Joystick getRightStick() {
		return leftStick;
	}

	@Override
	public Joystick getXbox() {
		return xbox;
	}

	@Override
	public double getLeftDrive() {
		System.err.println("~~~~~Left Velocity: " + (getVelocity() - getTurn()) +"~~~~~");
		System.err.println("Left Turn: " + -getTurn());
		return (getVelocity() - getTurn()) * .5;
	}

	@Override
	public double getRightDrive() {
		System.err.println("~~~~~Right Velocity: " + (getVelocity() + getTurn())+"~~~~~");
		System.err.println("Right Turn: " + getTurn());
		return -(getVelocity() + getTurn()) * .5;
	}

	@Override
	public JoystickButton getServoMoveButton() {
		return servoButton;
	}

	@Override
	public JoystickButton getPegPositionButton() {
		return pegPositionButton;
	}

	@Override
	public JoystickButton getGearArmTopButton() {
		return gearArmTopButton;
	}

	@Override
	public JoystickButton getGearArmBottomButton() {
		return gearArmBottomButton;
	}

	@Override
	public JoystickButton getWinchUpButton() {
		return winchUpButton;
	}

	@Override
	public JoystickButton getWinchDownButton() {
		return winchDownButton;
	}

	@Override
	public JoystickButton getWinchDeployMoveButton() {
		return winchDeployMoveButton;
	}

	@Override
	public JoystickButton getWinchDeployEnableButton() {
		return winchDeployEnableButton;
	}

	@Override
	public JoystickButton getWinchDeployReverseButton() {
		return winchDeployReverseButton;
	}
	@Override
	public Command getDefaultDrive() {
		//return DriveGyro.getInstance();
		return null;
	}

}

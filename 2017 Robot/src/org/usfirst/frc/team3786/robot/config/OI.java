package org.usfirst.frc.team3786.robot.config;

import org.usfirst.frc.team3786.robot.commands.ServoClose;
import org.usfirst.frc.team3786.robot.commands.ServoOpen;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	JoystickButton openButton = new JoystickButton(ControlConfig.getInstance().getLeftStick(), 1);
	Button closeButton = new JoystickButton(ControlConfig.getInstance().getLeftStick(), 2);
//	Button motorMove = new JoystickButton(Robot.stick, 5);
//	Button motorStop = new JoystickButton(Robot.stick, 3);
	
	public OI() {
		//should remove these once actual competition stuff rolls around
		openButton.whenPressed(ServoOpen.getInstance());
		closeButton.whenPressed(ServoClose.getInstance());
//		motorMove.whenPressed(MotorMove.getInstance());
//		motorStop.whenPressed(MotorStop.getInstance());
		
	}
	public static double getJoystickY() {
		//return stick.getRawAxis(1);
		return 0;
	}

	
}

package org.usfirst.frc.team3786.robot.config;

import edu.wpi.first.wpilibj.Joystick;

public class TankDrive extends ControlConfig {
	private Joystick leftStick = new Joystick(0);
	@Override
	public Joystick getLeftStick() {
		return leftStick;
	}

}

package org.usfirst.frc.team3786.robot.commands.climber;

import org.usfirst.frc.team3786.robot.config.UIConfig;

import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 *
 */
public class DeployTrigger extends Trigger {
	
	private static DeployTrigger instance;
	
	public static DeployTrigger getInstance() {
		if(instance == null) 
			instance = new DeployTrigger();
		return instance;
	}
	
    public boolean get() {
        return UIConfig.getInstance().getWinchDeployMoveButton().get() && UIConfig.getInstance().getWinchDeployEnableButton().get();
    }
}

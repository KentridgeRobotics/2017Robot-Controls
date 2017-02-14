package org.usfirst.frc.team3786.robot.autonomous;

import java.util.ArrayList;
import java.util.List;

import org.usfirst.frc.team3786.robot.autonomous.action.RobotAction;
import org.usfirst.frc.team3786.robot.utility.TargetPosition;

public class DirectionFinder {
	public static List<RobotAction> extractListOfAction(TargetPosition pos) {
		List<RobotAction> listOfActions = new ArrayList<RobotAction>();
		if(pos.getAngleToTargetInDegrees() > -1.0 && pos.getAngleToTargetInDegrees() < 1.0){
			listOfActions.add(new RobotAction(pos.getAngleToTargetInDegrees(), 0));
		} 	
		if(pos.getAngleOfTargetInDegrees() > -1.0 && pos.getAngleOfTargetInDegrees() < 1.0){
				listOfActions.add(new RobotAction(pos.getAngleOfTargetInDegrees(), 0));
				listOfActions.add(new RobotAction(0, 
						(pos.getDistanceToTargetInInches() / 2) / Math.cos(Math.toRadians(pos.getAngleOfTargetInDegrees()))
						));
				listOfActions.add(new RobotAction(
						-(90 - pos.getAngleOfTargetInDegrees()) * 2
						, 0));
				listOfActions.add(new RobotAction(0, 
						(pos.getDistanceToTargetInInches() / 2) / Math.cos(Math.toRadians(pos.getAngleOfTargetInDegrees()))
						));
			} else {
				listOfActions.add(new RobotAction(0, pos.getDistanceToTargetInInches()));
		}
		
		return listOfActions;
	}
	
	public static void displayListOfActions(List<RobotAction> roboaction) {
		System.out.println("List of Actions: ");
		for(int i = 0; i < roboaction.size(); i++) {
			System.out.println((i + 1) +". " + roboaction.get(i));
		}
	}
	
	/*public static void main(String[] args) {
		System.err.println("hello");
		TargetPosition[] pos1 = {
				new TargetPosition(20, 10, 45),
				new TargetPosition(0, 10, 30),
				new TargetPosition(0, 10, -30)
		};
		for(TargetPosition p: pos1){
			System.out.println(" ");
			System.out.println("Action: ");
			displayListOfActions(extractListOfAction(p));
		}
	}*/
}
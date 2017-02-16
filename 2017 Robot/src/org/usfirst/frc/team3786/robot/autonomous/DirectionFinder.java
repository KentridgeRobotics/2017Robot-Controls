package org.usfirst.frc.team3786.robot.autonomous;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.usfirst.frc.team3786.robot.autonomous.action.RobotAction;
import org.usfirst.frc.team3786.robot.utility.ContourReport;
import org.usfirst.frc.team3786.robot.utility.TargetPosition;
import org.usfirst.frc.team3786.robot.utility.VisionUtil;

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
	
	/*
	public static void main(String[] args) {
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
		
		List<ContourReport> list1 = new ArrayList()
				
				new ContourReport(206, 112, 36, 93), 
				new ContourReport(361, 109, 38, 94)
				}
		
		List<ContourReport>[] pos2 = {
				list1
		};
	}
	
	public static List<TargetPosition> extractTargetPosition(List<ContourReport> contourReportsList) {
    	if(contourReportsList.size() == 2) {	
        	List<TargetPosition> tempList = new ArrayList<TargetPosition>();
    		for(ContourReport report : contourReportsList) {
    			double tempDouble = 0.0; 
    			if(contourReportsList.get(0).getArea() > contourReportsList.get(1).getArea()) {				//check Area of Each Rectangle
    				if(contourReportsList.get(0).getCenterX() > contourReportsList.get(1).getCenterX()) {
    					tempDouble = -VisionUtil.angleOfTarget(report.getWidth(), report.getHeight());
    				} else {
    					tempDouble = VisionUtil.angleOfTarget(report.getWidth(), report.getHeight());
    				}
				}else {
					if(contourReportsList.get(0).getCenterX() > contourReportsList.get(1).getCenterX()) {
    					tempDouble = VisionUtil.angleOfTarget(report.getWidth(), report.getHeight());
    				} else {
    					tempDouble = -VisionUtil.angleOfTarget(report.getWidth(), report.getHeight());
    				}
				}
    			tempList.add(new TargetPosition(
    					VisionUtil.angleToEstimate(report.getCenterX()), 
    					VisionUtil.distanceEstimate(report.getHeight()),
    					tempDouble
    					));
    		}
    		return tempList;
    	} else {
    		return Collections.emptyList();
    	}
    }*/
}
		
		
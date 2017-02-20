package org.usfirst.frc.team3786.robot.autonomous;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;

import org.usfirst.frc.team3786.robot.vision.ContourReport;
import org.usfirst.frc.team3786.robot.vision.GripPipeline;
import org.usfirst.frc.team3786.robot.vision.TargetPosition;
import org.usfirst.frc.team3786.robot.vision.VisionUtil;

public class RobotActionGenerator {
	
	public static List<RobotAction> extractActions(List<TargetPosition> listOfPos)
	{
		if (listOfPos.size() == 2) {
			return extractListOfActionFromTwoTargetPosition(listOfPos);
		}
		else if (listOfPos.size() == 1) {
			return extractListOfActionsFromSingleTargetPosition(listOfPos);
		}
		else
		{
			return Collections.emptyList();
		}
	}
	
	//Requires Two TargetPositions
	//Creates A List Of actions based on the Target Position
	//Most Likely Only used in the case where the Robot is Directly facing Target. 
	public static List<RobotAction> extractListOfActionFromTwoTargetPosition(List<TargetPosition> listOfPos) {
		if(listOfPos.size() == 2) {
			List<RobotAction> listOfActions = new ArrayList<RobotAction>();
			TargetPosition pos1 = listOfPos.get(0);
			TargetPosition pos2 = listOfPos.get(1);
			//Display Outliers
			if(Math.abs(pos1.getTargetFaceAngleDegrees() - pos2.getTargetFaceAngleDegrees()) > 20.0) {
				System.err.println("Exceeds Error Margin for Angle Of Target face...");
				if(Double.isNaN(pos1.getTargetFaceAngleDegrees() - pos2.getTargetFaceAngleDegrees())) {
					return Collections.emptyList();
				}
			}
			if(Math.abs(pos1.getTargetDirectionDegrees() - pos2.getTargetDirectionDegrees()) > 45.0) {
				System.err.println("Exceeds Error Margin for direction To Target...");
				if(Double.isNaN(pos1.getTargetDirectionDegrees() - pos2.getTargetDirectionDegrees())) {
					return Collections.emptyList();
				}
			}
			if(Math.abs(pos1.getDistanceToTargetInInches() - pos2.getDistanceToTargetInInches()) > 18.0) {
				System.err.println("Exceeds Error Margin for Distance To Target...");
				
			}
			
			TargetPosition targetMidpoint = new TargetPosition(
					(pos1.getTargetDirectionDegrees() + pos2.getTargetDirectionDegrees()) / 2,
					(pos1.getDistanceToTargetInInches() + pos2.getDistanceToTargetInInches()) / 2,
					(pos1.getTargetFaceAngleDegrees() + pos2.getTargetFaceAngleDegrees()) / 2);
			
			// If we're not pointing straight at the target, turn towards it. This orients the robot.
			if(targetMidpoint.getTargetDirectionDegrees() < -1.0 || targetMidpoint.getTargetDirectionDegrees() > 1.0){
				listOfActions.add(RobotAction.createTurn(targetMidpoint.getTargetDirectionDegrees()));
			} 	
			
			if(targetMidpoint.getTargetFaceAngleDegrees() < -1.0 || targetMidpoint.getTargetFaceAngleDegrees() > 1.0){
				listOfActions.add(RobotAction.createTurn(targetMidpoint.getTargetFaceAngleDegrees()));
				listOfActions.add(RobotAction.createDrive((targetMidpoint.getDistanceToTargetInInches() / 2) / Math.cos(Math.toRadians(targetMidpoint.getTargetFaceAngleDegrees()))));
				listOfActions.add(RobotAction.createTurn(-(90 - targetMidpoint.getTargetFaceAngleDegrees()) * 2));
				listOfActions.add(RobotAction.createDrive((targetMidpoint.getDistanceToTargetInInches() / 2) / Math.cos(Math.toRadians(targetMidpoint.getTargetFaceAngleDegrees()))));
			} else {
				listOfActions.add(RobotAction.createDrive(targetMidpoint.getDistanceToTargetInInches()));
			}
			return listOfActions;
		} else {
			System.err.println("Error in extractListOfAction: Size of list does not equal 2");
    		System.err.println("Size of List: " + listOfPos.size());
    		return Collections.emptyList();
		}
	}
	
	
	
	//Extract List Of Actions based on one Target Position.
	//
	public static List<RobotAction> extractListOfActionsFromSingleTargetPosition(List<TargetPosition> targetPosition) {
		if(targetPosition.size() == 1) {
			List<RobotAction> listOfActions = new ArrayList<RobotAction>();
			TargetPosition tempPos = targetPosition.get(0);
			
			if(tempPos.getTargetDirectionDegrees() < -1.0 || tempPos.getTargetDirectionDegrees() > 1.0){
				listOfActions.add(RobotAction.createTurn(tempPos.getTargetDirectionDegrees()));
			} 	
			if(tempPos.getTargetFaceAngleDegrees() < -1.0 || tempPos.getTargetFaceAngleDegrees() > 1.0){
				listOfActions.add(RobotAction.createTurn(tempPos.getTargetFaceAngleDegrees()));
				listOfActions.add(RobotAction.createDrive((tempPos.getDistanceToTargetInInches() / 2) / Math.cos(Math.toRadians(tempPos.getTargetFaceAngleDegrees()))));
				listOfActions.add(RobotAction.createTurn(-(90 - tempPos.getTargetFaceAngleDegrees()) * 2));
			} else {
				listOfActions.add(RobotAction.createDrive(tempPos.getDistanceToTargetInInches()));
			}
			return listOfActions;
		} else {
			System.err.println("Error in extractListOfAction: Size of list does not equal 2");
    		System.err.println("Size of List: " + targetPosition.size());
    		return Collections.emptyList();
		}
	}
	
	
	//Display List Of Actions.
	public static void displayListOfActions(List<RobotAction> roboaction) {
		System.out.println("List of Actions: ");
		for(int i = 0; i < roboaction.size(); i++) {
			System.out.println((i + 1) +". " + roboaction.get(i));
		}  
	}
	
	
	
	
	

	
	//TEST MAIN METHOD -------------------------------------------------------------------------------------
	/*
	public static void main(String[] args) {
		System.err.println("hello");
				
		List<ContourReport> list1 = new ArrayList();
		list1.add(new ContourReport(206, 112, 36, 93)); 
		list1.add(new ContourReport(361, 109, 38, 94));
				
		List<ContourReport> list2 = new ArrayList();
		list2.add(new ContourReport(363, 119, 26, 37));
		list2.add(new ContourReport(247, 130, 26, 71));
		
		List<ContourReport> list3 = new ArrayList();
		list3.add(new ContourReport(234, 75, 36, 95));
		list3.add(new ContourReport(387, 75, 38, 100));
		
		List<List<ContourReport>> listOfLists = new ArrayList();
		listOfLists.add(list1);
		listOfLists.add(list2);
		listOfLists.add(list3);
		
		System.out.println(" ");
		for(int i = 0; i < listOfLists.size(); i++) {
			for(TargetPosition TP : DirectionFinder.extractTargetPosition(listOfLists.get(i))) {
				System.out.println(TP);
				System.out.println(" ");
			}
			displayListOfActions(extractListOfAction(extractTargetPosition(listOfLists.get(i))));
			System.out.println(" ");
		}
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
    }
    */
}
		
		
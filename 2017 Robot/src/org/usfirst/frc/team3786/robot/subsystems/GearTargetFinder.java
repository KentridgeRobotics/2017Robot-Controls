package org.usfirst.frc.team3786.robot.subsystems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
//import java.util.concurrent.Callable;
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.Future;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team3786.robot.utility.*;
import org.usfirst.frc.team3786.robot.vision.ContourReport;
import org.usfirst.frc.team3786.robot.vision.GripPipeline;
import org.usfirst.frc.team3786.robot.vision.TargetPosition;
import org.usfirst.frc.team3786.robot.vision.VisionUtil;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 Take Inputs from Usb Camera and input into GripPipeline
 Find Distance and Angle of Targets in list. 
 */
public class GearTargetFinder extends Subsystem {
	//Temporary Resolution
	private int IMG_WIDTH = 640;
	private int IMG_HEIGHT = 480;
	
	//Fixed ThreadPool for Running Image through Pipeline
	UsbCamera camera;
	CvSink cvSink;
	CvSource cvSource;
	//ExecutorService executorService = Executors.newFixedThreadPool(1);
	
	//Default Command
    @Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
    
    //Constructor
    public GearTargetFinder() {
    	camera = CameraServer.getInstance().startAutomaticCapture();
        camera.setResolution(IMG_WIDTH, IMG_HEIGHT);
    	cvSink = CameraServer.getInstance().getVideo();
    	cvSource = CameraServer.getInstance().putVideo("MyOutput", IMG_WIDTH, IMG_HEIGHT);
    	
    }
    
    //Callable method to run Vision Thread on seperate Thread
    //return ArrayList of MatOfPoint
    public ArrayList<MatOfPoint> runVisionThread() {
    	System.err.println("Callable called");
    	GripPipeline grip = new GripPipeline();
    	
        Mat source = new Mat();
        cvSink.grabFrame(source);
      	        
        grip.process(source);
		ArrayList<MatOfPoint> convexHulls = grip.convexHullsOutput();
		
		return convexHulls;    	
    }
    
    /*public Callable<ArrayList<MatOfPoint>> callVisionThread() {	
    	return new Callable<ArrayList<MatOfPoint>>() {
			@Override
			public ArrayList<MatOfPoint> call() throws Exception {
				// TODO Auto-generated method stub
				System.err.println("Callable called");
		    	GripPipeline grip = new GripPipeline();
		    	
		        Mat source = new Mat();
		        cvSink.grabFrame(source);
		      	        
		        grip.process(source);
				ArrayList<MatOfPoint> convexHulls = grip.convexHullsOutput();
				
				return convexHulls;
			}
    	};
    	
    }
    
    
    
    //Run image through the Grip Pipeline once. 
    //
    public List<ContourReport> executeVisionCamera() {
    	System.err.println("Execute Vision Camera Called ");
    	try {
    		Future<ArrayList<MatOfPoint>> futureResult = executorService.submit(callVisionThread());
    		ArrayList<MatOfPoint> result = futureResult.get();
    		return extractContourReports(result);
    	} catch(Exception exc) {
    		System.err.println("Exception: " + exc);
    		return null;
    	}
    
    }
    */
        
    //Return List of ContourReports
    public List<ContourReport> extractContourReports(ArrayList<MatOfPoint> contourMap) {
    	List<ContourReport> contourReports = new ArrayList<ContourReport>();
    	System.err.println("Size of Contour Map: " + contourMap.size());
    	for(MatOfPoint matOfPoint : contourMap) {
    		System.err.println("Mat Of Point Found");
    		Rect r = Imgproc.boundingRect(matOfPoint);
    		contourReports.add(new ContourReport(
    			r.x + (r.width / 2),
    			r.y + (r.height / 2),
    			r.width,
    			r.height
    		));
    	}
    	return contourReports;
    }
    
    //Enum class 
    public enum Direction {
    	LEFT 	(-1),
    	RIGHT 	(1),
    	MIDDLE 	(0);
    	
    	private final int value;
    	private Direction(int val) {
    		this.value = val;
    	}
    	
    	public int getValue() {
    		return this.value;
    	}
    }
    
    //Return List of ContourReports Based On Object
    //Returns one or two ContourReport(s)
    //Finish Later
       
    public List<ContourReport> findObjectiveContourReport(List<ContourReport> contourReport, Direction direction) {
    	List<ContourReport> contourReports = new ArrayList(contourReport);
    	ContourReport temp;
    	for (int i = 0; i < contourReports.size(); i++){
	    	for (int j = 0; j < contourReports.size(); j++){
	            if (contourReports.get(i).getCenterX() < contourReports.get(j).getCenterX())
	            {
	                temp = contourReports.get(i);
	                contourReports.set(i, contourReports.get(j));
	                contourReports.set(j,  temp);
	            }
	        }
	    }
    	switch(contourReport.size()) {
    		case 4:
    			
    		case 3: 
    			
    		case 2:
    		
    		default:
    			if(contourReport.size() > 4){
    				
    			}else if(contourReport.size() < 1) {
    				
    			} else {
    				System.err.println("T");
    			}
    			return Collections.emptyList();
    			//break;
    	}
    	
    	
    	//return contourReports;
    }
    
    
    //Return List of TargetPotitions Based On Contour Reports
    public List<TargetPosition> extractListOfTargetPosition(List<ContourReport> contourReportsList) {
    	List<TargetPosition> tempList = new ArrayList<TargetPosition>();
    		for(ContourReport report : contourReportsList) {
    			tempList.add(new TargetPosition(
    					VisionUtil.angleToEstimate(report.getCenterX()), 
    					VisionUtil.distanceEstimate(report.getHeight()),
    					VisionUtil.angleOfTarget(report.getWidth(), report.getHeight())
    					));
    		}
    		return tempList;
    }
    
    
    //Display List of ContourReports onto the Console.
    public void displayContourReports(List<ContourReport> contourReportsList) {
    	System.err.println("Size of ContourReport: " + contourReportsList.size());
    	for(ContourReport report: contourReportsList) {
    		System.out.println(report);
    	}
    }
    
    public void displayTargetPositions(List<TargetPosition> targetPositionList) {
    	System.err.println("Target Positions: ");
    	for(TargetPosition point: targetPositionList) {
    		System.out.println(point);
    	}
    }
   
}


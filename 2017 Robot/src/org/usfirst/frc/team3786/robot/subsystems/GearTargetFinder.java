package org.usfirst.frc.team3786.robot.subsystems;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team3786.robot.utility.*;
import org.usfirst.frc.team3786.robot.vision.GripPipeline;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.vision.VisionRunner;

/**
 Take Inputs from Usb Camera and input into GripPipeline
 Find Distance and Angle of Targets in list. 
 */
public class GearTargetFinder extends Subsystem {
	//Temporary Resolution
	private int IMG_WIDTH = 640;
	private int IMG_HEIGHT = 480;
		
	//Fixed ThreadPool for Running Image through Pipeline
	ExecutorService executorService = Executors.newFixedThreadPool(1);
	
	//Default Command
    @Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
    
    //Constructor
    public GearTargetFinder() {
    	
    }
    
    //Callable method to run Vision Thread on seperate Thread
    public Callable<ArrayList<MatOfPoint>> runVisionThread() {	
    	return new Callable<ArrayList<MatOfPoint>>() {
			@Override
			public ArrayList<MatOfPoint> call() throws Exception {
				// TODO Auto-generated method stub
				UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		    	camera.setResolution(IMG_WIDTH, IMG_HEIGHT);
		    	GripPipeline grip = new GripPipeline();
		    	
		    	CvSink cvSink = CameraServer.getInstance().getVideo();
		        
		        Mat source = new Mat();
		        cvSink.grabFrame(source);
		        grip.process(source);
				ArrayList<MatOfPoint> output = grip.convexHullsOutput();
				return output;
			}
    	};
    	
    }
    
    
    
    //Run image through the Grip Pipeline once. 
    //
    public List<TargetPosition> executeVisionCamera() throws Exception{
    	Future<ArrayList<MatOfPoint>> futureResult = executorService.submit(runVisionThread());
    	ArrayList<MatOfPoint> result = futureResult.get();
    	return extractTargetPosition(extractContourReports(result));
    
    }
    
        
    //Return List of ContourReports
    public List<ContourReport> extractContourReports(ArrayList<MatOfPoint> contourMap) {
    	List<ContourReport> contourReports = new ArrayList<ContourReport>();
    	for(MatOfPoint matOfPoint : contourMap) {
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
    
    
    //Return List of TargetPotitions
    public List<TargetPosition> extractTargetPosition(List<ContourReport> contourReportsList) {
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
    	for(ContourReport report: contourReportsList) {
    		System.out.println(report);
    	}
    }
   
}


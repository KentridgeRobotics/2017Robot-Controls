package org.usfirst.frc.team3786.robot.subsystems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team3786.robot.Robot;
import org.usfirst.frc.team3786.robot.config.RobotConfig;
import org.usfirst.frc.team3786.robot.vision.ContourReport;
import org.usfirst.frc.team3786.robot.vision.GripPipeline;
import org.usfirst.frc.team3786.robot.vision.TargetPosition;
import org.usfirst.frc.team3786.robot.vision.VisionUtil;
import org.usfirst.frc.team3786.robot.vision.WhichDirection;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 Take Inputs from Usb Camera and input into GripPipeline
 Find Distance and Angle of Targets in list. 
 */
public class GearTargetFinder {
	//Temporary Resolution
	private static GearTargetFinder instance;
	private Mat workingMat = new Mat();

	//Fixed ThreadPool for Running Image through Pipeline
	//ExecutorService executorService = Executors.newFixedThreadPool(1);
	public static GearTargetFinder getInstance(){
		if(instance == null)
		{
			instance = new GearTargetFinder();
		}
		return instance;
	}

    //Constructor
    private GearTargetFinder() {    	
    }
    
    
    public boolean isRobotInPosition(CvSink cvSink, GripPipeline grip) {
    	Mat source = workingMat;
        long result = cvSink.grabFrame(source);
        if (result == 0)
        {
        	// There was an error!
        	System.err.println("ERROR: acquireVisionInput failed: "+cvSink.getError());
        	return false;
        }
        grip.process(source);
		List<TargetPosition> targetPositions = extractListOfTargetPosition(findObjectiveContourReport(extractContourReports(grip.filterContoursOutput()), WhichDirection.UNKNOWN));
		// Release the Mat
		//source.release();
		if(targetPositions.size() == 2) {
			return true;
		} else {
			return false;
		}
    }
        
    //Return List of ContourReports
    public List<ContourReport> extractContourReports(List<MatOfPoint> contourMap) {
    	List<ContourReport> contourReports = new ArrayList<ContourReport>();
    	try {
    		for(MatOfPoint matOfPoint : contourMap) {
    			Rect r = Imgproc.boundingRect(matOfPoint);
    			// We only want contours that look like vision target rectangles.
    			if (r.height > 1.7 * r.width) 
    			{
    				contourReports.add(new ContourReport(
    						r.x + (r.width / 2),
    						r.y + (r.height / 2),
    						r.width,
    						r.height
    						));
    			}
    		}
    	}
    	
    	catch (Exception ex) {
    		System.err.println("ERROR IN EXTRACT CONTOUR REPORTS LIST "+ex);
    		ex.printStackTrace(System.err);
    	}
    	return contourReports;
    }
    

    
    //Return List of ContourReports Based On Object
    //Returns one or two ContourReport(s)
    //Filters List of Contours into one or two
       
    public List<ContourReport> findObjectiveContourReport(List<ContourReport> contourReport, WhichDirection direction) {
    	List<ContourReport> contourReports = new ArrayList<ContourReport>(contourReport);
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
    			if(Math.abs(contourReports.get(0).getCenterX() - contourReports.get(1).getCenterX()) < contourReports.get(0).getHeight() * 2.5) {
    				contourReports.remove(3);
    				contourReports.remove(2);
    			} else if(Math.abs(contourReports.get(2).getCenterX() - contourReports.get(3).getCenterX()) < contourReports.get(2).getHeight() * 2.5) {
    				contourReports.remove(0);
    				contourReports.remove(0);
    			} else if(Math.abs(contourReports.get(1).getCenterX() - contourReports.get(2).getCenterX()) < contourReports.get(1).getHeight() * 2.5) {
    				contourReports.remove(3);
    				contourReports.remove(0);
    			} else {
    				System.err.println("Doesn't have an ideal target");
    				contourReports.clear();
    			}
    			break;
    		case 3: 
    			if(Math.abs(contourReports.get(0).getCenterX() - contourReports.get(1).getCenterX()) < contourReports.get(0).getHeight() * 2.5) {
    				contourReports.remove(2); 
    			}else if (Math.abs(contourReports.get(1).getCenterX() - contourReports.get(2).getCenterX()) < contourReports.get(1).getHeight() * 2.5) {
    				contourReports.remove(0);
    			} else {
    				System.err.println("Doesn't have an ideal target");
    				contourReports.clear();
    			}
    			break;
    		case 2:
    			if(Math.abs(contourReports.get(0).getCenterX() - contourReports.get(1).getCenterX()) < contourReports.get(0).getHeight() * 2.5) {
    				return contourReports;
    			} else {
    				switch(direction) {
    					case LEFT:
    						contourReports.remove(1);
    						break;
    					case RIGHT:
    						contourReports.remove(0);
    						break;
    					case MIDDLE_LEFT:
    						contourReports.remove(0);
    						break;
    					case MIDDLE_RIGHT:
    						contourReports.remove(1);
    						break;
    					case UNKNOWN:
    						if(RobotConfig.IMG_WIDTH - contourReports.get(0).getCenterX() < RobotConfig.IMG_WIDTH - contourReports.get(0).getCenterX()) {
    							contourReport.remove(0);
    						} else {
    							contourReport.remove(1);
    						}
    						break;
    					default:
    						break;
    				}
    			}
    			break;
    		default:
    			if(contourReport.size() > 4){
    				System.err.println("Too many Contours");
    			}else if(contourReport.size() < 1) {
    				System.err.println("No Contour Reports");
    			} else {
    				System.err.println("Unknown Error");
    			}
    			return Collections.emptyList();
    	}
    	
    	
    	return contourReports;
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
    		System.err.println(report);
    	}
    }
    
    public void displayTargetPositions(List<TargetPosition> targetPositionList) {
    	System.err.println("Target Positions: ");
    	for(TargetPosition point: targetPositionList) {
    		System.err.println(point);
    	}
    }
   
}


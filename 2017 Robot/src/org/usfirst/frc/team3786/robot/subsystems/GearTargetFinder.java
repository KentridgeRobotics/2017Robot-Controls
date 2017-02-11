package org.usfirst.frc.team3786.robot.subsystems;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team3786.robot.utility.*;
import org.usfirst.frc.team3786.robot.vision.GripPipeline;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.vision.VisionRunner;

/**
 Take Inputs from Usb Camera and input into GripPipeline
 Find Distance and Angle of Targets in list. 
 */
public class GearTargetFinder extends Subsystem {
	private int IMG_WIDTH = 640;
	private int IMG_HEIGHT = 480;
	//Temporary Resolution
	
	//Camera
	private final UsbCamera camera;
	public VisionRunner<GripPipeline> visionRunner;
	
	//Default Command
    @Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
    
    //Constructor
    public GearTargetFinder(UsbCamera cam) {
    	camera = cam;
    	visionRunner = new VisionRunner<GripPipeline>(camera, new GripPipeline(), new VisionListener());

    }
    
    // here. Call these from Commands.
    public void runVisionRunnerOnce() {
    	visionRunner.runOnce();
    }
    
    //Return List of ContourReports
    //Finish Later
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
    
    //Output of the GripPipeline
    //Called after VisionRunner runs once
    private class VisionListener implements VisionRunner.Listener<GripPipeline> {
		@Override
		public void copyPipelineOutputs(GripPipeline pipeline) {
			// TODO Auto-generated method stub
			pipeline.findContoursOutput();
		}
    }

}


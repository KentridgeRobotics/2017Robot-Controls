package org.usfirst.frc.team3786.robot.vision;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team3786.robot.config.RobotConfig;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;

public class NoNameRobotVision implements Runnable {

	public synchronized static void startRobotVisionThread() {
		if (visionThread != null)
			return;

		visionThread = new Thread(getInstance());
		visionThread.setDaemon(true);
		visionThread.start();
	}

	private static NoNameRobotVision instance;
	private static Thread visionThread = null;
	private final AtomicReference<AngleAndDistance> myAngleAndDistance = new AtomicReference<AngleAndDistance>();
	private NoNameRobotVision() {

	}
	public static NoNameRobotVision getInstance()
	{
		if (instance == null) {
			instance = new NoNameRobotVision();
		}
		return instance;
	}
	
	public AngleAndDistance getTargetAngleAndDistance() {
		return myAngleAndDistance.get(); // I guess we're OK if the position is null.
	}
	
	private static final double THICKNESS = 12.0;
	private static final Scalar COLOR = new Scalar(124, 252, 0);
	@Override
	public void run() {
		// Get the UsbCamera from CameraServer
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		// Set the resolution
		camera.setResolution(RobotConfig.IMG_WIDTH, RobotConfig.IMG_HEIGHT);

		// Get a CvSink. This will capture Mats from the camera
		CvSink cvSink = CameraServer.getInstance().getVideo();
		// Setup a CvSource. This will send images back to the Dashboard
		CvSource outputStream = CameraServer.getInstance().putVideo("Rectangle", RobotConfig.IMG_WIDTH, RobotConfig.IMG_HEIGHT);

		// Mats are very memory expensive. Lets reuse this Mat.
		Mat mat = new Mat();
		GripPipeline gripPipeline = new GripPipeline();
		// This cannot be 'true'. The program will never exit if it is. This
		// lets the robot stop this thread when restarting robot code or
		// deploying.
		while (!Thread.interrupted()) {
			// Tell the CvSink to grab a frame from the camera and put it
			// in the source mat. If there is an error notify the output.
			if (cvSink.grabFrame(mat) == 0) {
				// Send the output the error.
				outputStream.notifyError(cvSink.getError());
				// skip the rest of the current iteration
				continue;
			}
			// Let's find the target.
			gripPipeline.process(mat);
			AngleAndDistance angleAndDistance = findGearPegLocationBasedOnVisionTargets(gripPipeline.filterContoursOutput());
	    	myAngleAndDistance.set(angleAndDistance);
			// Do image processing here, if you want.
			if (angleAndDistance != null) {
				Imgproc.rectangle(mat,  new Point(angleAndDistance.getAvgCenterX()-THICKNESS, angleAndDistance.getAvgCenterY()-THICKNESS), 
										new Point(angleAndDistance.getAvgCenterX()-THICKNESS, angleAndDistance.getAvgCenterY()-THICKNESS), 
										COLOR, (int)THICKNESS);
			}
			// Whatever you want to put out to the screen, do it here.
			outputStream.putFrame(mat);
		}
	}

	static final ContourReport[] emptyCrArr = new ContourReport[0];
	
	private static List<ContourReport> findBestContours(List<MatOfPoint> contours)
	{
		// Find the 2 "best" contours.
		// What makes them the best?
		// - They look like tall rectangles
		if (contours == null)
		{
			return Collections.emptyList();
		}
		List<ContourReport> contourReports = new ArrayList<ContourReport>(contours.size());
		for(Iterator<MatOfPoint> iter = contours.iterator(); iter.hasNext();) {
			MatOfPoint matOfPoint = iter.next();
			Rect r = Imgproc.boundingRect(matOfPoint);
			// We only want contours that look like vision target rectangles.
			if (r.height < 1.2 * r.width) 
			{
				iter.remove();
			}
			else
			{
				contourReports.add(new ContourReport(r));
			}
		}
		if (contourReports.size() > 2)
		{
			ContourReport[] crArr;
			crArr = contourReports.toArray(emptyCrArr);
			Arrays.sort(crArr);
			contourReports.clear();
			contourReports.add(crArr[crArr.length-1]);
			contourReports.add(crArr[crArr.length-2]);
			
		}
		return contourReports;
	}
	
	private static AngleAndDistance findGearPegLocationBasedOnVisionTargets(List<MatOfPoint> contours)
	{
		List<ContourReport> bestContours = findBestContours(contours);
		if (bestContours.size() != 2)
		{
			// no idea. 
			return null;
		}
		// Let's find the midpoint between the 2
		double avgCenterX = (bestContours.get(0).getCenterX() + bestContours.get(1).getCenterX()) / 2.0;
		double avgCenterY = (bestContours.get(0).getCenterY() + bestContours.get(1).getCenterY()) / 2.0;
		double avgHeight = (bestContours.get(0).getHeight() + bestContours.get(1).getHeight()) / 2.0;
		double angleInDegrees = VisionUtil.angleToEstimate(avgCenterX);
		double distanceInInches = VisionUtil.distanceEstimate(avgHeight);
		
		return new AngleAndDistance(angleInDegrees, distanceInInches, avgCenterX, avgCenterY);
	}
	
	
}

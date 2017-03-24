package org.usfirst.frc.team3786.robot.vision;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team3786.robot.subsystems.GearTargetFinder;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;

public class NoNameRobotVision implements Runnable {

	public synchronized static Thread getRobotVisionThread() {
		if (visionThread != null)
			return visionThread;

		Thread thread = new Thread(getInstance());
		thread.setDaemon(true);
		thread.start();
		return thread;
	}

	private static NoNameRobotVision instance;
	private static Thread visionThread = null;
	private final AtomicReference<List<TargetPosition>> targetPositionsHolder = new AtomicReference<List<TargetPosition>>(Collections.emptyList());
	private NoNameRobotVision() {

	}
	public static NoNameRobotVision getInstance()
	{
		if (instance == null) {
			instance = new NoNameRobotVision();
		}
		return instance;
	}
	
	public List<TargetPosition> getTargetPositionList() {
		return targetPositionsHolder.getAndSet(Collections.emptyList()); // We set this to null after retrieving it, so we never return a stale target position.
	}

	@Override
	public void run() {
		// Get the UsbCamera from CameraServer
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		// Set the resolution
		camera.setResolution(640, 480);

		// Get a CvSink. This will capture Mats from the camera
		CvSink cvSink = CameraServer.getInstance().getVideo();
		// Setup a CvSource. This will send images back to the Dashboard
		CvSource outputStream = CameraServer.getInstance().putVideo("Rectangle", 640, 480);

		// Mats are very memory expensive. Lets reuse this Mat.
		Mat mat = new Mat();
		GripPipeline gripPipeline = new GripPipeline();
		GearTargetFinder gtf = GearTargetFinder.getInstance();
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
			List<ContourReport> contourReports = gtf.extractContourReports(gripPipeline.filterContoursOutput());
	    	List<ContourReport> objContourReports = gtf.findObjectiveContourReport(contourReports, WhichDirection.MIDDLE_LEFT);
	    	List<TargetPosition> targetList = gtf.extractListOfTargetPosition(objContourReports);
	    	if (targetList != null && !targetList.isEmpty())
	    	{
	    		targetPositionsHolder.set(targetList);
	    	}
			// Do image processing here, if you want.
			if (contourReports != null) {
				for (ContourReport contourReport : contourReports)
				{
					Imgproc.rectangle(mat,  new Point(contourReport.getLeftX(), contourReport.getTopY()), new Point(contourReport.getRightX(), contourReport.getBottomY()), new Scalar(124, 252, 0), 5);
				}
			}
			// Whatever you want to put out to the screen, do it here.
			outputStream.putFrame(mat);
		}
	}
	
	
}

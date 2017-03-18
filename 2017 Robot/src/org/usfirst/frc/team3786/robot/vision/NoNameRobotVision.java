package org.usfirst.frc.team3786.robot.vision;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;

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

		NoNameRobotVision instance = new NoNameRobotVision();
		Thread thread = new Thread(instance);
		thread.setDaemon(true);
		thread.start();
		return thread;
	}

	private static Thread visionThread = null;
	public static final java.util.concurrent.BlockingQueue<List<TargetPosition>> targetPositionQueue = new ArrayBlockingQueue<List<TargetPosition>>(1);
	private NoNameRobotVision() {

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
		int count = 0;
		while (!Thread.interrupted()) {
			// Tell the CvSink to grab a frame from the camera and put it
			// in the source mat. If there is an error notify the output.
			System.err.println("Frame count: "+count);
			++count;
			if (cvSink.grabFrame(mat) == 0) {
				// Send the output the error.
				outputStream.notifyError(cvSink.getError());
				// skip the rest of the current iteration
				continue;
			}
			gripPipeline.process(mat);
			List<ContourReport> contourReports = gtf.extractContourReports(gripPipeline.filterContoursOutput());
	    	List<ContourReport> objContourReports = gtf.findObjectiveContourReport(contourReports, WhichDirection.MIDDLE_LEFT);
	    	List<TargetPosition> targetList = gtf.extractListOfTargetPosition(objContourReports);
	    	if (targetList != null && !targetList.isEmpty())
	    	{
	    		targetPositionQueue.offer(targetList);
	    	}
			// Do image processing here...
//			Imgproc.rectangle(mat, new Point(100, 100), new Point(400, 400), new Scalar(255, 255, 255), 5);
			if (contourReports != null) {
				for (ContourReport contourReport : contourReports)
				{
					Imgproc.rectangle(mat,  new Point(contourReport.getLeftX(), contourReport.getTopY()), new Point(contourReport.getRightX(), contourReport.getBottomY()), new Scalar(255, 255, 255), 5);
				}
			}
			// Whatever you want to put out to the screen, do it here.
			outputStream.putFrame(mat);
		}
	}
}

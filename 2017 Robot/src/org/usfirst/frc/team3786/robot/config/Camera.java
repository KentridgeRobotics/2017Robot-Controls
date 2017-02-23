package org.usfirst.frc.team3786.robot.config;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITable;

public class Camera {
	
	private static Camera instance;
	
	public static Camera getInstance() {
		if(instance == null)
			instance = new Camera("VISION/ContourReport");
		return instance;
	}
	
	private NetworkTable table;
	
	private String KEY;
	private double[] defaultValue;
	
	private double[] cameraX, cameraY;
	
	private Camera(String key) {
		this.KEY = key;
		//NetworkTable.setClientMode();
		//NetworkTable.setIPAddress("10.37.86.88");
		table = NetworkTable.getTable(this.KEY);
		this.defaultValue = new double[1];
		SmartDashboard.putString("Camera", "Instantiated!");
	}
	
	public void pollCamera() {
		//cameraX = table.getNumberArray("centerX", defaultValue);
		//cameraY = table.getNumberArray("centerY", defaultValue);
		SmartDashboard.putNumber("X", table.getNumber("centerX", 0.0));
//		for(double e : cameraX) {
//			System.out.println(e);
//		}
	}
	
	public double[] getX() {
		return cameraX;
	}

}

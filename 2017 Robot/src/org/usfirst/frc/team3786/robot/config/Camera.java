package org.usfirst.frc.team3786.robot.config;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class Camera {
	
	private NetworkTable table;
	
	private String KEY;
	private double[] defaultValue;
	
	private double[] cameraX, cameraY;
	
	public Camera(String key) {
		this.KEY = key;
		table = NetworkTable.getTable(this.KEY);
		this.defaultValue = new double[0];
	}
	
	public void pollCamera() {
		cameraX = table.getNumberArray("centerX", defaultValue);
		cameraY = table.getNumberArray("centerY", defaultValue);
	}
	
	public double getX() {
		return cameraX[0];
	}

}

package org.usfirst.frc.team3786.robot.autonomous.map.gameobject;

public class GameObject {
	public ObjArea objArea;
	public double xPos;
	public double yPos;
	public boolean isSolid;
	
	public GameObject(ObjArea a, int x, int y, boolean solid) {
		objArea = a;
		xPos = x;
		yPos = y;
		isSolid = solid;
	}
	
}

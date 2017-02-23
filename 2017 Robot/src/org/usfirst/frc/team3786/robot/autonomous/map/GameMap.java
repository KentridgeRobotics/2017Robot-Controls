package org.usfirst.frc.team3786.robot.autonomous.map;

import java.util.ArrayList;

import org.usfirst.frc.team3786.robot.autonomous.map.gameobject.GameObject;

public class GameMap {
	private int _width;
	private int _height;
	public ArrayList<GameObject> mapObjects;
	
	
	public GameMap(int width, int height) {
		_width = width;
		_height = height;
	}
	
	public void addGameObject(GameObject obj) {
		mapObjects.add(obj);
	}
	
	//Return Dimensions
	public int getWidth() {
		return _width;
	}
	public int getHeight() {
		return _height;
	}
}

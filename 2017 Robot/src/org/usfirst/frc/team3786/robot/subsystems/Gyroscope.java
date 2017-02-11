package org.usfirst.frc.team3786.robot.subsystems;

import java.nio.ByteBuffer;

import edu.wpi.first.wpilibj.I2C;

/**
 * Gyroscope class to interface with the Arduino 
 */
public class Gyroscope {
	
	/**
	 * Enum/Datastructure which holds data to talk to an Arduino and hold the data it receives
	 */
	private enum Axis {
		X('x'),Y('y'),Z('y');
		
		private byte[] _axis = new byte[1];
		private byte[] _data = new byte[8];
		
		private Axis(char foo) {
			_axis[0] = (byte)foo;
		}
		
		/**
		 * Returns the data to tell the Arduino which axis data to send back
		 * @return The character value this enum holds
		 */
		public byte[] toByte() {
			return _axis;
		}
		
		/**
		 * Variable to store the data received back from the Arduino
		 * @return The 8 byte data axis data received from the Arduino
		 */
		public byte[] dataRecieved() {
			return _data;
		}
	};
	
	private I2C _gyroscope;
	private static Gyroscope _instance;
	
	/**
	 * Access methods of this class
	 * @return A static object reference to access this object/class
	 */
	public static Gyroscope getInstance() {
		if(_instance == null)
			_instance = new Gyroscope();
		return _instance;
	}
	
	/**
	 * Constructor which initializes the I2C connection to the device with ID 3786 (the Arduino)
	 */
	private Gyroscope() {
		_gyroscope = new I2C(I2C.Port.kMXP, 3786);
	}
	
	public boolean isConnected() {
		return _gyroscope.addressOnly();
	}
	
	/**
	 * Update all 3 Axis readings
	 */
	private void update() {
		_gyroscope.transaction(Axis.X.toByte(), 1, Axis.X.dataRecieved(), 8);
		_gyroscope.transaction(Axis.Y.toByte(), 1, Axis.Y.dataRecieved(), 8);
		_gyroscope.transaction(Axis.Z.toByte(), 1, Axis.Z.dataRecieved(), 8);
	}
	
	/**
	 * Converts an array of bytes to a double
	 * @param byteArray The array to convert
	 * @return A double containing the converted bytes
	 */
	private double toDouble(byte[] byteArray) {
		return ByteBuffer.wrap(byteArray).getDouble();
	}
	
	/**
	 * @return A double containing the X value read from the Gyroscope
	 */
	public double getX() {
		update();
		return toDouble(Axis.X.dataRecieved());
	}
	
	/**
	 * @return A double containing the Y value read from the Gyroscope
	 */
	public double getY() {
		update();
		return toDouble(Axis.Y.dataRecieved());
	}
	
	/**
	 * @return A double containing the Z value read from the Gyroscope
	 */
	public double getZ() {
		update();
		return toDouble(Axis.Z.dataRecieved());
	}
}

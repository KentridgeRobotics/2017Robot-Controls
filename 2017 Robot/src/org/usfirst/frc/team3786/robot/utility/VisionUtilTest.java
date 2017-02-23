package org.usfirst.frc.team3786.robot.utility;

import static org.junit.Assert.*;

import org.junit.Test;
import org.usfirst.frc.team3786.robot.vision.VisionUtil;

public class VisionUtilTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}

	@Test
	public void estimateDistanceTest() {
		
		assertEquals(36.0, VisionUtil.distanceEstimate(93), 0.01);
		assertEquals(36.0, VisionUtil.distanceEstimate(94), 0.01);	
	}
	

	
}

package org.usfirst.frc.team3786.robot.utility;

public class FinderOfRange {
	public static int rangeForVoltage(double voltage){
		if (voltage >= 2.9) {
			return 3;
		}
		else if (voltage < 2.9 && voltage >= 2.4) {
			return 4;
		}
		else if (voltage < 2.4 && voltage >= 2) {
			return 5;
		}
		else if (voltage < 2 && voltage >= 1.65) {
			return 6;
		}
		else if (voltage < 1.65 && voltage >= 1.46) {
			return 7;
		}
		else if (voltage < 1.46 && voltage >= 1.31) {
			return 8;
		}
		else if (voltage < 1.31 && voltage >= 1.15) {
			return 9;
		}
		else if (voltage < 1.15 && voltage >= 1.02) {
			return 10;
		}
		else if (voltage < 1.02 && voltage >= 0.98) {
			return 11;
		}
		else if (voltage < 0.98 && voltage >= 0.91) {
			return 12;
		}
		else if (voltage < 0.91 && voltage >= 0.85) {
			return 13;
		}
		else if (voltage < 0.85 && voltage >= 0.77) {
			return 14;
		}
		else if (voltage < 0.77 && voltage >= 0.7) {
			return 15;
		}
		else if (voltage < 0.7 && voltage >= 0.66) {
			return 16;
		}
		else if (voltage < 0.66 && voltage >= 0.64) {
			return 17;
		}
		else if (voltage < 0.64 && voltage >= 0.6) {
			return 18;
		}
		else if (voltage < 0.6) {
			return 19;
		}
		else {
			return Integer.MAX_VALUE;
		}
	}
}

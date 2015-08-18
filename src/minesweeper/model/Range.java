package minesweeper.model;

import java.awt.Point;

public class Range {

	private Range() {}
	
	public static boolean isInRange(double value, double min, double max) {
		if (value >= min && value <= max)
			return true;
		else 
			return false;
	}
	
	public static boolean isPointInBounds(Point point, double minX, double minY, double maxX, double maxY) {
		
		if (point.getX() >= minX && point.getX() <= maxX && point.getY() >= minY && point.getY() <= maxY)
			return true;
		else 
			return false;
	}

}

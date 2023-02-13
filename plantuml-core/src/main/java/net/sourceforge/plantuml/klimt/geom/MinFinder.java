package net.sourceforge.plantuml.klimt.geom;

import java.util.Objects;

import net.sourceforge.plantuml.awt.geom.XPoint2D;

public class MinFinder {

	private double minX = Double.MAX_VALUE;
	private double minY = Double.MAX_VALUE;

	public void manage(double x, double y) {
		if (x < minX) {
			minX = x;
		}
		if (y < minY) {
			minY = y;
		}
	}

	public void manage(XPoint2D p) {
		Objects.requireNonNull(p);
		manage(p.getX(), p.getY());
	}

	public void manage(MinFinder other) {
		manage(other.minX, other.minY);
	}

	@Override
	public String toString() {
		return "minX=" + minX + " minY=" + minY;
	}

	public double getMinX() {
		return minX;
	}

	public double getMinY() {
		return minY;
	}

}

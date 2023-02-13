package net.sourceforge.plantuml.klimt.geom;

import net.sourceforge.plantuml.awt.geom.XDimension2D;

public class MinMaxMutable {

	private double maxX;
	private double maxY;
	private double minX;
	private double minY;

	public static MinMaxMutable getEmpty(boolean initToZero) {
		if (initToZero)
			return new MinMaxMutable(0, 0, 0, 0);

		return new MinMaxMutable(Double.MAX_VALUE, Double.MAX_VALUE, -Double.MAX_VALUE, -Double.MAX_VALUE);
	}

	public boolean isInfinity() {
		return minX == Double.MAX_VALUE;
	}

	@Override
	public String toString() {
		return "X=" + minX + " " + maxX + " Y=" + minY + " " + maxY;
	}

	private MinMaxMutable(double minX, double minY, double maxX, double maxY) {
		if (Double.isNaN(minX))
			throw new IllegalArgumentException();

		if (Double.isNaN(maxX))
			throw new IllegalArgumentException();

		if (Double.isNaN(minY))
			throw new IllegalArgumentException();

		if (Double.isNaN(maxY))
			throw new IllegalArgumentException();

		this.minX = minX;
		this.minY = minY;
		this.maxX = maxX;
		this.maxY = maxY;
	}

	public void addPoint(double x, double y) {
		if (Double.isNaN(x))
			throw new IllegalArgumentException();

		if (Double.isNaN(y))
			throw new IllegalArgumentException();

		this.maxX = Math.max(x, maxX);
		this.maxY = Math.max(y, maxY);
		this.minX = Math.min(x, minX);
		this.minY = Math.min(y, minY);
	}

	public static MinMaxMutable fromMax(double maxX, double maxY) {
		if (Double.isNaN(maxX))
			throw new IllegalArgumentException();

		if (Double.isNaN(maxY))
			throw new IllegalArgumentException();

		final MinMaxMutable result = MinMaxMutable.getEmpty(true);
		result.addPoint(maxX, maxY);
		return result;
	}

	public final double getMaxX() {
		return maxX;
	}

	public final double getMaxY() {
		return maxY;
	}

	public final double getMinX() {
		return minX;
	}

	public final double getMinY() {
		return minY;
	}

	public XDimension2D getDimension() {
		return new XDimension2D(maxX - minX, maxY - minY);
	}

	public void reset() {
		this.maxX = 0;
		this.maxY = 0;
		this.minX = 0;
		this.minY = 0;
	}

}

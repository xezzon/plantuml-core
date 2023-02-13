package net.sourceforge.plantuml.klimt.geom;

public class BasicEnsureVisible implements EnsureVisible {

	private double minX = Double.MAX_VALUE;
	private double maxX = -Double.MAX_VALUE;
	private double minY = Double.MAX_VALUE;
	private double maxY = -Double.MAX_VALUE;

	public void ensureVisible(double x, double y) {
		if (x > maxX) {
			maxX = x;
		}
		if (x < minX) {
			minX = x;
		}
		if (y > maxY) {
			maxY = y;
		}
		if (y < minY) {
			minY = y;
		}
	}

	public boolean hasData() {
		return minX != Double.MAX_VALUE;
	}

	public String getCoords(double scale) {
		if (minX == Double.MAX_VALUE) {
			return "0,0,0,0";
		}
		final int x1 = (int) (minX * scale);
		final int y1 = (int) (minY * scale);
		final int x2 = (int) (maxX * scale);
		final int y2 = (int) (maxY * scale);
		return "" + x1 + "," + y1 + "," + x2 + "," + y2;
	}

	public double getSurface() {
		if (minX == Double.MAX_VALUE) {
			return 0;
		}
		return (maxX - minX) * (maxY - minY);
	}

}

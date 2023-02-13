package net.sourceforge.plantuml;

public class ScaleWidth extends ScaleProtected implements Scale {

	private final double maxWidth;

	public ScaleWidth(double maxWidth) {
		this.maxWidth = maxWidth;
	}

	public double getScaleInternal(double width, double height) {
		return maxWidth / width;
	}
}

package net.sourceforge.plantuml;

public class ScaleMaxWidth extends ScaleProtected implements Scale {

	private final double maxWidth;

	public ScaleMaxWidth(double maxWidth) {
		this.maxWidth = maxWidth;
	}

	public double getScaleInternal(double width, double height) {
		final double result = maxWidth / width;
		if (result > 1) {
			return 1;
		}
		return result;
	}
}

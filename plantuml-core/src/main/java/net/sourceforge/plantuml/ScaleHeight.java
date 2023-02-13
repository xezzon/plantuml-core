package net.sourceforge.plantuml;

public class ScaleHeight extends ScaleProtected implements Scale {

	private final double maxHeight;

	public ScaleHeight(double maxHeight) {
		this.maxHeight = maxHeight;
	}

	public double getScaleInternal(double width, double height) {
		return maxHeight / height;
	}
}

package net.sourceforge.plantuml;

public class ScaleMaxHeight extends ScaleProtected implements Scale {

	private final double maxHeight;

	public ScaleMaxHeight(double maxHeight) {
		this.maxHeight = maxHeight;
	}

	public double getScaleInternal(double width, double height) {
		final double result = maxHeight / height;
		if (result > 1) {
			return 1;
		}
		return result;
	}
}

package net.sourceforge.plantuml;

public class ScaleWidthAndHeight extends ScaleProtected implements Scale {

	private final double maxWidth;
	private final double maxHeight;

	public ScaleWidthAndHeight(double maxWidth, double maxHeight) {
		this.maxWidth = maxWidth;
		this.maxHeight = maxHeight;
	}

	public double getScaleInternal(double width, double height) {
		final double scale1 = maxWidth / width;
		final double scale2 = maxHeight / height;
//		if (scale1 > 1 && scale2 > 1) {
//			return 1;
//		}
		return Math.min(scale1, scale2);
	}
}

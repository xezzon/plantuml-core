package net.sourceforge.plantuml;

abstract class ScaleProtected implements Scale {

	abstract double getScaleInternal(double width, double height);

	final public double getScale(double width, double height) {
		final double result = getScaleInternal(width, height);
		if (result <= 0)
			return 1;

		if (result > 4)
			return 4;

		return result;
	}

}

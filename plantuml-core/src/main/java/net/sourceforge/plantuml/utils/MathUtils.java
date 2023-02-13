package net.sourceforge.plantuml.utils;

import net.sourceforge.plantuml.awt.geom.XDimension2D;

public class MathUtils {

	public static double max(double a, double b) {
		return Math.max(a, b);
	}

	public static double max(double a, double b, double c) {
		return max(max(a, b), c);
	}

	public static double max(double a, double b, double c, double d) {
		return max(max(a, b), max(c, d));
	}

	public static double max(double a, double b, double c, double d, double e) {
		return max(max(a, b, c), max(d, e));
	}

	public static double min(double a, double b) {
		return Math.min(a, b);
	}

	public static double min(double a, double b, double c) {
		return min(min(a, b), c);
	}

	public static double min(double a, double b, double c, double d) {
		return min(min(a, b), min(c, d));
	}

	public static double min(double a, double b, double c, double d, double e) {
		return min(min(a, b, c), min(d, e));
	}

	public static double limitation(double v, double min, double max) {
		if (min >= max) {
			// assert false : "min="+min+" max="+max+" v="+v;
			return v;
			// throw new IllegalArgumentException("min="+min+" max="+max+" v="+v);
		}
		if (v < min) {
			return min;
		}
		if (v > max) {
			return max;
		}
		return v;
	}

	public static XDimension2D max(XDimension2D dim1, XDimension2D dim2) {
		return new XDimension2D(Math.max(dim1.getWidth(), dim2.getWidth()),
				Math.max(dim1.getHeight(), dim2.getHeight()));
	}

	public static XDimension2D max(XDimension2D dim1, XDimension2D dim2, XDimension2D dim3) {
		return new XDimension2D(MathUtils.max(dim1.getWidth(), dim2.getWidth(), dim3.getWidth()),
				MathUtils.max(dim1.getHeight(), dim2.getHeight(), dim3.getHeight()));
	}

}

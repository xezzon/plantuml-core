package net.sourceforge.plantuml.awt.geom;

import java.awt.Dimension;

import net.sourceforge.plantuml.utils.MathUtils;

public class XDimension2D {

	final private double width;
	final private double height;

	public XDimension2D() {
		this(0, 0);
	}

	public XDimension2D(double width, double height) {
		if (Double.isNaN(width) || Double.isNaN(height))
			throw new IllegalArgumentException();

		this.width = width;
		this.height = height;
	}

	@Override
	public String toString() {
		return "[" + width + "," + height + "]";
	}

	public double getHeight() {
		return height;
	}

	public double getWidth() {
		return width;
	}

	public XDimension2D delta(double delta) {
		return this.delta(delta, delta);
	}

	public XDimension2D withWidth(double newWidth) {
		return new XDimension2D(newWidth, height);
	}

	public XDimension2D delta(double deltaWidth, double deltaHeight) {
		if (deltaHeight == 0 && deltaWidth == 0)
			return this;

		return new XDimension2D(getWidth() + deltaWidth, getHeight() + deltaHeight);
	}

	public XDimension2D mergeTB(XDimension2D bottom) {
		final double width = Math.max(this.getWidth(), bottom.getWidth());
		final double height = this.getHeight() + bottom.getHeight();
		return new XDimension2D(width, height);
	}

	public XDimension2D mergeTB(XDimension2D b, XDimension2D c) {
		final double width = MathUtils.max(this.getWidth(), b.getWidth(), c.getWidth());
		final double height = this.getHeight() + b.getHeight() + c.getHeight();
		return new XDimension2D(width, height);
	}

	public XDimension2D mergeLR(XDimension2D right) {
		final double height = Math.max(this.getHeight(), right.getHeight());
		final double width = this.getWidth() + right.getWidth();
		return new XDimension2D(width, height);
	}

	public XDimension2D atLeast(double minWidth, double minHeight) {
		double h = getHeight();
		double w = getWidth();
		if (w > minWidth && h > minHeight)
			return this;

		if (h < minHeight)
			h = minHeight;

		if (w < minWidth)
			w = minWidth;

		return new XDimension2D(w, h);
	}

	public static XDimension2D fromDimension(Dimension dimension) {
		return new XDimension2D(dimension.getWidth(), dimension.getHeight());
	}

	public static XDimension2D mergeLayoutT12B3(XDimension2D top1, XDimension2D top2, XDimension2D bottom) {
		final double width = MathUtils.max(top1.getWidth(), top2.getWidth(), bottom.getWidth());
		final double height = top1.getHeight() + top2.getHeight() + bottom.getHeight();
		return new XDimension2D(width, height);
	}

	public static XDimension2D max(XDimension2D dim1, XDimension2D dim2) {
		return dim1.atLeast(dim2.getWidth(), dim2.getHeight());
	}

}

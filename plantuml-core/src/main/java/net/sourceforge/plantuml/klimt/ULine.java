package net.sourceforge.plantuml.klimt;

import java.awt.geom.AffineTransform;

import net.sourceforge.plantuml.awt.geom.XPoint2D;

public class ULine extends AbstractShadowable implements UShapeSized {

	private final double dx;
	private final double dy;

	public ULine(XPoint2D p1, XPoint2D p2) {
		this(p2.getX() - p1.getX(), p2.getY() - p1.getY());
	}

	public ULine(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}

	public ULine rotate(double theta) {
		if (theta == 0)
			return this;
		final AffineTransform rot = AffineTransform.getRotateInstance(theta);
		final XPoint2D tmp = new XPoint2D(dx, dy).transform(rot);
		return new ULine(tmp.getX(), tmp.getY());
	}

	public static ULine hline(double dx) {
		return new ULine(dx, 0);
	}

	public static ULine vline(double dy) {
		return new ULine(0, dy);
	}

	@Override
	public String toString() {
		return "ULine dx=" + dx + " dy=" + dy;
	}

	public double getDX() {
		return dx;
	}

	public double getDY() {
		return dy;
	}

	public double getLength() {
		return Math.sqrt(dx * dx + dy * dy);
	}

	public double getWidth() {
		return dx;
	}

	public double getHeight() {
		return dy;
	}

}

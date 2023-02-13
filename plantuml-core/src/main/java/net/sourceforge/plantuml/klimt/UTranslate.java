package net.sourceforge.plantuml.klimt;

import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.awt.geom.XPoint2D;
import net.sourceforge.plantuml.awt.geom.XRectangle2D;

public class UTranslate implements UChange {

	private final double dx;
	private final double dy;

	@Override
	public String toString() {
		return "translate dx=" + dx + " dy=" + dy;
	}

	public static UTranslate none() {
		return new UTranslate(0, 0);
	}

	public UTranslate(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}

	public static UTranslate dx(double dx) {
		return new UTranslate(dx, 0);
	}

	public static UTranslate dy(double dy) {
		return new UTranslate(0, dy);
	}

	public UTranslate(XPoint2D p) {
		this(p.getX(), p.getY());
	}

	public UTranslate() {
		this(0, 0);
	}

	public double getDx() {
		return dx;
	}

	public double getDy() {
		return dy;
	}

	public boolean isAlmostSame(UTranslate other) {
		return this.dx == other.dx || this.dy == other.dy;
	}

	public XPoint2D getTranslated(XPoint2D p) {
		if (p == null)
			return null;

		return new XPoint2D(p.getX() + dx, p.getY() + dy);
	}

	public XDimension2D getTranslated(XDimension2D dim) {
		return new XDimension2D(dim.getWidth() + dx, dim.getHeight() + dy);
	}

	public UTranslate scaled(double scale) {
		return new UTranslate(dx * scale, dy * scale);
	}

	public UTranslate compose(UTranslate other) {
		return new UTranslate(dx + other.dx, dy + other.dy);
	}

	public UTranslate reverse() {
		return new UTranslate(-dx, -dy);
	}

	public XRectangle2D apply(XRectangle2D rect) {
		return new XRectangle2D(rect.getX() + dx, rect.getY() + dy, rect.getWidth(), rect.getHeight());
	}

	public UTranslate multiplyBy(double v) {
		return new UTranslate(dx * v, dy * v);
	}

	public UTranslate sym() {
		return new UTranslate(dy, dx);
	}

	public XPoint2D getPosition() {
		return new XPoint2D(dx, dy);
	}

}

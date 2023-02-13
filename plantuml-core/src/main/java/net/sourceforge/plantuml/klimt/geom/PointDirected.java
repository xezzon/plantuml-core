package net.sourceforge.plantuml.klimt.geom;

import net.sourceforge.plantuml.awt.geom.XPoint2D;

public class PointDirected {

	final private double x;
	final private double y;
	final private double angle;

	public PointDirected(XPoint2D p, double angle) {
		this.x = p.getX();
		this.y = p.getY();
		this.angle = angle;
	}

	public final XPoint2D getPoint2D() {
		return new XPoint2D(x, y);
	}

	public final double getAngle() {
		return angle;
	}

}

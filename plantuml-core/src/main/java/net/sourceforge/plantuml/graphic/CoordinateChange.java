package net.sourceforge.plantuml.graphic;

import net.sourceforge.plantuml.awt.geom.XPoint2D;

class CoordinateChange {

	private final double x1;
	private final double y1;
	private final double x2;
	private final double y2;

	private final double vect_u_x;
	private final double vect_u_y;
	private final double vect_v_x;
	private final double vect_v_y;
	private final double len;

	public static CoordinateChange create(XPoint2D p1, XPoint2D p2) {
		return new CoordinateChange(p1.getX(), p1.getY(), p2.getX(), p2.getY());
	}

	public CoordinateChange(double x1, double y1, double x2, double y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.len = XPoint2D.distance(x1, y1, x2, y2);
		if (this.len == 0) {
			throw new IllegalArgumentException();
		}

		this.vect_u_x = (x2 - x1) / len;
		this.vect_u_y = (y2 - y1) / len;

		this.vect_v_x = -this.vect_u_y;
		this.vect_v_y = this.vect_u_x;

	}

	public XPoint2D getTrueCoordinate(double a, double b) {
		final double x = a * vect_u_x + b * vect_v_x;
		final double y = a * vect_u_y + b * vect_v_y;
		return new XPoint2D(x1 + x, y1 + y);
	}

	public final double getLength() {
		return len;
	}

}

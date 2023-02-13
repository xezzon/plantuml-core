package net.sourceforge.plantuml.posimo;

import net.sourceforge.plantuml.awt.geom.XLine2D;
import net.sourceforge.plantuml.awt.geom.XPoint2D;

public class LineSegmentIntersection {

	private final XPoint2D inter;
	
	// http://local.wasp.uwa.edu.au/~pbourke/geometry/lineline2d/

	public LineSegmentIntersection(XLine2D segment, XLine2D lineB) {
		final double x1 = segment.getX1();
		final double y1 = segment.getY1();
		final double x2 = segment.getX2();
		final double y2 = segment.getY2();
		final double x3 = lineB.getX1();
		final double y3 = lineB.getY1();
		final double x4 = lineB.getX2();
		final double y4 = lineB.getY2();

		final double den = (y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1);

		if (den == 0) {
			inter = null;
		} else {

			final double uA1 = (x4 - x3) * (y1 - y3) - (y4 - y3) * (x1 - x3);
			final double uA = uA1 / den;

			final double x = x1 + uA * (x2 - x1);
			final double y = y1 + uA * (y2 - y1);

			if (uA >= 0 && uA <= 1) {
				inter = new XPoint2D(x, y);
			} else {
				inter = null;
			}
		}
	}

	public final XPoint2D getIntersection() {
		return inter;
	}

}

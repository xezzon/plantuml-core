package net.sourceforge.plantuml.svek.extremity;

import net.sourceforge.plantuml.awt.geom.XPoint2D;
import net.sourceforge.plantuml.klimt.UStroke;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.ugraphic.UEllipse;
import net.sourceforge.plantuml.ugraphic.UGraphic;

class ExtremityCircleConnect extends Extremity {

	private final double px;
	private final double py;
	private final XPoint2D dest;
	private final double radius = 6;
	private final double radius2 = 10;
	private final double ortho;
	private final HColor backgroundColor;

	@Override
	public XPoint2D somePoint() {
		return dest;
	}

	public ExtremityCircleConnect(XPoint2D p1, double ortho, HColor backgroundColor) {
		this.px = p1.getX() - radius;
		this.py = p1.getY() - radius;
		this.dest = new XPoint2D(p1.getX(), p1.getY());
		this.ortho = ortho;
		this.backgroundColor = backgroundColor;
	}

	public void drawU(UGraphic ug) {
		ug = ug.apply(new UStroke(1.5)).apply(backgroundColor.bg());
		ug.apply(new UTranslate(dest.getX() - radius, dest.getY() - radius)).draw(new UEllipse(radius * 2, radius * 2));

		final double deg = -ortho * 180 / Math.PI + 90 - 45;
		final UEllipse arc1 = new UEllipse(2 * radius2, 2 * radius2, deg, 90);
		ug.apply(new UTranslate(dest.getX() - radius2, dest.getY() - radius2)).draw(arc1);
	}

	// private XPoint2D getPointOnCircle(double angle) {
	// final double x = px + radius + radius2 * Math.cos(angle);
	// final double y = py + radius + radius2 * Math.sin(angle);
	// return new XPoint2D(x, y);
	// }
	//
	// static private void drawLine(UGraphic ug, double x, double y, Point2D p1,
	// Point2D p2) {
	// final double dx = p2.getX() - p1.getX();
	// final double dy = p2.getY() - p1.getY();
	// ug.draw(x + p1.getX(), y + p1.getY(), new ULine(dx, dy));
	//
	// }

}

package net.sourceforge.plantuml.jsondiagram;

import net.sourceforge.plantuml.awt.geom.XPoint2D;
import net.sourceforge.plantuml.klimt.ULine;
import net.sourceforge.plantuml.klimt.UPath;
import net.sourceforge.plantuml.klimt.UStroke;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public class Arrow {

	private final XPoint2D p1;
	private final XPoint2D p2;

	public Arrow(XPoint2D p1, XPoint2D p2) {
		this.p1 = p1;
		this.p2 = p2;
	}

	public void drawArrow(UGraphic ug) {
		ug = ug.apply(new UStroke());

		final ULine p1p2 = new ULine(p1, p2);
		final double dist = p1.distance(p2);

		final double alpha = Math.atan2(p1p2.getDX(), p1p2.getDY());

		final double factor = .4;
		final double factor2 = .3;
		
		final XPoint2D p3 = getPoint(p1, alpha + Math.PI / 2, dist * factor);
		final XPoint2D p4 = getPoint(p1, alpha - Math.PI / 2, dist * factor);
		final XPoint2D p11 = getPoint(p1, alpha, dist * factor2);

		
		final UPath path = new UPath();
		path.moveTo(p4);
		path.lineTo(p11);
		path.lineTo(p3);
		path.lineTo(p2);
		path.lineTo(p4);
		path.closePath();

		ug.draw(path);

	}

	private XPoint2D getPoint(XPoint2D center, double alpha, double len) {
		final double x = center.getX() + len * Math.sin(alpha);
		final double y = center.getY() + len * Math.cos(alpha);
		return new XPoint2D(x, y);
	}

}

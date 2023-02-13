package net.sourceforge.plantuml.svek.extremity;

import java.awt.geom.AffineTransform;

import net.sourceforge.plantuml.awt.geom.XPoint2D;
import net.sourceforge.plantuml.klimt.ULine;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.ugraphic.UGraphic;

class ExtremityDoubleLine extends Extremity {

	private final XPoint2D contact;
	private final double angle;
	private final double lineHeight = 4;

	@Override
	public XPoint2D somePoint() {
		return contact;
	}

	public ExtremityDoubleLine(XPoint2D p1, double angle) {
		this.contact = new XPoint2D(p1.getX(), p1.getY());
		this.angle = manageround(angle + Math.PI / 2);
	}

	public void drawU(UGraphic ug) {
		final int xWing = 4;
		final AffineTransform rotate = AffineTransform.getRotateInstance(this.angle);
		XPoint2D firstLineTop = new XPoint2D(-xWing, -lineHeight);
		XPoint2D firstLineBottom = new XPoint2D(-xWing, lineHeight);
		XPoint2D secondLineTop = new XPoint2D(-xWing - 3, -lineHeight);
		XPoint2D secondLineBottom = new XPoint2D(-xWing - 3, lineHeight);

		XPoint2D middle = new XPoint2D(0, 0);
		XPoint2D base = new XPoint2D(-xWing - 4, 0);

		middle = middle.transform(rotate);
		base = base.transform(rotate);
		firstLineTop = firstLineTop.transform(rotate);
		firstLineBottom = firstLineBottom.transform(rotate);
		secondLineTop = secondLineTop.transform(rotate);
		secondLineBottom = secondLineBottom.transform(rotate);

		drawLine(ug, contact.getX(), contact.getY(), firstLineTop, firstLineBottom);
		drawLine(ug, contact.getX(), contact.getY(), secondLineTop, secondLineBottom);
		drawLine(ug, contact.getX(), contact.getY(), base, middle);
	}

	static private void drawLine(UGraphic ug, double x, double y, XPoint2D p1, XPoint2D p2) {
		final double dx = p2.getX() - p1.getX();
		final double dy = p2.getY() - p1.getY();
		ug.apply(new UTranslate(x + p1.getX(), y + p1.getY())).draw(new ULine(dx, dy));
	}

}

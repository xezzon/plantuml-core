package net.sourceforge.plantuml.svek.extremity;

import net.sourceforge.plantuml.awt.geom.XPoint2D;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.svek.image.EntityImageLollipopInterfaceEye2;
import net.sourceforge.plantuml.ugraphic.UEllipse;
import net.sourceforge.plantuml.ugraphic.UGraphic;

class ExtremityParenthesis2 extends Extremity {

	private final XPoint2D contact;
	private final XPoint2D center;

	private final double ortho;
	private final double ang = 30;

	public ExtremityParenthesis2(XPoint2D contact, double ortho, XPoint2D p1) {
		this.contact = new XPoint2D(contact.getX(), contact.getY());
		this.ortho = ortho;
		final double dx = p1.getX() - contact.getX();
		final double dy = p1.getY() - contact.getY();
		final double distance1 = Math.round(contact.distance(p1));
		// System.err.println("distance=" + distance1);
		final double len = Math.round(distance1 + EntityImageLollipopInterfaceEye2.SIZE / 2);
		this.center = new XPoint2D(contact.getX() + dx / distance1 * len, contact.getY() + dy / distance1 * len);
	}
	
	@Override
	public XPoint2D somePoint() {
		return contact;
	}


	public void drawU(UGraphic ug) {
		final double deg = -ortho * 180 / Math.PI + 90 - ang;
		// final XPoint2D other = new XPoint2D(contact.getX() + 10 * Math.cos(deg), contact.getY() + 10
		// * Math.sin(deg));
		// final ULine line = new ULine(1, 1);
		// ug.apply(UChangeColor.nnn(HtmlColorUtils.GREEN)).apply(new UTranslate(contact.getX(), contact.getY()))
		// .draw(line);
		// ug.apply(UChangeColor.nnn(HtmlColorUtils.BLACK)).apply(new UTranslate(center.getX(),
		// center.getY())).draw(line);
		// // final UEllipse arc1 = new UEllipse(2 * radius2, 2 * radius2, deg, 2 * ang);
		// // ug.apply(new UStroke(1.5)).apply(new UTranslate(dest.getX() - radius2, dest.getY() - radius2)).draw(arc1);
		//
		final double size = Math.round(contact.distance(center));
		// System.err.println("size=" + size);
		final UEllipse circle = new UEllipse(size * 2, size * 2, deg, 2 * ang);
		ug.apply(new UTranslate(center.getX() - size, center.getY() - size)).draw(circle);

	}
}

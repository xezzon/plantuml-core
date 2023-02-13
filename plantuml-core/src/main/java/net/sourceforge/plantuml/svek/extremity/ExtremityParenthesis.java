package net.sourceforge.plantuml.svek.extremity;

import net.sourceforge.plantuml.awt.geom.XPoint2D;
import net.sourceforge.plantuml.klimt.UStroke;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.ugraphic.UEllipse;
import net.sourceforge.plantuml.ugraphic.UGraphic;

class ExtremityParenthesis extends Extremity {

	private final XPoint2D dest;
	private final double radius2 = 9;
	private final double ortho;

	private final double ang = 70;

	public ExtremityParenthesis(XPoint2D p1, double ortho) {
		this.dest = new XPoint2D(p1.getX(), p1.getY());
		this.ortho = ortho;
	}
	
	@Override
	public XPoint2D somePoint() {
		return dest;
	}


	public void drawU(UGraphic ug) {
		final double deg = -ortho * 180 / Math.PI + 90 - ang;
		final UEllipse arc1 = new UEllipse(2 * radius2, 2 * radius2, deg, 2 * ang);
		ug.apply(new UStroke(1.5)).apply(new UTranslate(dest.getX() - radius2, dest.getY() - radius2)).draw(arc1);
	}

}

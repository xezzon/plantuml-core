package net.sourceforge.plantuml.salt.element;

import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.ULine;
import net.sourceforge.plantuml.klimt.UStroke;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public class ElementLine extends AbstractElement {

	private final char separator;

	public ElementLine(char separator) {
		this.separator = separator;
	}

	public XDimension2D getPreferredDimension(StringBounder stringBounder, double x, double y) {
		return new XDimension2D(10, 6);
	}

	public void drawU(UGraphic ug, int zIndex, XDimension2D dimToUse) {
		if (zIndex != 0) {
			return;
		}
		ug = ug.apply(getColorAA());
		double y2 = dimToUse.getHeight() / 2;
		if (separator == '=') {
			y2 = y2 - 1;
		}
		drawLine(ug, 0, y2, dimToUse.getWidth(), separator);
	}

	private static void drawLine(UGraphic ug, double x, double y, double widthToUse, char separator) {
		if (separator == '=') {
			ug.apply(new UStroke()).apply(new UTranslate(x, y)).draw(ULine.hline(widthToUse));
			ug.apply(new UStroke()).apply(new UTranslate(x, y + 2)).draw(ULine.hline(widthToUse));
		} else if (separator == '.') {
			ug.apply(new UStroke(1, 2, 1)).apply(new UTranslate(x, y)).draw(ULine.hline(widthToUse));
		} else if (separator == '-') {
			ug.apply(new UStroke()).apply(new UTranslate(x, y)).draw(ULine.hline(widthToUse));
		} else {
			ug.apply(new UStroke(1.5)).apply(new UTranslate(x, y)).draw(ULine.hline(widthToUse));
		}
	}

}

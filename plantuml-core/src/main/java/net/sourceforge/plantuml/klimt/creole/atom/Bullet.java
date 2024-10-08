// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml.klimt.creole.atom;

import net.sourceforge.plantuml.klimt.UStroke;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.FontConfiguration;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.shape.UEllipse;
import net.sourceforge.plantuml.klimt.shape.URectangle;

public class Bullet extends AbstractAtom implements Atom {

	private final FontConfiguration fontConfiguration;
	private final int order;

	public Bullet(FontConfiguration fontConfiguration, int order) {
		this.fontConfiguration = fontConfiguration;
		this.order = order;
	}

	public void drawU(UGraphic ug) {
		final HColor color = fontConfiguration.getColor();
		ug = ug.apply(color).apply(color.bg()).apply(UStroke.withThickness(0));
		if (order == 0) {
			ug = ug.apply(UTranslate.dx(3));
			ug.draw(UEllipse.build(5, 5));
		} else {
			ug = ug.apply(UTranslate.dx(1 + 8 * order));
			ug.draw(URectangle.build(3.5, 3.5));
		}

	}

	public XDimension2D calculateDimension(StringBounder stringBounder) {
		if (order == 0)
			return new XDimension2D(12, 5);
		return new XDimension2D(8 + 8 * order, 3);
	}

	public double getStartingAltitude(StringBounder stringBounder) {
		if (order == 0)
			return -5;
		return -7;
	}

}

package net.sourceforge.plantuml.skin.rose;

import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.skin.Area;
import net.sourceforge.plantuml.skin.Component;
import net.sourceforge.plantuml.skin.Context2D;
import net.sourceforge.plantuml.style.Style;
import net.sourceforge.plantuml.style.StyleSignatureBasic;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public class ComponentRoseGroupingSpace implements Component {

	public Style[] getUsedStyles() {
		throw new UnsupportedOperationException();
	}

	public StyleSignatureBasic getStyleSignature() {
		throw new UnsupportedOperationException();
	}

	private final double space;

	public ComponentRoseGroupingSpace(double space) {
		this.space = space;
	}

	public double getPreferredWidth(StringBounder stringBounder) {
		return 0;
	}

	public double getPreferredHeight(StringBounder stringBounder) {
		return space;
	}

	public void drawU(UGraphic ug, Area area, Context2D context) {
	}

	public final XDimension2D getPreferredDimension(StringBounder stringBounder) {
		final double w = getPreferredWidth(stringBounder);
		final double h = getPreferredHeight(stringBounder);
		return new XDimension2D(w, h);
	}

}

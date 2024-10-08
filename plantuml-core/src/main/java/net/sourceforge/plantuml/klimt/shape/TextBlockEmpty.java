// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml.klimt.shape;

import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;

public class TextBlockEmpty extends AbstractTextBlock {

	private final double width;
	private final double height;

	public TextBlockEmpty(double width, double height) {
		this.width = width;
		this.height = height;
	}

	public TextBlockEmpty() {
		this(0, 0);
	}

	public XDimension2D calculateDimension(StringBounder stringBounder) {
		return new XDimension2D(width, height);
	}

	public void drawU(UGraphic ug) {
	}

}

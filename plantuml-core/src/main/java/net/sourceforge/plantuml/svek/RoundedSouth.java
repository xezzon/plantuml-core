// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml.svek;

import net.sourceforge.plantuml.klimt.UPath;
import net.sourceforge.plantuml.klimt.UShape;
import net.sourceforge.plantuml.klimt.UStroke;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.shape.UDrawable;
import net.sourceforge.plantuml.klimt.shape.URectangle;

public final class RoundedSouth implements UDrawable {

	private final double width;
	private final double height;
	private final HColor backColor;
	private final double rounded;

	public RoundedSouth(double width, double height, HColor backColor, double rounded) {
		if (width == 0)
			throw new IllegalArgumentException();
		if (height == 0)
			throw new IllegalArgumentException();

		this.width = width;
		this.height = height;
		this.rounded = rounded;
		this.backColor = backColor;
	}

	public void drawU(UGraphic ug) {
		if (backColor.isTransparent())
			return;

		final UShape header;
		if (rounded == 0) {
			header = URectangle.build(width, height);
		} else {
			final UPath path = UPath.none();
			path.moveTo(0, 0);
			path.lineTo(width, 0);
			path.lineTo(width, height - rounded / 2);
			path.arcTo(rounded / 2, rounded / 2, 0, 0, 1, width - rounded / 2, height);
			path.lineTo(rounded / 2, height);
			path.arcTo(rounded / 2, rounded / 2, 0, 0, 1, 0, height - rounded / 2);
			path.lineTo(0, 0);
			path.closePath();
			header = path;
		}
		ug.apply(UStroke.simple()).apply(backColor).apply(backColor.bg()).draw(header);

	}
}

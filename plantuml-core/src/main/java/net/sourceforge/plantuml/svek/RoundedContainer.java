// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml.svek;

import net.sourceforge.plantuml.klimt.UStroke;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.color.HColors;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.shape.ULine;
import net.sourceforge.plantuml.klimt.shape.URectangle;

public final class RoundedContainer {

	private final XDimension2D dim;
	private final double titleHeight;
	private final double attributeHeight;
	private final HColor borderColor;
	private final HColor backColor;
	private final HColor imgBackcolor;
	private final UStroke stroke;
	private final double rounded;
	private final double shadowing;

	public RoundedContainer(XDimension2D dim, double titleHeight, double attributeHeight, HColor borderColor,
			HColor backColor, HColor imgBackcolor, UStroke stroke, double rounded, double shadowing) {
		if (dim.getWidth() == 0)
			throw new IllegalArgumentException();

		this.rounded = rounded;
		this.dim = dim;
		this.imgBackcolor = imgBackcolor;
		this.titleHeight = titleHeight;
		this.borderColor = borderColor;
		this.backColor = backColor;
		this.attributeHeight = attributeHeight;
		this.stroke = stroke;
		this.shadowing = shadowing;
	}

	public void drawU(UGraphic ug) {
		ug = ug.apply(backColor.bg()).apply(borderColor).apply(stroke);
		final URectangle rect = new URectangle(dim.getWidth(), dim.getHeight()).rounded(rounded);

		if (shadowing > 0) {
			rect.setDeltaShadow(shadowing);
			ug.apply(HColors.transparent().bg()).draw(rect);
			rect.setDeltaShadow(0);

		}
		final double headerHeight = titleHeight + attributeHeight;

		new RoundedNorth(dim.getWidth(), headerHeight, backColor, rounded).drawU(ug);
		new RoundedSouth(dim.getWidth(), dim.getHeight() - headerHeight, imgBackcolor, rounded)
				.drawU(ug.apply(UTranslate.dy(headerHeight)));

		ug.apply(HColors.transparent().bg()).draw(rect);

		if (headerHeight > 0)
			ug.apply(UTranslate.dy(headerHeight)).draw(ULine.hline(dim.getWidth()));

		if (attributeHeight > 0)
			ug.apply(UTranslate.dy(titleHeight)).draw(ULine.hline(dim.getWidth()));

	}
}

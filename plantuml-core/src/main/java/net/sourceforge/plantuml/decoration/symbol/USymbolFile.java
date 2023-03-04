// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml.decoration.symbol;

import net.sourceforge.plantuml.klimt.Fashion;
import net.sourceforge.plantuml.klimt.Shadowable;
import net.sourceforge.plantuml.klimt.UPath;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.drawing.UGraphicStencil;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.HorizontalAlignment;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.geom.XPoint2D;
import net.sourceforge.plantuml.klimt.shape.AbstractTextBlock;
import net.sourceforge.plantuml.klimt.shape.TextBlock;
import net.sourceforge.plantuml.klimt.shape.TextBlockUtils;
import net.sourceforge.plantuml.klimt.shape.UPolygon;
import net.sourceforge.plantuml.style.SName;

class USymbolFile extends USymbol {

	private final HorizontalAlignment stereotypeAlignement = HorizontalAlignment.CENTER;

	@Override
	public SName getSName() {
		return SName.file;
	}

	private void drawFile(UGraphic ug, double width, double height, double shadowing, double roundCorner) {
		final int cornersize = 10;
		final Shadowable out;
		if (roundCorner == 0) {
			final UPolygon polygon = new UPolygon();
			polygon.addPoint(0, 0);
			polygon.addPoint(0, height);
			polygon.addPoint(width, height);
			polygon.addPoint(width, cornersize);
			polygon.addPoint(width - cornersize, 0);
			polygon.addPoint(0, 0);
			out = polygon;
		} else {
			final UPath path = new UPath();
			path.moveTo(0, roundCorner / 2);
			path.lineTo(0, height - roundCorner / 2);
			path.arcTo(new XPoint2D(roundCorner / 2, height), roundCorner / 2, 0, 0);
			path.lineTo(width - roundCorner / 2, height);
			path.arcTo(new XPoint2D(width, height - roundCorner / 2), roundCorner / 2, 0, 0);
			path.lineTo(width, cornersize);
			path.lineTo(width - cornersize, 0);
			path.lineTo(roundCorner / 2, 0);
			path.arcTo(new XPoint2D(0, roundCorner / 2), roundCorner / 2, 0, 0);
			out = path;
		}

		out.setDeltaShadow(shadowing);
		ug.draw(out);

		final UPath path = new UPath();
		path.moveTo(width - cornersize, 0);
		if (roundCorner == 0) {
			path.lineTo(width - cornersize, cornersize);
		} else {
			path.lineTo(width - cornersize, cornersize - roundCorner / 2);
			path.arcTo(new XPoint2D(width - cornersize + roundCorner / 2, cornersize), roundCorner / 2, 0, 0);
		}
		path.lineTo(width, cornersize);
		ug.draw(path);
	}

	private Margin getMargin() {
		return new Margin(10, 10, 10, 10);
	}

	@Override
	public TextBlock asSmall(TextBlock name, final TextBlock label, final TextBlock stereotype,
			final Fashion symbolContext, final HorizontalAlignment stereoAlignment) {
		return new AbstractTextBlock() {

			public void drawU(UGraphic ug) {
				final XDimension2D dim = calculateDimension(ug.getStringBounder());
				ug = UGraphicStencil.create(ug, dim);
				ug = symbolContext.apply(ug);
				drawFile(ug, dim.getWidth(), dim.getHeight(), symbolContext.getDeltaShadow(),
						symbolContext.getRoundCorner());
				final Margin margin = getMargin();
				final TextBlock tb = TextBlockUtils.mergeTB(stereotype, label, HorizontalAlignment.CENTER);
				tb.drawU(ug.apply(new UTranslate(margin.getX1(), margin.getY1())));
			}

			public XDimension2D calculateDimension(StringBounder stringBounder) {
				final XDimension2D dimLabel = label.calculateDimension(stringBounder);
				final XDimension2D dimStereo = stereotype.calculateDimension(stringBounder);
				return getMargin().addDimension(dimStereo.mergeTB(dimLabel));
			}
		};
	}

	@Override
	public TextBlock asBig(final TextBlock title, HorizontalAlignment labelAlignment, final TextBlock stereotype,
			final double width, final double height, final Fashion symbolContext,
			final HorizontalAlignment stereoAlignment) {
		return new AbstractTextBlock() {

			public void drawU(UGraphic ug) {
				final XDimension2D dim = calculateDimension(ug.getStringBounder());
				ug = symbolContext.apply(ug);
				drawFile(ug, dim.getWidth(), dim.getHeight(), symbolContext.getDeltaShadow(),
						symbolContext.getRoundCorner());
				final XDimension2D dimStereo = stereotype.calculateDimension(ug.getStringBounder());
				final double posStereoX;
				final double posStereoY;
				if (stereotypeAlignement == HorizontalAlignment.RIGHT) {
					posStereoX = width - dimStereo.getWidth() - getMargin().getX1() / 2;
					posStereoY = getMargin().getY1() / 2;
				} else {
					posStereoX = (width - dimStereo.getWidth()) / 2;
					posStereoY = 2;
				}
				stereotype.drawU(ug.apply(new UTranslate(posStereoX, posStereoY)));
				final XDimension2D dimTitle = title.calculateDimension(ug.getStringBounder());
				final double posTitle = (width - dimTitle.getWidth()) / 2;
				title.drawU(ug.apply(new UTranslate(posTitle, 2 + dimStereo.getHeight())));
			}

			public XDimension2D calculateDimension(StringBounder stringBounder) {
				return new XDimension2D(width, height);
			}
		};
	}

}

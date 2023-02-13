package net.sourceforge.plantuml.activitydiagram3.ftile.vertical;

import net.sourceforge.plantuml.ISkinParam;
import net.sourceforge.plantuml.activitydiagram3.ftile.Ftile;
import net.sourceforge.plantuml.activitydiagram3.ftile.FtileGeometry;
import net.sourceforge.plantuml.activitydiagram3.ftile.Hexagon;
import net.sourceforge.plantuml.activitydiagram3.ftile.Swimlane;
import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.graphic.TextBlock;
import net.sourceforge.plantuml.graphic.TextBlockUtils;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public class FtileDiamondInside extends FtileDiamondWIP {

	public FtileDiamondInside(TextBlock label, ISkinParam skinParam, HColor backColor, HColor borderColor,
			Swimlane swimlane) {
		this(label, skinParam, backColor, borderColor, swimlane, TextBlockUtils.empty(0, 0), TextBlockUtils.empty(0, 0),
				TextBlockUtils.empty(0, 0), TextBlockUtils.empty(0, 0));
	}

	public FtileDiamondInside withNorth(TextBlock north) {
		return new FtileDiamondInside(label, skinParam(), backColor, borderColor, swimlane, north, south, west, east);
	}

	public FtileDiamondInside withWest(TextBlock west) {
		return new FtileDiamondInside(label, skinParam(), backColor, borderColor, swimlane, north, south, west, east);
	}

	public FtileDiamondInside withEast(TextBlock east) {
		return new FtileDiamondInside(label, skinParam(), backColor, borderColor, swimlane, north, south, west, east);
	}

	public Ftile withWestAndEast(TextBlock tb1, TextBlock tb2) {
		return withWest(tb1).withEast(tb2);
	}

	public FtileDiamondInside withSouth(TextBlock south) {
		return new FtileDiamondInside(label, skinParam(), backColor, borderColor, swimlane, north, south, west, east);
	}

	private FtileDiamondInside(TextBlock label, ISkinParam skinParam, HColor backColor, HColor borderColor,
			Swimlane swimlane, TextBlock north, TextBlock south, TextBlock west, TextBlock east) {
		super(label, skinParam, backColor, borderColor, swimlane, north, south, east, west);
	}

	public void drawU(UGraphic ug) {
		final StringBounder stringBounder = ug.getStringBounder();
		final XDimension2D dimLabel = label.calculateDimension(stringBounder);
		final XDimension2D dimTotal = calculateDimensionAlone(stringBounder);
		ug = ug.apply(borderColor).apply(getThickness(getStyle())).apply(backColor.bg());
		ug.draw(Hexagon.asPolygon(shadowing, dimTotal.getWidth(), dimTotal.getHeight()));

		north.drawU(ug.apply(new UTranslate(4 + dimTotal.getWidth() / 2, dimTotal.getHeight())));
		south.drawU(ug.apply(new UTranslate(4 + dimTotal.getWidth() / 2, dimTotal.getHeight())));

		final double lx = (dimTotal.getWidth() - dimLabel.getWidth()) / 2;
		final double ly = (dimTotal.getHeight() - dimLabel.getHeight()) / 2;
		label.drawU(ug.apply(new UTranslate(lx, ly)));

		final XDimension2D dimWest = west.calculateDimension(stringBounder);
		west.drawU(ug.apply(new UTranslate(-dimWest.getWidth(), -dimWest.getHeight() + dimTotal.getHeight() / 2)));

		final XDimension2D dimEast = east.calculateDimension(stringBounder);
		east.drawU(ug.apply(new UTranslate(dimTotal.getWidth(), -dimEast.getHeight() + dimTotal.getHeight() / 2)));

	}

	private FtileGeometry calculateDimensionAlone(StringBounder stringBounder) {
		final XDimension2D dimLabel = label.calculateDimension(stringBounder);
		final XDimension2D dim;
		if (dimLabel.getWidth() == 0 || dimLabel.getHeight() == 0) {
			dim = new XDimension2D(Hexagon.hexagonHalfSize * 2, Hexagon.hexagonHalfSize * 2);
		} else {
			dim = dimLabel.atLeast(Hexagon.hexagonHalfSize * 2, Hexagon.hexagonHalfSize * 2)
					.delta(Hexagon.hexagonHalfSize * 2, 0);
		}
		return new FtileGeometry(dim, dim.getWidth() / 2, 0, dim.getHeight());
	}

	@Override
	protected FtileGeometry calculateDimensionFtile(StringBounder stringBounder) {
		final FtileGeometry dimDiamonAlone = calculateDimensionAlone(stringBounder);
		final XDimension2D dimWest = west.calculateDimension(stringBounder);
		final XDimension2D dimEast = east.calculateDimension(stringBounder);
		final double northHeight = north.calculateDimension(stringBounder).getHeight();
		return dimDiamonAlone.incHeight(northHeight);
	}

	public double getEastLabelWidth(StringBounder stringBounder) {
		final XDimension2D dimEast = east.calculateDimension(stringBounder);
		return dimEast.getWidth();
	}

	public double getSouthLabelHeight(StringBounder stringBounder) {
		final XDimension2D dimSouth = south.calculateDimension(stringBounder);
		return dimSouth.getHeight();
	}

}

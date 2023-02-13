package net.sourceforge.plantuml.activitydiagram3.ftile;

import net.sourceforge.plantuml.activitydiagram3.ftile.vertical.FtileDecorate;
import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public class FtileMinWidthCentered extends FtileDecorate {

	private final double minWidth;
	private FtileGeometry calculateDimensionInternal;

	public FtileMinWidthCentered(Ftile tile, double minWidth) {
		super(tile);
		this.minWidth = minWidth;
	}

	public void drawU(UGraphic ug) {
		final StringBounder stringBounder = ug.getStringBounder();
		final UTranslate change = getUTranslateInternal(stringBounder);
		super.drawU(ug.apply(change));
	}

	@Override
	public FtileGeometry calculateDimension(StringBounder stringBounder) {
		if (calculateDimensionInternal == null) {
			calculateDimensionInternal = calculateDimensionSlow(stringBounder);
		}
		return calculateDimensionInternal;
	}

	private FtileGeometry calculateDimensionSlow(StringBounder stringBounder) {
		final FtileGeometry geo = super.calculateDimension(stringBounder);
		final double left = getPoint2(geo.getLeft(), stringBounder);
		if (geo.hasPointOut() == false) {
			return new FtileGeometry(getDimensionInternal(stringBounder), left, geo.getInY());
		}
		return new FtileGeometry(getDimensionInternal(stringBounder), left, geo.getInY(), geo.getOutY());
	}

	private XDimension2D getDimensionInternal(StringBounder stringBounder) {
		final XDimension2D dim = getFtileDelegated().calculateDimension(stringBounder);
		if (dim.getWidth() < minWidth) {
			return new XDimension2D(minWidth, dim.getHeight());
		}
		return dim;
	}

	private UTranslate getUTranslateInternal(final StringBounder stringBounder) {
		final XDimension2D dimTile = getFtileDelegated().calculateDimension(stringBounder);
		final XDimension2D dimTotal = getDimensionInternal(stringBounder);
		final UTranslate change = UTranslate.dx((dimTotal.getWidth() - dimTile.getWidth()) / 2);
		return change;
	}

	public UTranslate getTranslateFor(Ftile child, StringBounder stringBounder) {
		if (child == getFtileDelegated()) {
			return getUTranslateInternal(stringBounder);
		}
		return null;
	}

	private double getPoint2(double x, StringBounder stringBounder) {
		final XDimension2D dim = getFtileDelegated().calculateDimension(stringBounder);
		if (dim.getWidth() < minWidth) {
			final double diff = minWidth - dim.getWidth();
			return x + diff / 2;
		}
		return x;
	}

}

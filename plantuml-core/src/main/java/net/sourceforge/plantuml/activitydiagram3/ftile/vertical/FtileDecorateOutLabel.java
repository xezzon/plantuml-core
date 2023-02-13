package net.sourceforge.plantuml.activitydiagram3.ftile.vertical;

import net.sourceforge.plantuml.activitydiagram3.ftile.Ftile;
import net.sourceforge.plantuml.activitydiagram3.ftile.FtileGeometry;
import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public class FtileDecorateOutLabel extends FtileDecorate {

	final private double xl;
	final private double yl;

	public FtileDecorateOutLabel(Ftile ftile, XDimension2D dim) {
		this(ftile, dim.getWidth(), dim.getHeight());
	}

	private FtileDecorateOutLabel(Ftile ftile, double xl, double yl) {
		super(ftile);
		this.xl = xl;
		this.yl = yl;
	}

	@Override
	public FtileGeometry calculateDimension(StringBounder stringBounder) {
		FtileGeometry result = super.calculateDimension(stringBounder);
		result = result.addBottom(yl);
		final double missing = xl - result.getRight();
		if (missing > 0)
			result = result.incRight(missing);

		return result;
	}

	@Override
	public void drawU(UGraphic ug) {
		super.drawU(ug);
	}

}

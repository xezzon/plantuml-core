package net.sourceforge.plantuml.activitydiagram3.ftile.vcompact;

import net.sourceforge.plantuml.activitydiagram3.ftile.Ftile;
import net.sourceforge.plantuml.activitydiagram3.ftile.FtileFactory;
import net.sourceforge.plantuml.activitydiagram3.ftile.FtileFactoryDelegator;
import net.sourceforge.plantuml.activitydiagram3.ftile.FtileGeometry;
import net.sourceforge.plantuml.activitydiagram3.ftile.FtileMargedRight;
import net.sourceforge.plantuml.activitydiagram3.ftile.FtileUtils;
import net.sourceforge.plantuml.awt.geom.XPoint2D;
import net.sourceforge.plantuml.graphic.Rainbow;
import net.sourceforge.plantuml.graphic.TextBlock;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.font.StringBounder;

public class FtileFactoryDelegatorAssembly extends FtileFactoryDelegator {

	public FtileFactoryDelegatorAssembly(FtileFactory factory) {
		super(factory);
	}

	@Override
	public Ftile assembly(final Ftile tile1, final Ftile tile2) {
		double height = 35;
		final TextBlock textBlock = getTextBlock(getInLinkRenderingDisplay(tile2));
		final StringBounder stringBounder = getStringBounder();
		if (textBlock != null) {
			height += textBlock.calculateDimension(stringBounder).getHeight();
		}
		// final Ftile space = new FtileEmpty(getFactory().shadowing(), 1, height);
		final Ftile tile1andSpace = FtileUtils.addBottom(tile1, height);
		Ftile result = super.assembly(tile1andSpace, tile2);
		final FtileGeometry geo = tile1.calculateDimension(stringBounder);
		if (geo.hasPointOut() == false) {
			return result;
		}
		final UTranslate translate1 = result.getTranslateFor(tile1andSpace, stringBounder);
		final XPoint2D p1 = geo.translate(translate1).getPointOut();
		final UTranslate translate2 = result.getTranslateFor(tile2, stringBounder);
		final XPoint2D p2 = tile2.calculateDimension(stringBounder).translate(translate2).getPointIn();

		final Rainbow color = getInLinkRenderingColor(tile2);

		final ConnectionVerticalDown connection = new ConnectionVerticalDown(tile1, tile2, p1, p2, color, textBlock);
		result = FtileUtils.addConnection(result, connection);
		if (textBlock != null) {
			final FtileGeometry dim = result.calculateDimension(stringBounder);
			final double width = dim.getWidth();
			// System.err.println("width=" + width);
			// System.err.println("p1=" + p1);
			// System.err.println("p2=" + p2);
			final double maxX = connection.getMaxX(stringBounder);
			// System.err.println("FtileFactoryDelegatorAssembly dim=" + dim);
			// System.err.println("maxX=" + maxX);
			final double needed = (maxX - width) * 2;
			// result = new FtileMinWidth(result, needed);
			if (width < maxX) {
				result = new FtileMargedRight(result, maxX);
			}
			// System.err.println("FtileFactoryDelegatorAssembly result=" + result.calculateDimension(stringBounder));
		}
		return result;
	}

}

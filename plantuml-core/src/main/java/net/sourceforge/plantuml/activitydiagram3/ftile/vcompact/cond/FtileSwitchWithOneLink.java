package net.sourceforge.plantuml.activitydiagram3.ftile.vcompact.cond;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.plantuml.activitydiagram3.Branch;
import net.sourceforge.plantuml.activitydiagram3.ftile.AbstractConnection;
import net.sourceforge.plantuml.activitydiagram3.ftile.Connection;
import net.sourceforge.plantuml.activitydiagram3.ftile.Ftile;
import net.sourceforge.plantuml.activitydiagram3.ftile.FtileGeometry;
import net.sourceforge.plantuml.activitydiagram3.ftile.FtileUtils;
import net.sourceforge.plantuml.activitydiagram3.ftile.Snake;
import net.sourceforge.plantuml.activitydiagram3.ftile.Swimlane;
import net.sourceforge.plantuml.awt.geom.XPoint2D;
import net.sourceforge.plantuml.graphic.Rainbow;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public class FtileSwitchWithOneLink extends FtileSwitchWithDiamonds {

	private final Rainbow arrowColor;

	public FtileSwitchWithOneLink(List<Ftile> tiles, List<Branch> branches, Swimlane in, Ftile diamond1, Ftile diamond2,
			StringBounder stringBounder, Rainbow arrowColor) {
		super(tiles, branches, in, diamond1, diamond2, stringBounder);
		this.arrowColor = arrowColor;
	}

	class ConnectionVerticalTop extends AbstractConnection {

		private final Branch branch;

		public ConnectionVerticalTop(Ftile tile, Branch branch) {
			super(diamond1, tile);
			this.branch = branch;
		}

		public void drawU(UGraphic ug) {
			final StringBounder stringBounder = ug.getStringBounder();
			final XPoint2D p1 = getP1(stringBounder);
			final XPoint2D p2 = getP2(stringBounder);
			final double x1 = p1.getX();
			final double y1 = p1.getY();
			final double x2 = p2.getX();
			final double y2 = p2.getY();

			final Snake snake = Snake.create(skinParam(), arrowColor, skinParam().arrows().asToDown())
					.withLabel(branch.getTextBlockPositive(), arrowHorizontalAlignment());
			snake.addPoint(x2, y1);
			snake.addPoint(x2, y2);

			ug.draw(snake);
		}

		private XPoint2D getP1(StringBounder stringBounder) {
			final FtileGeometry dimDiamond1 = diamond1.calculateDimension(stringBounder);
			return getTranslateDiamond1(stringBounder).getTranslated(dimDiamond1.getPointC());
		}

		private XPoint2D getP2(final StringBounder stringBounder) {
			return getTranslateOf(getFtile2(), stringBounder)
					.getTranslated(getFtile2().calculateDimension(stringBounder).getPointIn());
		}
	}

	class ConnectionVerticalBottom extends AbstractConnection {

		public ConnectionVerticalBottom(Ftile tile) {
			super(tile, diamond2);
		}

		public void drawU(UGraphic ug) {
			final StringBounder stringBounder = ug.getStringBounder();
			final XPoint2D p1 = getP1(stringBounder);
			final XPoint2D p2 = getP2(stringBounder);
			final double x1 = p1.getX();
			final double y1 = p1.getY();
			final double x2 = p2.getX();
			final double y2 = p2.getY();

			final Snake snake = Snake.create(skinParam(), arrowColor, skinParam().arrows().asToDown());
			snake.addPoint(x2, y1);
			snake.addPoint(x2, y2);

			ug.draw(snake);
		}

		private XPoint2D getP1(StringBounder stringBounder) {
			return getTranslateOf(getFtile1(), stringBounder)
					.getTranslated(getFtile1().calculateDimension(stringBounder).getPointOut());
		}

		private XPoint2D getP2(StringBounder stringBounder) {
			final FtileGeometry dimDiamond2 = diamond2.calculateDimension(stringBounder);
			return getTranslateDiamond2(stringBounder).getTranslated(dimDiamond2.getPointA());
		}
	}

	public Ftile addLinks(StringBounder stringBounder) {
		final List<Connection> conns = new ArrayList<>();
		final Ftile single = tiles.get(0);
		conns.add(new ConnectionVerticalTop(single, branches.get(0)));

		if (single.calculateDimension(stringBounder).hasPointOut())
			conns.add(new ConnectionVerticalBottom(single));

		return FtileUtils.addConnection(this, conns);
	}

}

package net.sourceforge.plantuml.salt.element;

import net.atmp.ISkinSimple;
import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.ULine;
import net.sourceforge.plantuml.klimt.UPath;
import net.sourceforge.plantuml.klimt.URectangle;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.salt.Positionner2;
import net.sourceforge.plantuml.salt.factory.ScrollStrategy;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public class ElementPyramidScrolled extends ElementPyramid {

	private final double v1 = 15;
	private final double v2 = 12;
	private final ScrollStrategy scrollStrategy;

	public ElementPyramidScrolled(Positionner2 positionner, ISkinSimple spriteContainer,
			ScrollStrategy scrollStrategy) {
		super(positionner, TableStrategy.DRAW_OUTSIDE, null, spriteContainer);
		this.scrollStrategy = scrollStrategy;
	}

	@Override
	public XDimension2D getPreferredDimension(StringBounder stringBounder, double x, double y) {
		final XDimension2D result = super.getPreferredDimension(stringBounder, x, y);
		if (scrollStrategy == ScrollStrategy.HORIZONTAL_ONLY)
			return result.delta(0, 30);

		if (scrollStrategy == ScrollStrategy.VERTICAL_ONLY)
			return result.delta(30, 0);

		return result.delta(30);
	}

	@Override
	public void drawU(UGraphic ug, int zIndex, XDimension2D dimToUse) {
		super.drawU(ug, zIndex, dimToUse);
		ug = ug.apply(getBlack());
		final XDimension2D dim = super.getPreferredDimension(ug.getStringBounder(), 0, 0);
		if (scrollStrategy == ScrollStrategy.BOTH || scrollStrategy == ScrollStrategy.VERTICAL_ONLY)
			drawV(ug.apply(UTranslate.dx(dim.getWidth() + 4)), v1, dim.getHeight());

		if (scrollStrategy == ScrollStrategy.BOTH || scrollStrategy == ScrollStrategy.HORIZONTAL_ONLY)
			drawH(ug.apply(UTranslate.dy(dim.getHeight() + 4)), dim.getWidth(), v1);

	}

	private UPath getTr0() {
		final UPath poly = new UPath();
		poly.moveTo(3, 0);
		poly.lineTo(6, 5);
		poly.lineTo(0, 5);
		poly.lineTo(3, 0);
		poly.closePath();
		return poly;
	}

	private UPath getTr180() {
		final UPath poly = new UPath();
		poly.moveTo(3, 5);
		poly.lineTo(6, 0);
		poly.lineTo(0, 0);
		poly.lineTo(3, 5);
		poly.closePath();
		return poly;
	}

	private UPath getTr90() {
		final UPath poly = new UPath();
		poly.moveTo(0, 3);
		poly.lineTo(5, 6);
		poly.lineTo(5, 0);
		poly.lineTo(0, 3);
		poly.closePath();
		return poly;
	}

	private UPath getTr270() {
		final UPath poly = new UPath();
		poly.moveTo(5, 3);
		poly.lineTo(0, 6);
		poly.lineTo(0, 0);
		poly.lineTo(5, 3);
		poly.closePath();
		return poly;
	}

	private void drawV(UGraphic ug, double width, double height) {
		ug.draw(new URectangle(width, height));
		ug.apply(UTranslate.dy(v2)).draw(ULine.hline(width));
		ug.apply(UTranslate.dy(height - v2)).draw(ULine.hline(width));
		ug.apply(new UTranslate(4, 4)).apply(getBlack().bg()).draw(getTr0());
		ug.apply(new UTranslate(4, height - v2 + 4)).apply(getBlack().bg()).draw(getTr180());
	}

	private void drawH(UGraphic ug, double width, double height) {
		ug.draw(new URectangle(width, height));
		ug.apply(UTranslate.dx(v2)).draw(ULine.vline(height));
		ug.apply(UTranslate.dx(width - v2)).draw(ULine.vline(height));
		ug.apply(new UTranslate(4, 4)).apply(getBlack().bg()).draw(getTr90());
		ug.apply(new UTranslate(width - v2 + 4, 4)).apply(getBlack().bg()).draw(getTr270());
	}

}

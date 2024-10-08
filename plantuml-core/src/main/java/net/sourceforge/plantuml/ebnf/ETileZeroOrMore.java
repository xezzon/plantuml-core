// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml.ebnf;

import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.color.HColors;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.shape.URectangle;

public class ETileZeroOrMore extends ETile {

	private final double deltax;
	private final double deltay = 20;
	private final ETile orig;
	private final boolean specialForAlternate;

	public ETileZeroOrMore(ETile orig) {
		this.orig = orig;
		this.specialForAlternate = orig instanceof ETileAlternation;
		this.deltax = this.specialForAlternate ? 0 : 20;
		if (this.specialForAlternate)
			((ETileAlternation) orig).setInZeroOrMore(true);
	}

	@Override
	public double getH1(StringBounder stringBounder) {
		return 10;
	}

	@Override
	public double getH2(StringBounder stringBounder) {
		return 10 + orig.getH1(stringBounder) + orig.getH2(stringBounder);
	}

	@Override
	public double getWidth(StringBounder stringBounder) {
		return orig.getWidth(stringBounder) + 2 * deltax;
	}

	@Override
	public void drawU(UGraphic ug) {
		final StringBounder stringBounder = ug.getStringBounder();
		final XDimension2D fullDim = calculateDimension(stringBounder);
		if (TRACE) {
			if (specialForAlternate)
				ug.apply(HColors.COL_B38D22).draw(URectangle.build(fullDim));
			else
				ug.apply(HColors.GREEN).draw(URectangle.build(fullDim));
		}

		final double linePos = getH1(stringBounder);

		drawHline(ug, linePos, 0, fullDim.getWidth());
		final double corner = 12;

		if (specialForAlternate) {
			CornerCurved.createNE_arrow(corner).drawU(ug.apply(new UTranslate(corner, linePos)));
			CornerCurved.createNW(corner).drawU(ug.apply(new UTranslate(corner, linePos)));

			final double posB = fullDim.getWidth() - corner;
			CornerCurved.createNW_arrow(corner).drawU(ug.apply(new UTranslate(posB, linePos)));
			CornerCurved.createNE(corner).drawU(ug.apply(new UTranslate(posB, linePos)));

		} else {

			CornerCurved.createNE_arrow(corner).drawU(ug.apply(new UTranslate(deltax - corner, linePos)));
			CornerCurved.createNW(corner).drawU(ug.apply(new UTranslate(deltax - corner, linePos)));
			drawVline(ug, deltax - corner, linePos + corner, deltay + orig.getH1(stringBounder) - corner);
			CornerCurved.createSW(corner)
					.drawU(ug.apply(new UTranslate(deltax - corner, deltay + orig.getH1(stringBounder))));

			final double posB = fullDim.getWidth() - deltax + corner;
			CornerCurved.createSE(corner).drawU(ug.apply(new UTranslate(posB, deltay + orig.getH1(stringBounder))));
			drawVline(ug, posB, linePos + corner, deltay + orig.getH1(stringBounder) - corner);
			CornerCurved.createNW_arrow(corner).drawU(ug.apply(new UTranslate(posB, linePos)));
			CornerCurved.createNE(corner).drawU(ug.apply(new UTranslate(posB, linePos)));
		}

		orig.drawU(ug.apply(new UTranslate(deltax, deltay)));
	}

	@Override
	public void push(ETile tile) {
		throw new UnsupportedOperationException();
	}

}

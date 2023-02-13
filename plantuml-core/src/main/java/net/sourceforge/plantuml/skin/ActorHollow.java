package net.sourceforge.plantuml.skin;

import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.graphic.AbstractTextBlock;
import net.sourceforge.plantuml.graphic.SymbolContext;
import net.sourceforge.plantuml.graphic.TextBlock;
import net.sourceforge.plantuml.klimt.UPath;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.ugraphic.UEllipse;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public class ActorHollow extends AbstractTextBlock implements TextBlock {

	private final double headDiam = 9;
	private final double bodyWidth = 25;
	private final double bodyHeight = 21;

	private final double neckHeight = 2;

	private final double armThickness = 5;
	private final double bodyThickness = 6;
	private final double legThickness = 6;

	private final SymbolContext symbolContext;

	public ActorHollow(SymbolContext symbolContext) {
		this.symbolContext = symbolContext;
	}

	public void drawU(UGraphic ug) {

		final UEllipse head = new UEllipse(headDiam, headDiam);
		final double centerX = getPreferredWidth() / 2;

		final UPath path = new UPath();
		path.moveTo(-bodyWidth/2, 0);
		path.lineTo(-bodyWidth / 2, armThickness);
		path.lineTo(-bodyThickness / 2, armThickness);
		path.lineTo(-bodyThickness / 2, bodyHeight - (bodyWidth + legThickness * Math.sqrt(2) - bodyThickness) / 2);
		path.lineTo(-bodyWidth / 2, bodyHeight - legThickness * Math.sqrt(2) / 2);
		path.lineTo(-(bodyWidth / 2 - legThickness * Math.sqrt(2) / 2), bodyHeight);

		path.lineTo(0, bodyHeight - (bodyWidth / 2 - legThickness * Math.sqrt(2) / 2));

		path.lineTo(+(bodyWidth / 2 - legThickness * Math.sqrt(2) / 2), bodyHeight);
		path.lineTo(+bodyWidth / 2, bodyHeight - legThickness * Math.sqrt(2) / 2);
		path.lineTo(+bodyThickness / 2, bodyHeight - (bodyWidth + legThickness * Math.sqrt(2) - bodyThickness) / 2);
		path.lineTo(+bodyThickness / 2, armThickness);
		path.lineTo(+bodyWidth / 2, armThickness);
		path.lineTo(+bodyWidth / 2, 0);
		path.lineTo(-bodyWidth/2, 0);
		path.closePath();

		if (symbolContext.getDeltaShadow() != 0) {
			head.setDeltaShadow(symbolContext.getDeltaShadow());
			path.setDeltaShadow(symbolContext.getDeltaShadow());
		}
		ug = symbolContext.apply(ug);
		ug.apply(new UTranslate(centerX - head.getWidth() / 2, thickness())).draw(head);
		ug.apply(new UTranslate(centerX, head.getHeight() + thickness() + neckHeight)).draw(path);
	}

	private double thickness() {
		return symbolContext.getStroke().getThickness();
	}

	public double getPreferredWidth() {
		return bodyWidth + thickness() * 2;
	}

	public double getPreferredHeight() {
		return headDiam + neckHeight + bodyHeight + thickness() * 2 + symbolContext.getDeltaShadow();
	}

	public XDimension2D calculateDimension(StringBounder stringBounder) {
		return new XDimension2D(getPreferredWidth(), getPreferredHeight());
	}
}

package net.sourceforge.plantuml.graphic;

import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.HorizontalAlignment;
import net.sourceforge.plantuml.ugraphic.UGraphic;

class TextBlockMinWidth extends AbstractTextBlock implements TextBlock {

	private final TextBlock textBlock;
	private final double minWidth;
	private final HorizontalAlignment horizontalAlignment;

	TextBlockMinWidth(TextBlock textBlock, double minWidth, HorizontalAlignment horizontalAlignment) {
		this.textBlock = textBlock;
		this.minWidth = minWidth;
		this.horizontalAlignment = horizontalAlignment;
	}

	public XDimension2D calculateDimension(StringBounder stringBounder) {
		final XDimension2D dim = textBlock.calculateDimension(stringBounder);
		return dim.atLeast(minWidth, 0);
	}

	public void drawU(UGraphic ug) {
		if (horizontalAlignment == HorizontalAlignment.LEFT) {
			textBlock.drawU(ug);
		} else if (horizontalAlignment == HorizontalAlignment.CENTER) {
			final XDimension2D dimText = textBlock.calculateDimension(ug.getStringBounder());
			final XDimension2D dimFull = calculateDimension(ug.getStringBounder());
			final double diffx = dimFull.getWidth() - dimText.getWidth();
			textBlock.drawU(ug.apply(UTranslate.dx(diffx / 2)));
		} else if (horizontalAlignment == HorizontalAlignment.RIGHT) {
			final XDimension2D dimText = textBlock.calculateDimension(ug.getStringBounder());
			final XDimension2D dimFull = calculateDimension(ug.getStringBounder());
			final double diffx = dimFull.getWidth() - dimText.getWidth();
			textBlock.drawU(ug.apply(UTranslate.dx(diffx)));
		} else {
			throw new UnsupportedOperationException();
		}
	}
}

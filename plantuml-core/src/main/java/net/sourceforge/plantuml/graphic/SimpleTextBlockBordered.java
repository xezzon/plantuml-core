package net.sourceforge.plantuml.graphic;

import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.URectangle;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.ugraphic.UGraphic;

class SimpleTextBlockBordered extends AbstractTextBlock implements TextBlock {

	private final TextBlock textBlock;
	private final HColor color;

	public SimpleTextBlockBordered(TextBlock textBlock, HColor color) {
		this.textBlock = textBlock;
		this.color = color;
	}

	public XDimension2D calculateDimension(StringBounder stringBounder) {
		final XDimension2D dim = textBlock.calculateDimension(stringBounder);
		return dim.delta(1, 1);
	}

	public void drawU(UGraphic ug) {
		final XDimension2D dim = textBlock.calculateDimension(ug.getStringBounder());
		textBlock.drawU(ug.apply(new UTranslate(1, 1)));
		ug.apply(color).draw(new URectangle(dim.getWidth(), dim.getHeight()));
	}
}

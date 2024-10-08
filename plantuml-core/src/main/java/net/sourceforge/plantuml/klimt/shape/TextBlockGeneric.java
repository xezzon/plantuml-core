// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml.klimt.shape;

import net.sourceforge.plantuml.klimt.UStroke;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;

public class TextBlockGeneric extends AbstractTextBlock implements TextBlock {

	private final TextBlock textBlock;
	private final HColor background;
	private final HColor border;

	public TextBlockGeneric(TextBlock textBlock, HColor background, HColor border) {
		this.textBlock = textBlock;
		this.border = border;
		this.background = background;
	}

	public XDimension2D calculateDimension(StringBounder stringBounder) {
		final XDimension2D dim = textBlock.calculateDimension(stringBounder);
		return dim;
	}

	public void drawU(UGraphic ug) {
		ug = ug.apply(background.bg());
		ug = ug.apply(border);
		final XDimension2D dim = calculateDimension(ug.getStringBounder());
		ug.apply(new UStroke(2, 2, 1)).draw(URectangle.build(dim.getWidth(), dim.getHeight()));

		textBlock.drawU(ug);
	}

}

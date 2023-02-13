package net.sourceforge.plantuml.graphic;

import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.awt.geom.XRectangle2D;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.color.HColors;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.svek.Ports;
import net.sourceforge.plantuml.svek.WithPorts;
import net.sourceforge.plantuml.ugraphic.UGraphic;
import net.sourceforge.plantuml.ugraphic.UHorizontalLine;

public class TextBlockLineBefore extends AbstractTextBlock implements TextBlock, WithPorts {

	private final TextBlock textBlock;
	private final char separator;
	private final TextBlock title;
	private final double defaultThickness;

	public TextBlockLineBefore(double defaultThickness, TextBlock textBlock, char separator, TextBlock title) {
		this.textBlock = textBlock;
		this.separator = separator;
		this.title = title;
		this.defaultThickness = defaultThickness;
	}

	public TextBlockLineBefore(double defaultThickness, TextBlock textBlock, char separator) {
		this(defaultThickness, textBlock, separator, null);
	}

	public TextBlockLineBefore(double defaultThickness, TextBlock textBlock) {
		this(defaultThickness, textBlock, '\0');
	}

	public XDimension2D calculateDimension(StringBounder stringBounder) {
		final XDimension2D dim = textBlock.calculateDimension(stringBounder);
		if (title != null) {
			final XDimension2D dimTitle = title.calculateDimension(stringBounder);
			return dim.atLeast(dimTitle.getWidth() + 8, dimTitle.getHeight());
		}
		return dim;
	}

	public void drawU(UGraphic ug) {
		final HColor color = ug.getParam().getColor();
		if (title == null)
			UHorizontalLine.infinite(defaultThickness, 1, 1, separator).drawMe(ug);

		textBlock.drawU(ug);
		if (color == null)
			ug = ug.apply(HColors.none());
		else
			ug = ug.apply(color);

		if (title != null)
			UHorizontalLine.infinite(defaultThickness, 1, 1, title, separator).drawMe(ug);

	}

	@Override
	public XRectangle2D getInnerPosition(String member, StringBounder stringBounder, InnerStrategy strategy) {
		return textBlock.getInnerPosition(member, stringBounder, strategy);
	}

	@Override
	public Ports getPorts(StringBounder stringBounder) {
		if (textBlock instanceof WithPorts)
			return ((WithPorts) textBlock).getPorts(stringBounder);
		return new Ports();
	}

}

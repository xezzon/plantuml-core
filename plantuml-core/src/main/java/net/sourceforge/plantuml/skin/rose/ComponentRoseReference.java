// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml.skin.rose;

import net.sourceforge.plantuml.klimt.Fashion;
import net.sourceforge.plantuml.klimt.LineBreakStrategy;
import net.sourceforge.plantuml.klimt.UPath;
import net.sourceforge.plantuml.klimt.UStroke;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.creole.Display;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.FontConfiguration;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.HorizontalAlignment;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.shape.TextBlock;
import net.sourceforge.plantuml.klimt.shape.URectangle;
import net.sourceforge.plantuml.skin.AbstractTextualComponent;
import net.sourceforge.plantuml.skin.Area;
import net.sourceforge.plantuml.style.ISkinSimple;
import net.sourceforge.plantuml.style.PName;
import net.sourceforge.plantuml.style.Style;

public class ComponentRoseReference extends AbstractTextualComponent {

	private final int cornersize = 10;
	private final TextBlock textHeader;
	private final double heightFooter = 5;
	private final double xMargin = 2;
	private final HorizontalAlignment position;
	private final Fashion symbolContextHeader;
	private final Fashion symbolContextBody;
	private int roundCorner;

	public ComponentRoseReference(Style style, Style styleHeader, Display stringsToDisplay, ISkinSimple spriteContainer,
			HColor background) {
		super(style, LineBreakStrategy.NONE, 4, 4, 4, spriteContainer,
				stringsToDisplay.subList(1, stringsToDisplay.size()), false);

		this.symbolContextHeader = styleHeader.getSymbolContext(getIHtmlColorSet());
		this.symbolContextBody = style.getSymbolContext(getIHtmlColorSet());
		this.roundCorner = style.value(PName.RoundCorner).asInt(false);
		final FontConfiguration fcHeader = styleHeader.getFontConfiguration(getIHtmlColorSet());
		this.position = style.getHorizontalAlignment();

		this.textHeader = stringsToDisplay.subList(0, 1).create(fcHeader, HorizontalAlignment.LEFT, spriteContainer);

	}

	@Override
	protected void drawInternalU(UGraphic ug, Area area) {
		final XDimension2D dimensionToUse = area.getDimensionToUse();
		final StringBounder stringBounder = ug.getStringBounder();
		final int textHeaderWidth = (int) (getHeaderWidth(stringBounder));
		final int textHeaderHeight = (int) (getHeaderHeight(stringBounder));

		URectangle rect = URectangle.build(dimensionToUse.getWidth() - xMargin * 2 - symbolContextBody.getDeltaShadow(),
				dimensionToUse.getHeight() - heightFooter);
		if (this.roundCorner != 0)
			rect = rect.rounded(this.roundCorner);

		rect.setDeltaShadow(symbolContextBody.getDeltaShadow());
		ug = symbolContextBody.apply(ug);
		ug.apply(UTranslate.dx(xMargin)).draw(rect);

		final UPath corner = UPath.none();
		if (this.roundCorner == 0) {
			corner.moveTo(0, 0);
			corner.lineTo(textHeaderWidth, 0);

			corner.lineTo(textHeaderWidth, textHeaderHeight - cornersize);
			corner.lineTo(textHeaderWidth - cornersize, textHeaderHeight);

			corner.lineTo(0, textHeaderHeight);
			corner.lineTo(0, 0);
		} else {
			corner.moveTo(this.roundCorner / 2, 0);
			corner.lineTo(textHeaderWidth, 0);

			corner.lineTo(textHeaderWidth, textHeaderHeight - cornersize);
			corner.lineTo(textHeaderWidth - cornersize, textHeaderHeight);

			corner.lineTo(0, textHeaderHeight);
			corner.lineTo(0, this.roundCorner / 2);

			corner.arcTo(this.roundCorner / 2, this.roundCorner / 2, 0, 0, 1, this.roundCorner / 2, 0);
		}

		ug = symbolContextHeader.apply(ug);
		ug.apply(UTranslate.dx(xMargin)).draw(corner);

		ug = ug.apply(UStroke.simple());

		textHeader.drawU(ug.apply(new UTranslate(15, 2)));
		final double textPos;
		if (position == HorizontalAlignment.CENTER) {
			final double textWidth = getTextBlock().calculateDimension(stringBounder).getWidth();
			textPos = (dimensionToUse.getWidth() - textWidth) / 2;
		} else if (position == HorizontalAlignment.RIGHT) {
			final double textWidth = getTextBlock().calculateDimension(stringBounder).getWidth();
			textPos = dimensionToUse.getWidth() - textWidth - getMarginX2() - xMargin;
		} else {
			textPos = getMarginX1() + xMargin;
		}
		getTextBlock().drawU(ug.apply(new UTranslate(textPos, (getMarginY() + textHeaderHeight))));
	}

	private double getHeaderHeight(StringBounder stringBounder) {
		final XDimension2D headerDim = textHeader.calculateDimension(stringBounder);
		return headerDim.getHeight() + 2 * 1;
	}

	private double getHeaderWidth(StringBounder stringBounder) {
		final XDimension2D headerDim = textHeader.calculateDimension(stringBounder);
		return headerDim.getWidth() + 30 + 15;
	}

	@Override
	public double getPreferredHeight(StringBounder stringBounder) {
		return getTextHeight(stringBounder) + getHeaderHeight(stringBounder) + heightFooter;
	}

	@Override
	public double getPreferredWidth(StringBounder stringBounder) {
		return Math.max(getTextWidth(stringBounder), getHeaderWidth(stringBounder)) + xMargin * 2
				+ symbolContextBody.getDeltaShadow();
	}

}

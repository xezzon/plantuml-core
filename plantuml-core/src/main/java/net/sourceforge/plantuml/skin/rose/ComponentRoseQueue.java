// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml.skin.rose;

import net.sourceforge.plantuml.decoration.symbol.USymbols;
import net.sourceforge.plantuml.klimt.Fashion;
import net.sourceforge.plantuml.klimt.LineBreakStrategy;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.creole.Display;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.HorizontalAlignment;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.shape.TextBlock;
import net.sourceforge.plantuml.klimt.shape.TextBlockUtils;
import net.sourceforge.plantuml.skin.AbstractTextualComponent;
import net.sourceforge.plantuml.skin.Area;
import net.sourceforge.plantuml.style.ISkinSimple;
import net.sourceforge.plantuml.style.Style;

public class ComponentRoseQueue extends AbstractTextualComponent {

	private final TextBlock stickman;
	private final boolean head;

	public ComponentRoseQueue(Style style, Style stereo, Display stringsToDisplay, boolean head,
			ISkinSimple spriteContainer) {
		super(style, stereo, LineBreakStrategy.NONE, 3, 3, 0, spriteContainer, stringsToDisplay, false);

		final Fashion biColor = style.getSymbolContext(getIHtmlColorSet());

		this.head = head;
		this.stickman = USymbols.QUEUE.asSmall(TextBlockUtils.empty(0, 0), getTextBlock(), TextBlockUtils.empty(0, 0),
				biColor, HorizontalAlignment.CENTER);
	}

	@Override
	protected void drawInternalU(UGraphic ug, Area area) {
		final StringBounder stringBounder = ug.getStringBounder();
		final XDimension2D dimStickman = stickman.calculateDimension(stringBounder);
		final double delta = (getPreferredWidth(stringBounder) - dimStickman.getWidth()) / 2;

		ug = ug.apply(UTranslate.dx(delta));
		stickman.drawU(ug);
	}

	@Override
	public double getPreferredHeight(StringBounder stringBounder) {
		final XDimension2D dimStickman = stickman.calculateDimension(stringBounder);
		return dimStickman.getHeight();
	}

	@Override
	public double getPreferredWidth(StringBounder stringBounder) {
		final XDimension2D dimStickman = stickman.calculateDimension(stringBounder);
		return dimStickman.getWidth();
	}

}

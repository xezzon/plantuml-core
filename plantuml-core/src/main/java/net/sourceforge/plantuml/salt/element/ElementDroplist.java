package net.sourceforge.plantuml.salt.element;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import net.atmp.ISkinSimple;
import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.cucadiagram.Display;
import net.sourceforge.plantuml.graphic.TextBlock;
import net.sourceforge.plantuml.klimt.ULine;
import net.sourceforge.plantuml.klimt.UPolygon;
import net.sourceforge.plantuml.klimt.URectangle;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.color.HColors;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.font.UFont;
import net.sourceforge.plantuml.klimt.geom.HorizontalAlignment;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public class ElementDroplist extends AbstractElementText implements Element {

	private final int box = 12;
	private final TextBlock openDrop;

	public ElementDroplist(String text, UFont font, ISkinSimple spriteContainer) {
		super(extract(text), font, true, spriteContainer);
		final StringTokenizer st = new StringTokenizer(text, "^");
		final List<String> drop = new ArrayList<>();
		while (st.hasMoreTokens()) {
			drop.add(st.nextToken());
		}
		if (drop.size() > 0) {
			drop.remove(0);
		}
		if (drop.size() == 0) {
			this.openDrop = null;
		} else {
			this.openDrop = Display.create(drop).create(getConfig(), HorizontalAlignment.LEFT, spriteContainer);
		}
	}

	private static String extract(String text) {
		final int idx = text.indexOf('^');
		if (idx == -1) {
			return text;
		}
		return text.substring(0, idx);
	}

	public XDimension2D getPreferredDimension(StringBounder stringBounder, double x, double y) {
		final XDimension2D dim = getTextDimensionAt(stringBounder, x + 2);
		return dim.delta((4 + box), 4);
	}

	public void drawU(UGraphic ug, int zIndex, XDimension2D dimToUse) {
		final XDimension2D dim = getPreferredDimension(ug.getStringBounder(), 0, 0);
		ug = ug.apply(getBlack());

		if (zIndex == 0) {
			ug.apply(getColorEE().bg()).draw(new URectangle(dim.getWidth() - 1, dim.getHeight() - 1));
			drawText(ug, 2, 2);
			final double xline = dim.getWidth() - box;
			ug.apply(UTranslate.dx(xline)).draw(ULine.vline(dim.getHeight() - 1));

			final UPolygon poly = new UPolygon();
			poly.addPoint(0, 0);
			poly.addPoint(box - 6, 0);
			final XDimension2D dimText = getPureTextDimension(ug.getStringBounder());
			poly.addPoint((box - 6) / 2, dimText.getHeight() - 8);

			ug.apply(HColors.changeBack(ug)).apply(new UTranslate(xline + 3, 6)).draw(poly);
		}

		if (openDrop != null) {
			final XDimension2D dimOpen = openDrop.calculateDimension(ug.getStringBounder()).atLeast(dim.getWidth() - 1,
					0);
			ug = ug.apply(UTranslate.dy(dim.getHeight() - 1));
			ug.apply(getColorEE().bg()).draw(new URectangle(dimOpen.getWidth() - 1, dimOpen.getHeight() - 1));
			openDrop.drawU(ug);
		}
	}
}

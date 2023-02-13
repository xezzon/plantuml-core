package net.sourceforge.plantuml.sequencediagram.teoz;

import net.sourceforge.plantuml.ISkinParam;
import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.graphic.UDrawable;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.sequencediagram.AbstractMessage;
import net.sourceforge.plantuml.sequencediagram.Note;
import net.sourceforge.plantuml.skin.Area;
import net.sourceforge.plantuml.skin.Component;
import net.sourceforge.plantuml.skin.Context2D;
import net.sourceforge.plantuml.skin.rose.Rose;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public class CommunicationTileNoteBottom extends CommunicationTileNoteBottomTopAbstract {

	public CommunicationTileNoteBottom(Tile tile, AbstractMessage message, Rose skin, ISkinParam skinParam,
			Note noteOnMessage, YGauge currentY) {
		super(tile, message, skin, skinParam, noteOnMessage, currentY);
	}

	@Override
	public void drawU(UGraphic ug) {
		final StringBounder stringBounder = ug.getStringBounder();
		final Component comp = getComponent(stringBounder);
		final XDimension2D dim = comp.getPreferredDimension(stringBounder);
		final Area area = Area.create(dim.getWidth(), dim.getHeight());
		((UDrawable) tile).drawU(ug);

		if (YGauge.USE_ME)
			ug = ug.apply(UTranslate.dy(getYGauge().getMin().getCurrentValue()));

		final double middleMsg = (tile.getMinX().getCurrentValue() + tile.getMaxX().getCurrentValue()) / 2;

		final double xNote = getNotePosition(stringBounder).getCurrentValue();
		final double yNote = tile.getPreferredHeight();

		comp.drawU(ug.apply(new UTranslate(xNote, yNote + spacey)), area, (Context2D) ug);

		drawLine(ug, middleMsg, tile.getContactPointRelative(), xNote + dim.getWidth() / 2,
				yNote + spacey + Rose.paddingY);

	}

}

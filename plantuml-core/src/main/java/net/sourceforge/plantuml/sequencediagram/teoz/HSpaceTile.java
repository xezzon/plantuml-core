package net.sourceforge.plantuml.sequencediagram.teoz;

import net.sourceforge.plantuml.real.Real;
import net.sourceforge.plantuml.sequencediagram.Event;
import net.sourceforge.plantuml.sequencediagram.HSpace;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public class HSpaceTile extends AbstractTile implements Tile {

	private final HSpace hspace;
	private final Real xorigin;
	private final YGauge yGauge;

	public Event getEvent() {
		return hspace;
	}

	public HSpaceTile(HSpace hspace, TileArguments tileArguments, YGauge currentY) {
		super(tileArguments.getStringBounder(), currentY);
		this.hspace = hspace;
		this.xorigin = tileArguments.getXOrigin();
		this.yGauge = YGauge.create(currentY.getMax(), getPreferredHeight());
	}

	@Override
	public YGauge getYGauge() {
		return yGauge;
	}


	public void drawU(UGraphic ug) {
	}

	public double getPreferredHeight() {
		return hspace.getPixel();
	}

	public void addConstraints() {
	}

	public Real getMinX() {
		return xorigin;
	}

	public Real getMaxX() {
		return xorigin.addFixed(10);
	}

}

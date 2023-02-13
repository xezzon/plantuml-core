package net.sourceforge.plantuml.klimt;

import java.awt.Graphics2D;
import java.awt.RenderingHints;

public enum UAntiAliasing implements UChange {

	ANTI_ALIASING_ON, ANTI_ALIASING_OFF;

	public void apply(Graphics2D g2d) {
		if (this == ANTI_ALIASING_ON) {
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		} else {
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
		}

	}

}

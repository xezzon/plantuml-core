// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml.sequencediagram.teoz;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.drawing.UGraphic;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.klimt.geom.HorizontalAlignment;
import net.sourceforge.plantuml.klimt.geom.VerticalAlignment;
import net.sourceforge.plantuml.klimt.geom.XDimension2D;
import net.sourceforge.plantuml.real.Real;
import net.sourceforge.plantuml.sequencediagram.Participant;
import net.sourceforge.plantuml.skin.Context2D;

public class LivingSpaces {

	private final Map<Participant, LivingSpace> all = new LinkedHashMap<Participant, LivingSpace>();

	public Collection<LivingSpace> values() {
		return all.values();
	}

	public void addConstraints(StringBounder stringBounder) {
		LivingSpace previous = null;
		for (LivingSpace current : all.values()) {
			if (previous != null) {
				final Real point1 = previous.getPosE(stringBounder);
				final Real point2 = current.getPosA(stringBounder);
				point2.ensureBiggerThan(point1.addFixed(10));
			}
			previous = current;
		}
	}

	public LivingSpace previous(LivingSpace element) {
		LivingSpace previous = null;
		for (LivingSpace current : all.values()) {
			if (current == element) {
				return previous;
			}
			previous = current;
		}
		return null;
	}

	public LivingSpace next(LivingSpace element) {
		for (Iterator<LivingSpace> it = all.values().iterator(); it.hasNext();) {
			final LivingSpace current = it.next();
			if (current == element && it.hasNext()) {
				return it.next();
			}
		}
		return null;

	}

	public Collection<Participant> participants() {
		return all.keySet();
	}

	public void put(Participant participant, LivingSpace livingSpace) {
		all.put(participant, livingSpace);
	}

	public LivingSpace get(Participant participant) {
		return all.get(participant);
	}

	public void drawHeads(final UGraphic ug, Context2D context, VerticalAlignment verticalAlignment) {
		final StringBounder stringBounder = ug.getStringBounder();
		final double headHeight = getHeadHeight(stringBounder);
		for (LivingSpace livingSpace : values()) {
			final double x = livingSpace.getPosB(stringBounder).getCurrentValue();
			double y = 0;
			if (verticalAlignment == VerticalAlignment.BOTTOM) {
				final XDimension2D dimHead = livingSpace.getHeadPreferredDimension(stringBounder);
				y = headHeight - dimHead.getHeight();
			}
			livingSpace.drawHead(ug.apply(new UTranslate(x, y)), context, verticalAlignment, HorizontalAlignment.LEFT);
		}
	}

	public double getHeadHeight(StringBounder stringBounder) {
		double headHeight = 0;
		for (LivingSpace livingSpace : values()) {
			final XDimension2D headDim = livingSpace.getHeadPreferredDimension(stringBounder);
			headHeight = Math.max(headHeight, headDim.getHeight());
		}
		return headHeight;
	}

	public void drawLifeLines(final UGraphic ug, double height, Context2D context) {
		int i = 0;
		for (LivingSpace livingSpace : values()) {
			// if (i++ == 0) {
			// System.err.println("TEMPORARY SKIPPING OTHERS");
			// continue;
			// }
			// System.err.println("drawing lines " + livingSpace);
			final double x = livingSpace.getPosC(ug.getStringBounder()).getCurrentValue();
			livingSpace.drawLineAndLiveboxes(ug.apply(UTranslate.dx(x)), height, context);
		}
	}

	public void delayOn(double y, double height) {
		for (LivingSpace livingSpace : values())
			livingSpace.delayOn(y, height);

	}

	public int size() {
		return all.size();
	}

}

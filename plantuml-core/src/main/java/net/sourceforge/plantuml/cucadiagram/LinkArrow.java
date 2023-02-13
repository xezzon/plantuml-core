package net.sourceforge.plantuml.cucadiagram;

import net.sourceforge.plantuml.svek.GuideLine;
import net.sourceforge.plantuml.utils.Direction;

public enum LinkArrow {

	NONE_OR_SEVERAL, DIRECT_NORMAL, BACKWARD;

	public LinkArrow reverse() {
		if (this == DIRECT_NORMAL) {
			return BACKWARD;
		}
		if (this == BACKWARD) {
			return DIRECT_NORMAL;
		}
		return NONE_OR_SEVERAL;
	}

	public GuideLine mute(final GuideLine guide) {
		switch (this) {
		case DIRECT_NORMAL:
			return guide;
		case BACKWARD:
			return new GuideLine() {
				public Direction getArrowDirection() {
					return guide.getArrowDirection().getInv();
				}

				public double getArrowDirection2() {
					return Math.PI + guide.getArrowDirection2();
				}
			};

		}
		throw new UnsupportedOperationException();
	}

}

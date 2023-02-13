package net.sourceforge.plantuml.sequencediagram.graphic;

import java.util.Objects;

import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.sequencediagram.InGroupable;
import net.sourceforge.plantuml.sequencediagram.NotePosition;
import net.sourceforge.plantuml.skin.Area;
import net.sourceforge.plantuml.skin.Component;
import net.sourceforge.plantuml.skin.Context2D;
import net.sourceforge.plantuml.ugraphic.UGraphic;
import net.sourceforge.plantuml.url.Url;

final class NoteBox extends GraphicalElement implements InGroupable {

	private final NotePosition position;
	private final Url url;

	private final LivingParticipantBox p1;
	private final LivingParticipantBox p2;

	private final Component comp;

	private double delta = 0;

	public NoteBox(double startingY, Component comp, LivingParticipantBox p1, LivingParticipantBox p2,
			NotePosition position, Url url) {
		super(startingY);
		if (p2 != null ^ position == NotePosition.OVER_SEVERAL) {
			throw new IllegalArgumentException();
		}
		this.p1 = Objects.requireNonNull(p1);
		this.p2 = p2;
		this.position = position;
		this.url = url;
		this.comp = comp;
	}

	public double getRightShift(double y) {
		if (p1 == null) {
			return 0;
		}
		return p1.getLifeLine().getRightShift(y) + 5;
	}

	@Override
	final public double getPreferredWidth(StringBounder stringBounder) {
		final double preferredWidth = comp.getPreferredWidth(stringBounder);
		if (position == NotePosition.OVER_SEVERAL) {
			assert p1 != p2;
			final double diff1 = p2.getParticipantBox().getMaxX(stringBounder) - p1.getParticipantBox().getMinX();
			if (diff1 > preferredWidth) {
				return diff1;
			}

		}
		return preferredWidth;
	}

	@Override
	final public double getPreferredHeight(StringBounder stringBounder) {
		return comp.getPreferredHeight(stringBounder);
	}

	@Override
	protected void drawInternalU(UGraphic ug, double maxX, Context2D context) {
		final StringBounder stringBounder = ug.getStringBounder();
		final double xStart = getStartingX(stringBounder);
		ug = ug.apply(new UTranslate(xStart, getStartingY()));
		final XDimension2D dimensionToUse = new XDimension2D(getPreferredWidth(stringBounder),
				comp.getPreferredHeight(stringBounder));
		if (url != null) {
			ug.startUrl(url);
		}
		comp.drawU(ug, new Area(dimensionToUse), context);
		if (url != null) {
			ug.closeUrl();
		}
	}

	@Override
	public double getStartingX(StringBounder stringBounder) {
		final SegmentColored segment = getSegment(stringBounder);
		final int xStart;
		if (position == NotePosition.LEFT) {
			xStart = (int) (segment.getSegment().getPos1() - getPreferredWidth(stringBounder));
		} else if (position == NotePosition.RIGHT) {
			xStart = (int) (segment.getSegment().getPos2());
		} else if (position == NotePosition.OVER) {
			xStart = (int) (p1.getParticipantBox().getCenterX(stringBounder) - getPreferredWidth(stringBounder) / 2);
		} else if (position == NotePosition.OVER_SEVERAL) {
			final double centre = (p1.getParticipantBox().getCenterX(stringBounder) + p2.getParticipantBox()
					.getCenterX(stringBounder)) / 2.0;
			xStart = (int) (centre - getPreferredWidth(stringBounder) / 2.0);
		} else {
			throw new IllegalStateException();
		}
		return xStart + delta;
	}

	private SegmentColored getSegment(StringBounder stringBounder) {
		final SegmentColored segment = p1.getLiveThicknessAt(stringBounder, getStartingY());
		final SegmentColored segment2 = p1.getLiveThicknessAt(stringBounder,
				getStartingY() + comp.getPreferredHeight(stringBounder));
		return segment.merge(segment2);
	}

	public void pushToRight(double x) {
		this.delta += x;
	}

	public double getMaxX(StringBounder stringBounder) {
		return getStartingX(stringBounder) + getPreferredWidth(stringBounder);
	}

	public double getMinX(StringBounder stringBounder) {
		return getStartingX(stringBounder);
	}

	public String toString(StringBounder stringBounder) {
		return toString();
	}

	public final Url getUrl() {
		return url;
	}

	public NotePosition getNotePosition() {
		return position;
	}

}

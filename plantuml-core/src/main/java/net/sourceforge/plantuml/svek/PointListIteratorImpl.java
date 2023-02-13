package net.sourceforge.plantuml.svek;

import java.util.Collections;
import java.util.List;

import net.sourceforge.plantuml.awt.geom.XPoint2D;
import net.sourceforge.plantuml.utils.Log;

class PointListIteratorImpl implements PointListIterator {

	private final SvgResult svg;
	private int pos = 0;

	static PointListIterator create(SvgResult svg, int lineColor) {
		final PointListIteratorImpl result = new PointListIteratorImpl(svg);
		final int idx = svg.getIndexFromColor(lineColor);
		if (idx == -1) {
			result.pos = -1;
		}
		return result;
	}

	public PointListIterator cloneMe() {
		final PointListIteratorImpl result = new PointListIteratorImpl(svg);
		result.pos = this.pos;
		return result;
	}

	private PointListIteratorImpl(SvgResult svg) {
		this.svg = svg;
	}

	public boolean hasNext() {
		return true;
	}

	public List<XPoint2D> next() {
		if (pos == -1) {
			return Collections.emptyList();
		}
		try {
			final List<XPoint2D> result = svg.substring(pos).extractList(SvgResult.POINTS_EQUALS);
			pos = svg.indexOf(SvgResult.POINTS_EQUALS, pos) + SvgResult.POINTS_EQUALS.length() + 1;
			return result;
		} catch (StringIndexOutOfBoundsException e) {
			Log.error("Error " + e);
			return Collections.emptyList();
		}
	}

	public void remove() {
		throw new UnsupportedOperationException();
	}

}

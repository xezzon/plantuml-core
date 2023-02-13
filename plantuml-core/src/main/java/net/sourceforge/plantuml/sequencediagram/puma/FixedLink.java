package net.sourceforge.plantuml.sequencediagram.puma;

public class FixedLink {

	final private SegmentPosition segmentPosition1;
	final private SegmentPosition segmentPosition2;

	public FixedLink(SegmentPosition segmentPosition1, SegmentPosition segmentPosition2) {
		this.segmentPosition1 = segmentPosition1;
		this.segmentPosition2 = segmentPosition2;
	}

	public boolean pushIfNeed() {
		final double p1 = segmentPosition1.getPosition();
		final double p2 = segmentPosition2.getPosition();
		if (p1 == p2) {
			return false;
		}
		final double diff = p1 - p2;
		segmentPosition2.getSegment().push(diff);
		assert segmentPosition1.getPosition() == segmentPosition2.getPosition();
		return true;
	}

}

package net.sourceforge.plantuml.sequencediagram.puma;

public class SegmentPosition {

	final private PSegment segment;
	final private double position;

	public SegmentPosition(PSegment segment, double position) {
		this.segment = segment;
		this.position = position;
	}

	public double getPosition() {
		return segment.getPosition(position);
	}

	public PSegment getSegment() {
		return segment;
		
	}

}

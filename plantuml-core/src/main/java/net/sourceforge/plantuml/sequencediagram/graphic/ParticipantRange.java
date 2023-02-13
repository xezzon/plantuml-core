package net.sourceforge.plantuml.sequencediagram.graphic;

class ParticipantRange {

	private final int start;
	private final int end;

	public ParticipantRange(int start, int end) {
		if (start > end) {
			throw new IllegalArgumentException();
		}
		this.start = start;
		this.end = end;
	}

	public int start() {
		return start;
	}

	public int end() {
		return end;
	}

	public ParticipantRange merge(ParticipantRange other) {
		return new ParticipantRange(this.start, other.end);
	}

}

package net.sourceforge.plantuml.ugraphic.comp;

public class Slot implements Comparable<Slot> {

	private final double start;
	private final double end;

	public Slot(double start, double end) {
		if (start >= end) {
			throw new IllegalArgumentException();
		}
		this.start = start;
		this.end = end;
	}

	@Override
	public String toString() {
		return "(" + start + "," + end + ")";
	}

	public double getStart() {
		return start;
	}

	public double getEnd() {
		return end;
	}

	public double size() {
		return end - start;
	}

	public boolean contains(double v) {
		return v >= start && v <= end;
	}

	public boolean intersect(Slot other) {
		return contains(other.start) || contains(other.end) || other.contains(start) || other.contains(end);
	}

	public Slot merge(Slot other) {
		return new Slot(Math.min(start, other.start), Math.max(end, other.end));
	}

	public Slot intersect(double otherStart, double otherEnd) {
		if (otherStart >= end) {
			return null;
		}
		if (otherEnd <= start) {
			return null;
		}
		return new Slot(Math.max(start, otherStart), Math.min(end, otherEnd));
	}

	public int compareTo(Slot other) {
		if (this.start < other.start) {
			return -1;
		}
		if (this.start > other.start) {
			return 1;
		}
		return 0;
	}

}

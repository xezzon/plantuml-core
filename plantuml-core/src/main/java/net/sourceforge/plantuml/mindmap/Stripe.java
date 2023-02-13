package net.sourceforge.plantuml.mindmap;

public class Stripe implements Comparable<Stripe> {

	private final double x1;
	private final double x2;
	private final double value;

	@Override
	public String toString() {
		return "" + (int) x1 + "->" + (int) x2 + " (" + (int) value + ")";
	}

	public Stripe(double x1, double x2, double value) {
		if (x2 <= x1) {
			System.err.println("x1=" + x1);
			System.err.println("x2=" + x2);
			throw new IllegalArgumentException();
		}
		this.x1 = x1;
		this.x2 = x2;
		this.value = value;
	}

	public boolean contains(double x) {
		return x >= x1 && x <= x2;
	}

	public int compareTo(Stripe other) {
		return (int) Math.signum(this.x1 - other.x1);
	}

	public double getValue() {
		return value;
	}

	public final double getStart() {
		return x1;
	}

	public final double getEnd() {
		return x2;
	}

}

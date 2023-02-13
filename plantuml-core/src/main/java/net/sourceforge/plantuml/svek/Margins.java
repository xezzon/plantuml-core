package net.sourceforge.plantuml.svek;

public class Margins {

	private final double x1;
	private final double x2;
	private final double y1;
	private final double y2;

	static public Margins NONE = new Margins(0, 0, 0, 0);

	public static Margins uniform(double value) {
		return new Margins(value, value, value, value);
	}

	@Override
	public String toString() {
		return "MARGIN[" + x1 + "," + x2 + "," + y1 + "," + y2 + "]";
	}

	public Margins merge(Margins other) {
		return new Margins(//
				Math.max(this.x1, other.x1), //
				Math.max(this.x2, other.x2), //
				Math.max(this.y1, other.y1), //
				Math.max(this.y2, other.y2));
	}

	public Margins(double x1, double x2, double y1, double y2) {
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
	}

	public boolean isZero() {
		return x1 == 0 && x2 == 0 && y1 == 0 && y2 == 0;
	}

	public final double getX1() {
		return x1;
	}

	public final double getX2() {
		return x2;
	}

	public final double getY1() {
		return y1;
	}

	public final double getY2() {
		return y2;
	}

	public double getTotalWidth() {
		return x1 + x2;
	}

	public double getTotalHeight() {
		return y1 + y2;
	}

}

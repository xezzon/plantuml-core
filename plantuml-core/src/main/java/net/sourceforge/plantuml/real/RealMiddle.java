package net.sourceforge.plantuml.real;

class RealMiddle extends AbstractReal implements Real {

	private final RealMoveable p1;
	private final RealMoveable p2;
	private final double delta;

	private RealMiddle(RealMoveable p1, RealMoveable p2, double delta) {
		super(p1.getLine());
		this.p1 = p1;
		this.p2 = p2;
		this.delta = delta;
	}

	private static RealMiddle create(RealMoveable p1, RealMoveable p2) {
		return new RealMiddle(p1, p2, 0);
	}

	@Override
	double getCurrentValueInternal() {
		return (p1.getCurrentValue() + p2.getCurrentValue()) / 2 + delta;
	}

	public Real addFixed(double diff) {
		return new RealMiddle(p1, p2, delta + diff);
	}

	public Real addAtLeast(double delta) {
		throw new UnsupportedOperationException();
	}

	public void ensureBiggerThan(Real other) {
		throw new UnsupportedOperationException();
	}

	public String getName() {
		return "[Middle " + p1.getName() + " and " + p2.getName() + "]";
	}

	public void printCreationStackTrace() {
		throw new UnsupportedOperationException();
	}

}

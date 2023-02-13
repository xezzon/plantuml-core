package net.sourceforge.plantuml.real;

import net.sourceforge.plantuml.log.Logme;

class PositiveForce {

	private final Real fixedPoint;
	private final RealMoveable movingPoint;
	private final double minimunDistance;
	private final boolean trace = false;
	private final Throwable creationPoint;

	public PositiveForce(Real fixedPoint, RealMoveable movingPoint, double minimunDistance) {
		if (fixedPoint == movingPoint) {
			throw new IllegalArgumentException();
		}
		this.fixedPoint = fixedPoint;
		this.movingPoint = movingPoint;
		this.minimunDistance = minimunDistance;
		this.creationPoint = new Throwable();
		this.creationPoint.fillInStackTrace();
	}

	@Override
	public String toString() {
		return "PositiveForce fixed=" + fixedPoint + " moving=" + movingPoint + " min=" + minimunDistance;
	}

	public boolean apply() {
		if (trace) {
			System.err.println("apply " + this);
		}
		final double movingPointValue = movingPoint.getCurrentValue();
		final double fixedPointValue;
		try {
			fixedPointValue = fixedPoint.getCurrentValue();
		} catch (IllegalStateException e) {
			System.err.println("Pb with force " + this);
			System.err.println("This force has been created here:");
			Logme.error(creationPoint);
			System.err.println("The fixed point has been created here: " + fixedPoint);
			fixedPoint.printCreationStackTrace();
			throw e;
		}
		final double distance = movingPointValue - fixedPointValue;
		final double diff = distance - minimunDistance;
		if (diff >= 0) {
			if (trace) {
				System.err.println("Not using ");
			}
			return false;
		}
		if (trace) {
			System.err.println("moving " + (-diff) + " " + movingPoint);
		}
		movingPoint.move(-diff);
		return true;
	}

}

package net.sourceforge.plantuml.real;

import java.util.concurrent.atomic.AtomicInteger;

import net.sourceforge.plantuml.log.Logme;

abstract class RealMoveable extends AbstractReal implements Real {

	public static final AtomicInteger CPT = new AtomicInteger();
	private final int cpt = CPT.getAndIncrement();
	private final String name;
	private final Throwable creationPoint;

	RealMoveable(RealLine line, String name) {
		super(line);
		this.name = name;
		this.creationPoint = new Throwable();
		this.creationPoint.fillInStackTrace();
	}

	abstract void move(double delta);

	final public void printCreationStackTrace() {
		Logme.error(creationPoint);
	}

	final public Real addFixed(double delta) {
		return new RealDelta(this, delta);
	}

	@Override
	public final String toString() {
		return "#" + cpt + "_" + name;
	}

	final public String getName() {
		return name;
	}
}

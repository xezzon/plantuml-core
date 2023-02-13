package net.sourceforge.plantuml.real;

import java.util.Arrays;
import java.util.Collection;

public class RealUtils {

	public static RealOrigin createOrigin() {
		final RealLine line = new RealLine();
		final RealImpl result = new RealImpl("O", line, 0);
		return result;
	}

	public static Real middle(Real r1, Real r2) {
		return new RealMiddle2((RealMoveable) r1, (RealMoveable) r2);
	}

	public static Real max(Real... reals) {
		return new RealMax(Arrays.asList(reals));
	}

	public static Real max(Collection<Real> reals) {
		return new RealMax(reals);
	}

	public static Real min(Real... reals) {
		return new RealMin(Arrays.asList(reals));
	}

	public static Real min(Collection<Real> reals) {
		return new RealMin(reals);
	}
}

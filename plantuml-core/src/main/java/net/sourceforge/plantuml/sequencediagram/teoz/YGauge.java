package net.sourceforge.plantuml.sequencediagram.teoz;

import net.sourceforge.plantuml.real.Real;

public final class YGauge {

	public static final boolean USE_ME = false;

	private final Real min;
	private final Real max;

	public YGauge(Real min, Real max) {
		this.min = min;
		this.max = max;
	}

	@Override
	public String toString() {
		return min.getCurrentValue() + " " + max.getCurrentValue();
	}

	public static YGauge create(Real min, double height) {
		if (height < 0)
			throw new IllegalArgumentException();
		return new YGauge(min, min.addFixed(height));
	}

	public final Real getMin() {
		return min;
	}

	public final Real getMax() {
		return max;
	}

}

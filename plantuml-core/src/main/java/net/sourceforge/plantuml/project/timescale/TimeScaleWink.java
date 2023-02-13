package net.sourceforge.plantuml.project.timescale;

import net.sourceforge.plantuml.project.core.PrintScale;
import net.sourceforge.plantuml.project.time.Day;

public class TimeScaleWink implements TimeScale {

	private final double scale;
	private final PrintScale printScale;

	public TimeScaleWink(double scale, PrintScale printScale) {
		this.scale = 16.0 * scale;
		this.printScale = printScale;
	}

	public double getStartingPosition(Day instant) {
		final long wink = instant.getMillis();
		return wink * scale / Day.MILLISECONDS_PER_DAY;
	}

	public double getEndingPosition(Day instant) {
		return getStartingPosition(instant) + getWidth(instant);
	}

	public double getWidth(Day instant) {
		return scale;
	}

	public boolean isBreaking(Day instant) {
		if (printScale == PrintScale.WEEKLY) {
			final long num = instant.getMillis() / Day.MILLISECONDS_PER_DAY;
			return num % 7 == 6;
		}
		return true;
	}

}

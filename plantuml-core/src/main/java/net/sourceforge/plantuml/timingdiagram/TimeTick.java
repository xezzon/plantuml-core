package net.sourceforge.plantuml.timingdiagram;

import java.math.BigDecimal;

public class TimeTick implements Comparable<TimeTick> {

	private final BigDecimal time;
	private final TimingFormat format;

	public TimeTick(BigDecimal time, TimingFormat format) {
		this.time = time;
		this.format = format;
	}

	public final BigDecimal getTime() {
		return time;
	}

	public int compareTo(TimeTick other) {
		return this.time.compareTo(other.time);
	}

	public final TimingFormat getFormat() {
		return format;
	}

	@Override
	public String toString() {
		return format.formatTime(time);
	}

}

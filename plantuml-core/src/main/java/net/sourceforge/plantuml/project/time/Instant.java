package net.sourceforge.plantuml.project.time;

import net.sourceforge.plantuml.project.Value;

public class Instant implements Comparable<Instant>, Value {

	private final long ms;

	public static Instant create(long ms) {
		return new Instant(ms);
	}

	public static Instant today() {
		return create(System.currentTimeMillis());
	}

	private Instant(long ms) {
		this.ms = ms;
	}

	public final long getMillis() {
		return ms;
	}

	@Override
	public String toString() {
		return "" + ms;
	}

	@Override
	public int hashCode() {
		return toLong().hashCode();
	}

	private Long toLong() {
		return Long.valueOf(ms);
	}

	@Override
	public boolean equals(Object obj) {
		final Instant other = (Instant) obj;
		return this.ms == other.ms;
	}

	public int compareTo(Instant other) {
		return toLong().compareTo(other.toLong());
	}

}

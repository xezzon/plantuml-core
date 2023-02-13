package net.sourceforge.plantuml.project.core;

public enum PrintScale {
	DAILY(1), WEEKLY(4), MONTHLY(15), QUARTERLY(40), YEARLY(60);

	private final double defaultScale;

	private PrintScale(int compress) {
		this.defaultScale = 1.0 / compress;
	}

	public final double getDefaultScale() {
		return defaultScale;
	}

	static public PrintScale fromString(String value) {
		if (value.startsWith("w")) {
			return WEEKLY;
		}
		if (value.startsWith("m")) {
			return MONTHLY;
		}
		if (value.startsWith("q")) {
			return QUARTERLY;
		}
		if (value.startsWith("y")) {
			return YEARLY;
		}
		return DAILY;
	}

}

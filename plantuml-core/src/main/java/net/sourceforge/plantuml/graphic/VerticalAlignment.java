package net.sourceforge.plantuml.graphic;

public enum VerticalAlignment {
	TOP, CENTER, BOTTOM;

	public static VerticalAlignment fromString(String s) {
		if (TOP.name().equalsIgnoreCase(s)) {
			return TOP;
		}
		// if (CENTER.name().equalsIgnoreCase(s)) {
		// return CENTER;
		// }
		if (BOTTOM.name().equalsIgnoreCase(s)) {
			return BOTTOM;
		}
		return BOTTOM;
	}
}

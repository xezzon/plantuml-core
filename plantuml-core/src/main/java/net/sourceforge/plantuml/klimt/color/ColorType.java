package net.sourceforge.plantuml.klimt.color;

public enum ColorType {
	TEXT, LINE, BACK, HEADER, ARROW;

	public static ColorType getType(String s) {
		final int x = s.indexOf('.');
		if (x != -1) {
			s = s.substring(0, x);
		}
		final ColorType key = ColorType.valueOf(s.toUpperCase());
		return key;
	}
}

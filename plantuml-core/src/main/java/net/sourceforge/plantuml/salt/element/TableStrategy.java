package net.sourceforge.plantuml.salt.element;

public enum TableStrategy {
	DRAW_NONE(' '), DRAW_OUTSIDE('+'), DRAW_OUTSIDE_WITH_TITLE('^'), DRAW_HORIZONTAL('-'), DRAW_VERTICAL('!'), DRAW_ALL('#');

	private final char c;

	private TableStrategy(char c) {
		this.c = c;
	}

	public static TableStrategy fromChar(char c) {
		for (TableStrategy t : TableStrategy.values()) {
			if (c == t.c) {
				return t;
			}
		}
		return null;
	}
}

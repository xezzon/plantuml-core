package net.sourceforge.plantuml.golem;

import net.sourceforge.plantuml.StringUtils;

public enum TileGeometry {
	NORTH, SOUTH, EAST, WEST, CENTER;

	public TileGeometry opposite() {
		switch (this) {
		case NORTH:
			return SOUTH;
		case SOUTH:
			return NORTH;
		case EAST:
			return WEST;
		case WEST:
			return EAST;
		case CENTER:
		default:
			throw new UnsupportedOperationException();
		}
	}

	public static TileGeometry fromString(String s) {
		final char c = StringUtils.goUpperCase(s.charAt(0));
		switch (c) {
		case 'N':
			return NORTH;
		case 'S':
			return SOUTH;
		case 'E':
			return EAST;
		case 'W':
			return WEST;
		default:
			throw new IllegalArgumentException();
		}
	}

}

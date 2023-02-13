package net.sourceforge.plantuml.salt.factory;

public enum ScrollStrategy {

	BOTH, VERTICAL_ONLY, HORIZONTAL_ONLY;

	public static ScrollStrategy fromDesc(String s) {
		if (s.endsWith("-")) {
			return HORIZONTAL_ONLY;
		}
		if (s.endsWith("I")) {
			return VERTICAL_ONLY;
		}
		return BOTH;
	}

}

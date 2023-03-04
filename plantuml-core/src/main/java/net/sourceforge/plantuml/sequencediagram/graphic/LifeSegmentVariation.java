// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml.sequencediagram.graphic;

public enum LifeSegmentVariation {
	LARGER, SMALLER;

	public int apply(int v) {
		if (this == LARGER) {
			return v + 1;
		}
		assert this == SMALLER;
		if (v == 0) {
			return 0;
		}
		return v - 1;
	}

}

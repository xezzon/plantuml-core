// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml.activitydiagram3.ftile;

public enum MergeStrategy {

	FULL, LIMITED, NONE;

	public MergeStrategy max(MergeStrategy other) {
		final int max = Math.max(this.ordinal(), other.ordinal());
		return MergeStrategy.values()[max];
	}

}

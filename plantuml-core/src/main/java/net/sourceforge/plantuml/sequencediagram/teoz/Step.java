// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml.sequencediagram.teoz;

import net.sourceforge.plantuml.klimt.Fashion;

public class Step {
    // ::remove folder when __HAXE__

	private final double value;
	private final boolean destroy;
	private final int indent;
	private final Fashion color;

	public Step(double value, boolean destroy, int indent, Fashion color) {
		if (indent < 0) {
			throw new IllegalArgumentException();
		}
		this.indent = indent;
		this.color = color;
		this.value = value;
		this.destroy = destroy;
	}

	public double getValue() {
		return value;
	}

	public boolean isDestroy() {
		return destroy;
	}

	public int getIndent() {
		return indent;
	}

	public Fashion getColors() {
		return color;
	}

}

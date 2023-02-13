package net.sourceforge.plantuml.tim;

import net.sourceforge.plantuml.tim.expression.TValue;

public class TFunctionArgument {

	private final String name;
	private final TValue def;

	public TFunctionArgument(String name, TValue def) {
		this.name = name;
		this.def = def;
	}

	public final String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "ARG:" + name;
	}

	public final TValue getOptionalDefaultValue() {
		return def;
	}

}

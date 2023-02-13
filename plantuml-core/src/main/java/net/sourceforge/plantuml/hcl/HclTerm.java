package net.sourceforge.plantuml.hcl;

import java.util.Objects;

public class HclTerm {

	private final SymbolType type;
	private final String data;

	public HclTerm(SymbolType type) {
		this.type = type;
		this.data = null;
	}

	public HclTerm(SymbolType type, String data) {
		this.type = type;
		this.data = Objects.requireNonNull(data);
	}

	@Override
	public String toString() {
		if (data == null)
			return type.toString();

		return type + "(" + data + ")";
	}

	public SymbolType getType() {
		return type;
	}

	public String getData() {
		return data;
	}

	public boolean is(SymbolType type) {
		return this.type == type;
	}

	public boolean is(SymbolType type1, SymbolType type2) {
		return this.type == type1 || this.type == type2;
	}

}

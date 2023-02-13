package net.sourceforge.plantuml.regexdiagram;

public class ReToken {

	private final ReTokenType type;
	private final String data;

	public ReToken(ReTokenType type, String data) {
		this.type = type;
		this.data = data;
	}

	@Override
	public String toString() {
		return type.toString() + "[" + data + "]";
	}

	public final ReTokenType getType() {
		return type;
	}

	public final String getData() {
		return data;
	}

}

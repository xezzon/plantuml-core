package net.sourceforge.plantuml.ebnf;

public class Token {

	private final Symbol symbol;
	private final String data;

	public Token(Symbol symbol, String data) {
		this.symbol = symbol;
		this.data = data;
		if (data != null && data.length() == 0)
			throw new IllegalArgumentException();
	}

	@Override
	public String toString() {
		if (data == null)
			return symbol.toString();
		return symbol.toString() + " " + data;
	}

	public final Symbol getSymbol() {
		return symbol;
	}

	public final String getData() {
		return data;
	}

}

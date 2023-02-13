package net.sourceforge.plantuml.regexdiagram;

public enum ReTokenType {

	SIMPLE_CHAR, //
	ESCAPED_CHAR, //
	CLASS, //
	QUANTIFIER, //
	ANCHOR, //
	GROUP, //
	ALTERNATIVE, //
	PARENTHESIS_OPEN, //
	PARENTHESIS_CLOSE, //
	CONCATENATION_IMPLICIT;

	static public boolean needImplicitConcatenation(ReTokenType token1, ReTokenType token2) {
		if (token1 == ALTERNATIVE)
			return false;
		if (token2 == ALTERNATIVE)
			return false;
		if (token2 == QUANTIFIER)
			return false;
		if (token1 == PARENTHESIS_OPEN)
			return false;
		if (token2 == PARENTHESIS_CLOSE)
			return false;
		return true;
	}

}

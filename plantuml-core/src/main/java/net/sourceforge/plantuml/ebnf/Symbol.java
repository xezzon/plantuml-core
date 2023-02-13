package net.sourceforge.plantuml.ebnf;

public enum Symbol {

	LITTERAL, //

	// https://en.wikipedia.org/wiki/Extended_Backus%E2%80%93Naur_form
	DEFINITION, // =
	CONCATENATION, // ,
	TERMINATION, // ;
	ALTERNATION, // |
	OPTIONAL_OPEN, // [
	OPTIONAL_CLOSE, // ]
	OPTIONAL, //
	REPETITION_SYMBOL, // *
	REPETITION_OPEN, // {
	REPETITION_CLOSE, // }
	REPETITION_MINUS_CLOSE, // }
	REPETITION_ZERO_OR_MORE, //
	REPETITION_ONE_OR_MORE, //
	GROUPING_OPEN, // (
	GROUPING_CLOSE, // )
	TERMINAL_STRING1, // " "
	TERMINAL_STRING2, // ' '
	COMMENT_TOKEN, // (* *)
	COMMENT_BELOW, // (* *)
	COMMENT_ABOVE, // (* *)
	SPECIAL_SEQUENCE, // ? ?
	EXCEPTION; // -

	public int getPriority() {
		switch (this) {
		case REPETITION_SYMBOL:
			return 3;
		case CONCATENATION:
			return 2;
		case ALTERNATION:
			return 1;
		}
		throw new UnsupportedOperationException();
	}

	boolean isOperator() {
		return this == CONCATENATION || this == ALTERNATION || this == REPETITION_SYMBOL;
	}

	boolean isFunction() {
		return this == OPTIONAL || this == REPETITION_ZERO_OR_MORE;
	}

}

package net.sourceforge.plantuml.tim.expression;

//https://en.cppreference.com/w/c/language/operator_precedence

public enum TokenOperator {
	MULTIPLICATION(100 - 3, "*") {
		public TValue operate(TValue v1, TValue v2) {
			return v1.multiply(v2);
		}
	},
	DIVISION(100 - 3, "/") {
		public TValue operate(TValue v1, TValue v2) {
			return v1.dividedBy(v2);
		}
	},
	ADDITION(100 - 4, "+") {
		public TValue operate(TValue v1, TValue v2) {
			return v1.add(v2);
		}
	},
	SUBSTRACTION(100 - 4, "" + TokenType.COMMERCIAL_MINUS_SIGN) {
		public TValue operate(TValue v1, TValue v2) {
			return v1.minus(v2);
		}
	},
	LESS_THAN(100 - 6, "<") {
		public TValue operate(TValue v1, TValue v2) {
			return v1.lessThan(v2);
		}
	},
	GREATER_THAN(100 - 6, ">") {
		public TValue operate(TValue v1, TValue v2) {
			return v1.greaterThan(v2);
		}
	},
	LESS_THAN_OR_EQUALS(100 - 6, "<=") {
		public TValue operate(TValue v1, TValue v2) {
			return v1.lessThanOrEquals(v2);
		}
	},
	GREATER_THAN_OR_EQUALS(100 - 6, ">=") {
		public TValue operate(TValue v1, TValue v2) {
			return v1.greaterThanOrEquals(v2);
		}
	},
	EQUALS(100 - 7, "==") {
		public TValue operate(TValue v1, TValue v2) {
			return v1.equalsOperation(v2);
		}
	},
	NOT_EQUALS(100 - 7, "!=") {
		public TValue operate(TValue v1, TValue v2) {
			return v1.notEquals(v2);
		}
	},
	LOGICAL_AND(100 - 11, "&&") {
		public TValue operate(TValue v1, TValue v2) {
			return v1.logicalAnd(v2);
		}
	},
	LOGICAL_OR(100 - 12, "||") {
		public TValue operate(TValue v1, TValue v2) {
			return v1.logicalOr(v2);
		}
	};

	private final int precedence;
	private final String display;

	private TokenOperator(int precedence, String display) {
		this.precedence = precedence;
		this.display = display;
	}

	public boolean isLeftAssociativity() {
		return true;
	}

	public static TokenOperator getTokenOperator(char ch, char ch2) {
		for (TokenOperator op : TokenOperator.values())
			if (op.display.length() == 2 && op.display.charAt(0) == ch && op.display.charAt(1) == ch2)
				return op;

		for (TokenOperator op : TokenOperator.values())
			if (op.display.length() == 1 && op.display.charAt(0) == ch)
				return op;

		return null;
	}

	public final int getPrecedence() {
		return precedence;
	}

	public abstract TValue operate(TValue v1, TValue v2);

	public final String getDisplay() {
		return display;
	}
}

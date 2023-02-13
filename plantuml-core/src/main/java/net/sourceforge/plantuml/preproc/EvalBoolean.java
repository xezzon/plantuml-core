package net.sourceforge.plantuml.preproc;

// http://stackoverflow.com/questions/3422673/evaluating-a-math-expression-given-in-string-form
public class EvalBoolean {

	private final String str;
	private int pos = -1;
	private char ch;
	private final Truth truth;

	public EvalBoolean(String str, Truth truth) {
		this.str = str;
		this.truth = truth;
	}

	private void nextChar() {
		pos++;
		if (pos < str.length()) {
			ch = str.charAt(pos);
		} else {
			ch = '\0';
		}
	}

	private boolean eat(char charToEat) {
		while (ch == ' ') {
			nextChar();
		}
		if (ch == charToEat) {
			nextChar();
			return true;
		}
		return false;
	}

	private boolean parseExpression() {
		boolean x = parseTerm();
		while (true) {
			if (eat('|')) {
				eat('|');
				x = x | parseTerm();
			} else {
				return x;
			}
		}
	}

	private boolean parseTerm() {
		boolean x = parseFactor();
		while (true) {
			if (eat('&')) {
				eat('&');
				x = x & parseFactor();
			} else {
				return x;
			}
		}
	}

	private boolean parseFactor() {
		if (eat('!')) {
			return !(parseFactor());
		}

		final boolean x;
		final int startPos = pos;
		if (eat('(')) {
			x = parseExpression();
			eat(')');
		} else if (isIdentifier()) {
			while (isIdentifier()) {
				nextChar();
			}
			final String func = str.substring(startPos, pos);
			x = truth.isTrue(func);
		} else {
			throw new IllegalArgumentException("Unexpected: " + (char) ch);
		}

		return x;
	}

	private boolean isIdentifier() {
		return ch == '_' || ch == '$' || Character.isLetterOrDigit(ch);
	}

	public boolean eval() {
		nextChar();
		final boolean x = parseExpression();
		if (pos < str.length()) {
			throw new IllegalArgumentException("Unexpected: " + (char) ch);
		}
		return x;
	}
}

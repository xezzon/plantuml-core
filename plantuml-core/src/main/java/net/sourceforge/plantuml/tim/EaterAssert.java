// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml.tim;

import net.sourceforge.plantuml.text.StringLocated;
import net.sourceforge.plantuml.tim.expression.TValue;

public class EaterAssert extends Eater {

	public EaterAssert(StringLocated s) {
		super(s);
	}

	@Override
	public void analyze(TContext context, TMemory memory) throws EaterException, EaterExceptionLocated {
		skipSpaces();
		checkAndEatChar("!assert");
		skipSpaces();
		final TValue value = eatExpressionStopAtColon(context, memory);
		skipSpaces();
		if (value.toBoolean() == false) {
			final char ch = peekChar();
			if (ch == ':') {
				checkAndEatChar(':');
				final TValue message = eatExpression(context, memory);
				throw EaterException.located("Assertion error : " + message.toString());
			}
			throw EaterException.located("Assertion error");
		}
	}

}

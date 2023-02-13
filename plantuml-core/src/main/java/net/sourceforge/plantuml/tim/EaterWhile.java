package net.sourceforge.plantuml.tim;

import net.sourceforge.plantuml.text.StringLocated;
import net.sourceforge.plantuml.tim.expression.TokenStack;

public class EaterWhile extends Eater {

	private TokenStack expression;

	public EaterWhile(StringLocated s) {
		super(s);
	}

	@Override
	public void analyze(TContext context, TMemory memory) throws EaterException {
		skipSpaces();
		checkAndEatChar("!while");
		skipSpaces();
		this.expression = eatTokenStack();
	}

	public final TokenStack getWhileExpression() {
		return expression;
	}
}

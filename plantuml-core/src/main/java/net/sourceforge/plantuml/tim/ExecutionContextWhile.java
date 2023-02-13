package net.sourceforge.plantuml.tim;

import net.sourceforge.plantuml.tim.expression.TValue;
import net.sourceforge.plantuml.tim.expression.TokenStack;
import net.sourceforge.plantuml.tim.iterator.CodePosition;
import net.sourceforge.plantuml.utils.LineLocation;

public class ExecutionContextWhile {

	private final TokenStack whileExpression;
	private final CodePosition codePosition;
	private boolean skipMe;

	private ExecutionContextWhile(TokenStack whileExpression, CodePosition codePosition) {
		this.whileExpression = whileExpression;
		this.codePosition = codePosition;
	}

	@Override
	public String toString() {
		return whileExpression.toString() + " " + codePosition;
	}

	public static ExecutionContextWhile fromValue(TokenStack whileExpression, CodePosition codePosition) {
		return new ExecutionContextWhile(whileExpression, codePosition);
	}

	public TValue conditionValue(LineLocation location, TContext context, TMemory memory) throws EaterException, EaterExceptionLocated {
		return whileExpression.getResult(location, context, memory);
	}

	public void skipMe() {
		skipMe = true;
	}

	public final boolean isSkipMe() {
		return skipMe;
	}

	public CodePosition getStartWhile() {
		return codePosition;
	}

}

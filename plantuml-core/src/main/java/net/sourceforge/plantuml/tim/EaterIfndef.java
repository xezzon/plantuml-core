package net.sourceforge.plantuml.tim;

import net.sourceforge.plantuml.text.StringLocated;
import net.sourceforge.plantuml.tim.expression.TValue;

public class EaterIfndef extends Eater {

	private String varname;

	public EaterIfndef(StringLocated s) {
		super(s);
	}

	@Override
	public void analyze(TContext context, TMemory memory) throws EaterException {
		skipSpaces();
		checkAndEatChar("!ifndef");
		skipSpaces();
		varname = eatAndGetVarname();
	}

	public boolean isTrue(TContext context, TMemory memory) {
		final TValue currentValue = memory.getVariable(varname);
		return currentValue == null && context.doesFunctionExist(varname) == false;
	}

}

package net.sourceforge.plantuml.tim;

import net.sourceforge.plantuml.text.StringLocated;
import net.sourceforge.plantuml.tim.expression.TValue;

public class EaterAffectationDefine extends Eater {

	public EaterAffectationDefine(StringLocated s) {
		super(s.getTrimmed());
	}

	@Override
	public void analyze(TContext context, TMemory memory) throws EaterException, EaterExceptionLocated {
		skipSpaces();
		checkAndEatChar("!define");
		skipSpaces();
		final String varname = eatAndGetVarname();
		skipSpaces();
		final String tmp = eatAllToEnd();
		final String tmp2 = context.applyFunctionsAndVariables(memory, getLineLocation(), tmp);
		final TValue value = TValue.fromString(tmp2);
		// if (memory instanceof TMemoryLocal) {
		// memory = ((TMemoryLocal) memory).getGlobalForInternalUseOnly();
		// }
		memory.putVariable(varname, value, TVariableScope.GLOBAL);
	}

}

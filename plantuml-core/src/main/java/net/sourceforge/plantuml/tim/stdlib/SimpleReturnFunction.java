package net.sourceforge.plantuml.tim.stdlib;

import java.util.List;
import java.util.Map;

import net.sourceforge.plantuml.tim.EaterException;
import net.sourceforge.plantuml.tim.TContext;
import net.sourceforge.plantuml.tim.TFunction;
import net.sourceforge.plantuml.tim.TFunctionType;
import net.sourceforge.plantuml.tim.TMemory;
import net.sourceforge.plantuml.tim.expression.TValue;
import net.sourceforge.plantuml.utils.LineLocation;

public abstract class SimpleReturnFunction implements TFunction {

	final public TFunctionType getFunctionType() {
		return TFunctionType.RETURN_FUNCTION;
	}

	final public void executeProcedure(TContext context, TMemory memory, LineLocation location, String s)
			throws EaterException {
		throw new UnsupportedOperationException();
	}

	final public void executeProcedureInternal(TContext context, TMemory memory, List<TValue> args,
			Map<String, TValue> named) throws EaterException {
		throw new UnsupportedOperationException();
	}

	final public boolean isUnquoted() {
		return false;
	}

}

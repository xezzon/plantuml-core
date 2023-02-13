package net.sourceforge.plantuml.tim.stdlib;

import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sourceforge.plantuml.tim.EaterException;
import net.sourceforge.plantuml.tim.EaterExceptionLocated;
import net.sourceforge.plantuml.tim.TContext;
import net.sourceforge.plantuml.tim.TFunction;
import net.sourceforge.plantuml.tim.TFunctionSignature;
import net.sourceforge.plantuml.tim.TFunctionType;
import net.sourceforge.plantuml.tim.TMemory;
import net.sourceforge.plantuml.tim.expression.TValue;
import net.sourceforge.plantuml.utils.LineLocation;

public class InvokeProcedure implements TFunction {

	public TFunctionSignature getSignature() {
		return new TFunctionSignature("%invoke_procedure", 1);
	}

	public boolean canCover(int nbArg, Set<String> namedArgument) {
		return nbArg > 0;
	}

	public TFunctionType getFunctionType() {
		return TFunctionType.PROCEDURE;
	}

	public void executeProcedure(TContext context, TMemory memory, LineLocation location, String s)
			throws EaterException, EaterExceptionLocated {
		throw new UnsupportedOperationException();
	}

	public void executeProcedureInternal(TContext context, TMemory memory, List<TValue> args, Map<String, TValue> named)
			throws EaterException, EaterExceptionLocated {
		final String fname = args.get(0).toString();
		final List<TValue> sublist = args.subList(1, args.size());
		final TFunctionSignature signature = new TFunctionSignature(fname, sublist.size());
		final TFunction func = context.getFunctionSmart(signature);
		if (func == null) {
			throw EaterException.located("Cannot find void function " + fname);
		}
		func.executeProcedureInternal(context, memory, sublist, named);
	}

	public TValue executeReturnFunction(TContext context, TMemory memory, LineLocation location, List<TValue> values,
			Map<String, TValue> named) {
		throw new UnsupportedOperationException();
	}

	public boolean isUnquoted() {
		return false;
	}

}

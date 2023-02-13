package net.sourceforge.plantuml.tim.iterator;

import java.util.List;

import net.sourceforge.plantuml.text.StringLocated;
import net.sourceforge.plantuml.text.TLineType;
import net.sourceforge.plantuml.tim.EaterException;
import net.sourceforge.plantuml.tim.EaterExceptionLocated;
import net.sourceforge.plantuml.tim.FunctionsSet;
import net.sourceforge.plantuml.tim.TContext;
import net.sourceforge.plantuml.tim.TFunctionType;
import net.sourceforge.plantuml.tim.TMemory;

public class CodeIteratorProcedure extends AbstractCodeIterator {

	private final FunctionsSet functionsSet;

	private final TContext context;
	private final TMemory memory;
	private final List<StringLocated> logs;

	public CodeIteratorProcedure(CodeIterator source, TContext context, TMemory memory, FunctionsSet functionsSet,
			List<StringLocated> logs) {
		super(source);
		this.context = context;
		this.functionsSet = functionsSet;
		this.logs = logs;
		this.memory = memory;
	}

	public StringLocated peek() throws EaterException, EaterExceptionLocated {
		while (true) {
			final StringLocated result = source.peek();
			if (result == null) {
				return null;
			}

			if (functionsSet.pendingFunction() != null
					&& (functionsSet.pendingFunction().getFunctionType() == TFunctionType.PROCEDURE
							|| functionsSet.pendingFunction().getFunctionType() == TFunctionType.LEGACY_DEFINELONG)) {
				logs.add(result);
				if (result.getType() == TLineType.END_FUNCTION) {
					functionsSet.executeEndfunction();
				} else {
					functionsSet.pendingFunction().addBody(result);
				}
				next();
				continue;
			}

			if (result.getType() == TLineType.DECLARE_PROCEDURE) {
				logs.add(result);
				functionsSet.executeDeclareProcedure(context, memory, result);
				next();
				continue;
			}

			return result;
		}
	}

}

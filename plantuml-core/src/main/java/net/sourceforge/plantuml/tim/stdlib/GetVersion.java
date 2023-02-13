package net.sourceforge.plantuml.tim.stdlib;

import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sourceforge.plantuml.tim.EaterException;
import net.sourceforge.plantuml.tim.EaterExceptionLocated;
import net.sourceforge.plantuml.tim.TContext;
import net.sourceforge.plantuml.tim.TFunctionSignature;
import net.sourceforge.plantuml.tim.TMemory;
import net.sourceforge.plantuml.tim.expression.TValue;
import net.sourceforge.plantuml.utils.LineLocation;
import net.sourceforge.plantuml.version.Version;

public class GetVersion extends SimpleReturnFunction {

	public TFunctionSignature getSignature() {
		return new TFunctionSignature("%version", 0);
	}

	public boolean canCover(int nbArg, Set<String> namedArgument) {
		return nbArg == 0;
	}

	public TValue executeReturnFunction(TContext context, TMemory memory, LineLocation location, List<TValue> values,
			Map<String, TValue> named) throws EaterException, EaterExceptionLocated {
		return TValue.fromString(Version.versionString());
	}
}

package net.sourceforge.plantuml.tim.stdlib;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import net.sourceforge.plantuml.json.JsonArray;
import net.sourceforge.plantuml.tim.EaterException;
import net.sourceforge.plantuml.tim.EaterExceptionLocated;
import net.sourceforge.plantuml.tim.TContext;
import net.sourceforge.plantuml.tim.TFunctionSignature;
import net.sourceforge.plantuml.tim.TMemory;
import net.sourceforge.plantuml.tim.expression.TValue;
import net.sourceforge.plantuml.utils.LineLocation;

public class SplitStr extends SimpleReturnFunction {

	public TFunctionSignature getSignature() {
		return new TFunctionSignature("%splitstr", 3);
	}

	public boolean canCover(int nbArg, Set<String> namedArgument) {
		return nbArg == 2;
	}

	public TValue executeReturnFunction(TContext context, TMemory memory, LineLocation location, List<TValue> values,
			Map<String, TValue> named) throws EaterException, EaterExceptionLocated {
		final JsonArray result = new JsonArray();

		final String str = values.get(0).toString();
		final String separator = values.get(1).toString();

		final StringTokenizer tokenizer = new StringTokenizer(str, separator);
		while (tokenizer.hasMoreElements())
			result.add(tokenizer.nextToken());

		return TValue.fromJson(result);
	}

}

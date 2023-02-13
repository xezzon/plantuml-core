package net.sourceforge.plantuml.tim.stdlib;

import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sourceforge.plantuml.json.JsonArray;
import net.sourceforge.plantuml.json.JsonObject;
import net.sourceforge.plantuml.json.JsonValue;
import net.sourceforge.plantuml.tim.EaterException;
import net.sourceforge.plantuml.tim.EaterExceptionLocated;
import net.sourceforge.plantuml.tim.TContext;
import net.sourceforge.plantuml.tim.TFunctionSignature;
import net.sourceforge.plantuml.tim.TMemory;
import net.sourceforge.plantuml.tim.expression.TValue;
import net.sourceforge.plantuml.utils.LineLocation;

public class Size extends SimpleReturnFunction {

	public TFunctionSignature getSignature() {
		return new TFunctionSignature("%size", 1);
	}

	public boolean canCover(int nbArg, Set<String> namedArgument) {
		return nbArg == 1;
	}

	public TValue executeReturnFunction(TContext context, TMemory memory, LineLocation location, List<TValue> values,
			Map<String, TValue> named) throws EaterException, EaterExceptionLocated {
		final TValue value = values.get(0);
		if (value.isNumber())
			return TValue.fromInt(0);
		if (value.isString())
			return TValue.fromInt(value.toString().length());

		final JsonValue json = value.toJson();
		if (json instanceof JsonArray) {
			final JsonArray array = (JsonArray) json;
			return TValue.fromInt(array.size());
		}
		if (json instanceof JsonObject) {
			final JsonObject object = (JsonObject) json;
			return TValue.fromInt(object.size());
		}
		return TValue.fromInt(0);
	}
}

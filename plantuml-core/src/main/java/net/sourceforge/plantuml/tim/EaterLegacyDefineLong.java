package net.sourceforge.plantuml.tim;

import net.sourceforge.plantuml.text.StringLocated;

public class EaterLegacyDefineLong extends Eater {

	private TFunctionImpl function;

	public EaterLegacyDefineLong(StringLocated s) {
		super(s.getTrimmed());
	}

	@Override
	public void analyze(TContext context, TMemory memory) throws EaterException, EaterExceptionLocated {
		skipSpaces();
		checkAndEatChar("!definelong");
		skipSpaces();
		function = eatDeclareFunction(context, memory, true, getLineLocation(), true, TFunctionType.LEGACY_DEFINELONG);
		// function.setFunctionType(TFunctionType.LEGACY_DEFINELONG);
	}

	public TFunctionImpl getFunction() {
		return function;
	}

}

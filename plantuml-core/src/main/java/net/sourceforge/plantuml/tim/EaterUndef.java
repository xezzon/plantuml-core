package net.sourceforge.plantuml.tim;

import net.sourceforge.plantuml.text.StringLocated;

public class EaterUndef extends Eater {

	public EaterUndef(StringLocated s) {
		super(s.getTrimmed());
	}

	@Override
	public void analyze(TContext context, TMemory memory) throws EaterException {
		skipSpaces();
		checkAndEatChar("!undef");
		skipSpaces();
		final String varname = eatAndGetVarname();
		memory.removeVariable(varname);
	}

}

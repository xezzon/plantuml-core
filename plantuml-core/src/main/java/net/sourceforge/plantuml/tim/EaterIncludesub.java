// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml.tim;

import net.sourceforge.plantuml.text.StringLocated;

public class EaterIncludesub extends Eater {

	private String location;

	public EaterIncludesub(StringLocated s) {
		super(s);
	}

	@Override
	public void analyze(TContext context, TMemory memory) throws EaterException, EaterExceptionLocated {
		skipSpaces();
		checkAndEatChar("!includesub");
		skipSpaces();
		this.location = context.applyFunctionsAndVariables(memory, getLineLocation(), this.eatAllToEnd());

	}

	public final String getLocation() {
		return location;
	}

}

package net.sourceforge.plantuml.tim;

import net.sourceforge.plantuml.text.StringLocated;

public class EaterStartsub extends Eater {

	private String subname;

	public EaterStartsub(StringLocated s) {
		super(s);
	}

	@Override
	public void analyze(TContext context, TMemory memory) throws EaterException {
		skipSpaces();
		checkAndEatChar("!startsub");
		skipSpaces();
		this.subname = eatAllToEnd();
		if (this.subname.matches("\\w+") == false) {
			throw EaterException.located("Bad sub name");
		}
	}

	public final String getSubname() {
		return subname;
	}

}

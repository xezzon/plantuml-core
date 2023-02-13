package net.sourceforge.plantuml.tim;

import net.sourceforge.plantuml.text.StringLocated;
import net.sourceforge.plantuml.tim.expression.TValue;

public class EaterReturn extends Eater {

	private TValue value;

	public EaterReturn(StringLocated s) {
		super(s);
	}

	@Override
	public void analyze(TContext context, TMemory memory) throws EaterException, EaterExceptionLocated {
		skipSpaces();
		checkAndEatChar("!return");
		skipSpaces();
		this.value = eatExpression(context, memory);
	}

	public final TValue getValue2() {
		return value;
	}

}

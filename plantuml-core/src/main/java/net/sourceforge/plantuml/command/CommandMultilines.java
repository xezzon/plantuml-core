package net.sourceforge.plantuml.command;

import net.sourceforge.plantuml.core.Diagram;
import net.sourceforge.plantuml.regex.Matcher2;
import net.sourceforge.plantuml.regex.MyPattern;
import net.sourceforge.plantuml.regex.Pattern2;
import net.sourceforge.plantuml.utils.BlocLines;

public abstract class CommandMultilines<S extends Diagram> implements Command<S> {

	private final Pattern2 starting;

	public CommandMultilines(String patternStart) {
		if (patternStart.startsWith("^") == false || patternStart.endsWith("$") == false) {
			throw new IllegalArgumentException("Bad pattern " + patternStart);
		}
		this.starting = MyPattern.cmpile(patternStart);
	}

	public abstract String getPatternEnd();

	public String[] getDescription() {
		return new String[] { "START: " + starting.pattern(), "END: " + getPatternEnd() };
	}

	final public CommandControl isValid(BlocLines lines) {
		if (isCommandForbidden())
			return CommandControl.NOT_OK;

		Matcher2 m1 = starting.matcher(lines.getFirst().getTrimmed().getString());
		if (m1.matches() == false)
			return CommandControl.NOT_OK;

		if (lines.size() == 1)
			return CommandControl.OK_PARTIAL;

		m1 = MyPattern.cmpile(getPatternEnd()).matcher(lines.getLast().getTrimmed().getString());
		if (m1.matches() == false)
			return CommandControl.OK_PARTIAL;

		return finalVerification();
	}

	protected boolean isCommandForbidden() {
		return false;
	}

	protected CommandControl finalVerification() {
		return CommandControl.OK;
	}

	protected final Pattern2 getStartingPattern() {
		return starting;
	}

}

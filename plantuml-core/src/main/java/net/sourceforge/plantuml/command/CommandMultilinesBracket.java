package net.sourceforge.plantuml.command;

import net.sourceforge.plantuml.core.Diagram;
import net.sourceforge.plantuml.regex.Matcher2;
import net.sourceforge.plantuml.regex.MyPattern;
import net.sourceforge.plantuml.regex.Pattern2;
import net.sourceforge.plantuml.text.StringLocated;
import net.sourceforge.plantuml.utils.BlocLines;

public abstract class CommandMultilinesBracket<S extends Diagram> implements Command<S> {

	private final Pattern2 starting;

	public CommandMultilinesBracket(String patternStart) {
		if (patternStart.startsWith("^") == false || patternStart.endsWith("$") == false)
			throw new IllegalArgumentException("Bad pattern " + patternStart);

		this.starting = MyPattern.cmpile(patternStart);
	}

	protected boolean isCommandForbidden() {
		return false;
	}

	public String[] getDescription() {
		return new String[] { "BRACKET: " + starting.pattern() };
	}

	protected CommandControl finalVerification() {
		return CommandControl.OK;
	}

	protected final Pattern2 getStartingPattern() {
		return starting;
	}

	final public CommandControl isValid(BlocLines lines) {
		if (isCommandForbidden())
			return CommandControl.NOT_OK;

		final Matcher2 m1 = starting.matcher(lines.getFirst().getTrimmed().getString());
		if (m1.matches() == false)
			return CommandControl.NOT_OK;

		if (lines.size() == 1)
			return CommandControl.OK_PARTIAL;

		int level = 1;
		for (StringLocated cs : lines.subExtract(1, 0)) {
			final String s = cs.getTrimmed().getString();
			if (isLineConsistent(s, level) == false)
				return CommandControl.NOT_OK;

			if (s.endsWith("{"))
				level++;

			if (s.endsWith("}"))
				level--;

			if (level < 0)
				return CommandControl.NOT_OK;

		}

		if (level != 0)
			return CommandControl.OK_PARTIAL;

		return finalVerification();
	}

	protected abstract boolean isLineConsistent(String line, int level);
}

// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml.command;

import net.sourceforge.plantuml.core.Diagram;
import net.sourceforge.plantuml.klimt.color.NoSuchColorException;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.text.StringLocated;
import net.sourceforge.plantuml.utils.BlocLines;

public abstract class CommandMultilines3<S extends Diagram> implements Command<S> {

	private final IRegex starting;

	private final MultilinesStrategy strategy;

	private final Trim trimEnd;

	public CommandMultilines3(IRegex patternStart, MultilinesStrategy strategy, Trim trimEnd) {
		if (patternStart.getPattern().startsWith("^") == false || patternStart.getPattern().endsWith("$") == false)
			throw new IllegalArgumentException("Bad pattern " + patternStart.getPattern());

		this.strategy = strategy;
		this.starting = patternStart;
		this.trimEnd = trimEnd;
	}

	public abstract RegexConcat getPatternEnd2();

	public String[] getDescription() {
		return new String[] { "START: " + starting.getPattern(), "END: " + getPatternEnd2().getPattern() };
	}

	final public CommandControl isValid(BlocLines lines) {
		lines = lines.cleanList(strategy);
		if (isCommandForbidden())
			return CommandControl.NOT_OK;

		final StringLocated first = lines.getFirst();
		if (first == null)
			return CommandControl.NOT_OK;

		final boolean result1 = starting.match(first.getTrimmed());
		if (result1 == false)
			return CommandControl.NOT_OK;

		if (lines.size() == 1)
			return CommandControl.OK_PARTIAL;

		final StringLocated potentialLast;
		if (trimEnd == Trim.NONE)
			potentialLast = lines.getLast();
		else if (trimEnd == Trim.BOTH)
			potentialLast = lines.getLast().getTrimmed();
		else
			throw new IllegalStateException();

		final boolean m1 = getPatternEnd2().match(potentialLast);
		if (m1 == false)
			return CommandControl.OK_PARTIAL;

		return finalVerification();
	}

	public final CommandExecutionResult execute(S system, BlocLines lines) throws NoSuchColorException {
		lines = lines.cleanList(strategy);
		return executeNow(system, lines);
	}

	protected abstract CommandExecutionResult executeNow(S system, BlocLines lines) throws NoSuchColorException;

	protected boolean isCommandForbidden() {
		return false;
	}

	protected CommandControl finalVerification() {
		return CommandControl.OK;
	}

	protected final IRegex getStartingPattern() {
		return starting;
	}

}

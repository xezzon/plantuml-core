package net.sourceforge.plantuml.command;

import net.sourceforge.plantuml.core.Diagram;
import net.sourceforge.plantuml.klimt.color.NoSuchColorException;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.Matcher2;
import net.sourceforge.plantuml.regex.MyPattern;
import net.sourceforge.plantuml.text.StringLocated;
import net.sourceforge.plantuml.utils.BlocLines;

public abstract class CommandMultilines2<S extends Diagram> implements Command<S> {

	private final IRegex starting;

	private final Trim trimEnd;

	private final MultilinesStrategy strategy;

	public CommandMultilines2(IRegex patternStart, MultilinesStrategy strategy, Trim trimEnd) {
		if (patternStart.getPattern().startsWith("^") == false || patternStart.getPattern().endsWith("$") == false)
			throw new IllegalArgumentException("Bad pattern " + patternStart.getPattern());

		this.strategy = strategy;
		this.starting = patternStart;
		this.trimEnd = trimEnd;
	}

	public boolean syntaxWithFinalBracket() {
		return false;
	}

	public abstract String getPatternEnd();

	public String[] getDescription() {
		return new String[] { "START: " + starting.getPattern(), "END: " + getPatternEnd() };
	}

	final public CommandControl isValid(BlocLines lines) {
		lines = lines.cleanList(strategy);
		if (isCommandForbidden())
			return CommandControl.NOT_OK;

		if (syntaxWithFinalBracket()) {
			if (lines.size() == 1 && lines.getFirst().getTrimmed().getString().endsWith("{") == false) {
				final String vline = ((StringLocated) lines.getAt(0)).getString() + " {";
				if (isValid(BlocLines.singleString(vline)) == CommandControl.OK_PARTIAL)
					return CommandControl.OK_PARTIAL;

				return CommandControl.NOT_OK;
			}
			lines = lines.eventuallyMoveBracket();
		}
		final StringLocated first = lines.getFirst();
		if (first == null)
			return CommandControl.NOT_OK;

		final boolean result1 = starting.match(first.getTrimmed());
		if (result1 == false)
			return CommandControl.NOT_OK;

		if (lines.size() == 1)
			return CommandControl.OK_PARTIAL;

		final Matcher2 m1 = MyPattern.cmpile(getPatternEnd()).matcher(trimEnd.trim(lines.getLast()));
		if (m1.matches() == false)
			return CommandControl.OK_PARTIAL;

		return finalVerification(lines);
	}

	public final CommandExecutionResult execute(S system, BlocLines lines) {
		lines = lines.cleanList(strategy);
		if (syntaxWithFinalBracket())
			lines = lines.eventuallyMoveBracket();

		try {
			return executeNow(system, lines);
		} catch (NoSuchColorException e) {
			return CommandExecutionResult.badColor();
		}
	}

	protected abstract CommandExecutionResult executeNow(S system, BlocLines lines) throws NoSuchColorException;

	protected boolean isCommandForbidden() {
		return false;
	}

	protected CommandControl finalVerification(BlocLines lines) {
		return CommandControl.OK;
	}

	protected final IRegex getStartingPattern() {
		return starting;
	}

}

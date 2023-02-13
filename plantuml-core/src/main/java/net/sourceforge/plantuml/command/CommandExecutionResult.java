package net.sourceforge.plantuml.command;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.plantuml.AbstractPSystem;

public class CommandExecutionResult {

	private final String error;
	private final AbstractPSystem newDiagram;
	private final List<String> debugLines;
	private final int score;

	private CommandExecutionResult(AbstractPSystem newDiagram, String error, int score, List<String> debugLines) {
		this.error = error;
		this.newDiagram = newDiagram;
		this.debugLines = debugLines;
		this.score = score;

	}

	public CommandExecutionResult withDiagram(AbstractPSystem newDiagram) {
		return new CommandExecutionResult(newDiagram, error, 0, null);
	}

	@Override
	public String toString() {
		return super.toString() + " " + error;
	}

	public static CommandExecutionResult newDiagram(AbstractPSystem result) {
		return new CommandExecutionResult(result, null, 0, null);
	}

	public static CommandExecutionResult ok() {
		return new CommandExecutionResult(null, null, 0, null);
	}

	public static CommandExecutionResult badColor() {
		return new CommandExecutionResult(null, "No such color", 1, null);
	}

	public static CommandExecutionResult error(String error) {
		return new CommandExecutionResult(null, error, 0, null);
	}

	public static CommandExecutionResult error(String error, Throwable t) {
		return new CommandExecutionResult(null, error, 0, getStackTrace(t));
	}

	public static List<String> getStackTrace(Throwable exception) {
		final List<String> result = new ArrayList<>();
		result.add(exception.toString());
		for (StackTraceElement ste : exception.getStackTrace())
			result.add("  " + ste.toString());

		if (exception.getCause() != null) {
			final Throwable cause = exception.getCause();
			result.add("  ");
			result.add("Caused by " + cause.toString());
			for (StackTraceElement ste : cause.getStackTrace())
				result.add("  " + ste.toString());

		}
		return result;
	}

	public boolean isOk() {
		return error == null;
	}

	public String getError() {
		if (isOk())
			throw new IllegalStateException();

		return error;
	}

	public int getScore() {
		return score;
	}

	public AbstractPSystem getNewDiagram() {
		return newDiagram;
	}

	public List<String> getDebugLines() {
		return debugLines;
	}

}

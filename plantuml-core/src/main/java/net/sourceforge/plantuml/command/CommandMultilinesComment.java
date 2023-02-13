package net.sourceforge.plantuml.command;

import net.sourceforge.plantuml.core.Diagram;
import net.sourceforge.plantuml.utils.BlocLines;

public class CommandMultilinesComment extends CommandMultilines<Diagram> {

	public static final String COMMENT_MULTILINE_START = "^[%s]*/[%q]([^%q]|[%q][^/])*$";
	public static final String COMMENT_MULTILINE_END = "^([^%q]|[%q][^/])*[%q]/[%s]*$";
	public static final String COMMENT_SINGLE_LINE = "^[%s]*([%q].*||/[%q].*[%q]/[%s]*)$";
	public static final String INNER_COMMENT = "/[%q].*?[%q]/";

	private CommandMultilinesComment() {
		super(COMMENT_MULTILINE_START);
	}

	@Override
	public String getPatternEnd() {
		return COMMENT_MULTILINE_END;
	}

	public CommandExecutionResult execute(final Diagram diagram, BlocLines lines) {
		return CommandExecutionResult.ok();
	}

}

package net.sourceforge.plantuml.project.command;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.descdiagram.command.CommandLinkElement;
import net.sourceforge.plantuml.project.GanttConstraint;
import net.sourceforge.plantuml.project.GanttDiagram;
import net.sourceforge.plantuml.project.core.Task;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandGanttArrow extends SingleLineCommand2<GanttDiagram> {

	public CommandGanttArrow() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandGanttArrow.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("CODE1", "([%pLN_.]+)"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("(-+)"), //
				new RegexLeaf("ARROW_STYLE", "(?:\\[(" + CommandLinkElement.LINE_STYLE + ")\\])?"), //
				new RegexLeaf("(-*)"), //
				new RegexLeaf("\\>"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("CODE2", "([%pLN_.]+)"), //
				RegexLeaf.spaceZeroOrMore(), RegexLeaf.end());
	}

	@Override
	protected CommandExecutionResult executeArg(GanttDiagram diagram, LineLocation location, RegexResult arg) {

		final String code1 = arg.get("CODE1", 0);
		final String code2 = arg.get("CODE2", 0);
		final Task task1 = diagram.getExistingTask(code1);
		if (task1 == null) {
			return CommandExecutionResult.error("No such task " + code1);
		}
		final Task task2 = diagram.getExistingTask(code2);
		if (task2 == null) {
			return CommandExecutionResult.error("No such task " + code2);
		}

		final GanttConstraint link = diagram.forceTaskOrder(task1, task2);
		link.applyStyle(arg.get("ARROW_STYLE", 0));

		return CommandExecutionResult.ok();
	}

}

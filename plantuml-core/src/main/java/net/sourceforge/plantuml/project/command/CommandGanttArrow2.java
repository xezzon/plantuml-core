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

public class CommandGanttArrow2 extends SingleLineCommand2<GanttDiagram> {

	public CommandGanttArrow2() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandGanttArrow2.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("\\["), //
				new RegexLeaf("TASK1", "([^\\[\\]]+?)"), //
				new RegexLeaf("\\]"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("(-+)"), //
				new RegexLeaf("ARROW_STYLE", "(?:\\[(" + CommandLinkElement.LINE_STYLE + ")\\])?"), //
				new RegexLeaf("(-*)"), //
				new RegexLeaf("\\>"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("\\["), //
				new RegexLeaf("TASK2", "([^\\[\\]]+?)"), //
				new RegexLeaf("\\]"), //
				RegexLeaf.spaceZeroOrMore(), //
				RegexLeaf.end());
	}

	@Override
	protected CommandExecutionResult executeArg(GanttDiagram diagram, LineLocation location, RegexResult arg) {

		final String name1 = arg.get("TASK1", 0);
		final String name2 = arg.get("TASK2", 0);
		final Task task1 = diagram.getOrCreateTask(name1, null, false);
		final Task task2 = diagram.getOrCreateTask(name2, null, false);

		final GanttConstraint link = diagram.forceTaskOrder(task1, task2);
		link.applyStyle(arg.get("ARROW_STYLE", 0));

		return CommandExecutionResult.ok();
	}

}

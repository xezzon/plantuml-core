package net.sourceforge.plantuml.project.command;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.project.GanttDiagram;
import net.sourceforge.plantuml.project.time.DayOfWeek;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandWeekNumberStrategy extends SingleLineCommand2<GanttDiagram> {

	public CommandWeekNumberStrategy() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandWeekNumberStrategy.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("weeks?"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("starts?"), //
				new RegexLeaf("[^0-9]*?"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("WEEKDAY", "(" + DayOfWeek.getRegexString() + ")"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("[^0-9]*?"), //
				new RegexLeaf("NUM", "([0-9]+)"), //
				new RegexLeaf("[^0-9]*?"), //
				RegexLeaf.end());
	}

	@Override
	protected CommandExecutionResult executeArg(GanttDiagram diagram, LineLocation location, RegexResult arg) {

		final DayOfWeek weekDay = DayOfWeek.fromString(arg.get("WEEKDAY", 0));
		final String num = arg.get("NUM", 0);
		diagram.setWeekNumberStrategy(weekDay, Integer.parseInt(num));
		return CommandExecutionResult.ok();
	}

}

package net.sourceforge.plantuml.project.command;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.project.GanttDiagram;
import net.sourceforge.plantuml.project.lang.ComplementDate;
import net.sourceforge.plantuml.project.time.Day;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandPrintBetween extends SingleLineCommand2<GanttDiagram> {

	private static final ComplementDate pattern = new ComplementDate();

	public CommandPrintBetween() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandPrintBetween.class.getName(), RegexLeaf.start(), //
				// Print between 2020/02/14 and 2020/03/04
				new RegexLeaf("print"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("between"), //
				RegexLeaf.spaceOneOrMore(), //
				pattern.toRegex("START"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("and"), //
				RegexLeaf.spaceOneOrMore(), //
				pattern.toRegex("END"), //
				RegexLeaf.end()); //
	}

	@Override
	protected CommandExecutionResult executeArg(GanttDiagram diagram, LineLocation location, RegexResult arg) {
		final Day start = (Day) pattern.getMe(diagram, arg, "START").get();
		final Day end = (Day) pattern.getMe(diagram, arg, "END").get();
		diagram.setPrintInterval(start, end);
		return CommandExecutionResult.ok();
	}

}

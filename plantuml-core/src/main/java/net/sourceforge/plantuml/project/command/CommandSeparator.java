package net.sourceforge.plantuml.project.command;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.project.GanttDiagram;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandSeparator extends SingleLineCommand2<GanttDiagram> {

	public CommandSeparator() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandSeparator.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("--"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("COMMENT", "((.+?)[%s]*--)?"), RegexLeaf.end());
	}

	@Override
	protected CommandExecutionResult executeArg(GanttDiagram diagram, LineLocation location, RegexResult arg) {
		final String comment = arg.get("COMMENT", 1);
		diagram.addSeparator(comment);
		return CommandExecutionResult.ok();
	}

}

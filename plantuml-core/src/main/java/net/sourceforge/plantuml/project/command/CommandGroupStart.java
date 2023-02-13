package net.sourceforge.plantuml.project.command;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.project.GanttDiagram;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandGroupStart extends SingleLineCommand2<GanttDiagram> {

	public CommandGroupStart() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandGroupStart.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("group"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("\\["), //
				new RegexLeaf("NAME", "([^\\[\\]]+)"), //
				new RegexLeaf("\\]"), //
				RegexLeaf.spaceZeroOrMore(), //
				RegexLeaf.end());
	}

	@Override
	protected CommandExecutionResult executeArg(GanttDiagram diagram, LineLocation location, RegexResult arg) {
		final String name = arg.get("NAME", 0);
		return diagram.addGroup(name);
	}

}

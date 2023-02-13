package net.sourceforge.plantuml.activitydiagram.command;

import net.sourceforge.plantuml.activitydiagram.ActivityDiagram;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexOr;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandEndPartition extends SingleLineCommand2<ActivityDiagram> {

	public CommandEndPartition() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandEndPartition.class.getName(), RegexLeaf.start(), //
				new RegexOr( //
						new RegexConcat( //
								new RegexLeaf("end"), //
								RegexLeaf.spaceZeroOrMore(), //
								new RegexLeaf("partition")), //
						new RegexLeaf("\\}")), //
				RegexLeaf.end()); //
	}

	@Override
	protected CommandExecutionResult executeArg(ActivityDiagram diagram, LineLocation location, RegexResult arg) {
		final boolean result = diagram.endGroup();
		if (result == false)
			return CommandExecutionResult.error("No partition defined");

		return CommandExecutionResult.ok();
	}

}

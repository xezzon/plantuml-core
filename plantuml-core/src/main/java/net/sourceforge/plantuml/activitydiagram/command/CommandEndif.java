package net.sourceforge.plantuml.activitydiagram.command;

import net.sourceforge.plantuml.activitydiagram.ActivityDiagram;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandEndif extends SingleLineCommand2<ActivityDiagram> {

	public CommandEndif() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandEndif.class.getName(), //
				RegexLeaf.start(), //
				new RegexLeaf("end"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("if"), //
				RegexLeaf.end()); //
	}

	@Override
	protected CommandExecutionResult executeArg(ActivityDiagram diagram, LineLocation location, RegexResult arg) {
		if (diagram.getLastEntityConsulted() == null) {
			return CommandExecutionResult.error("No if for this endif");
		}
		if (diagram.getCurrentContext() == null) {
			return CommandExecutionResult.error("No if for this endif");
		}
		diagram.endif();

		return CommandExecutionResult.ok();
	}

}

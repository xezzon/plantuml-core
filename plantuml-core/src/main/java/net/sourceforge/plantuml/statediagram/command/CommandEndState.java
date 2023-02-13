package net.sourceforge.plantuml.statediagram.command;

import net.sourceforge.plantuml.baraye.Entity;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.statediagram.StateDiagram;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandEndState extends SingleLineCommand2<StateDiagram> {

	public CommandEndState() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandEndState.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("(end[%s]?state|\\})"), //
				RegexLeaf.end()); //
	}

	@Override
	protected CommandExecutionResult executeArg(StateDiagram diagram, LineLocation location, RegexResult arg) {
		final Entity currentPackage = diagram.getCurrentGroup();
		if (currentPackage == null) {
			return CommandExecutionResult.error("No inner state defined");
		}
		diagram.endGroup();
		return CommandExecutionResult.ok();
	}

}

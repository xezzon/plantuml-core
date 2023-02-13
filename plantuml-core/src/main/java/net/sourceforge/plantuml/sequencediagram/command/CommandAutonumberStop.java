package net.sourceforge.plantuml.sequencediagram.command;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.sequencediagram.SequenceDiagram;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandAutonumberStop extends SingleLineCommand2<SequenceDiagram> {

	public CommandAutonumberStop() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandAutonumberStop.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("autonumber"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("stop"), //
				RegexLeaf.spaceZeroOrMore(), RegexLeaf.end()); //
	}

	@Override
	protected CommandExecutionResult executeArg(SequenceDiagram diagram, LineLocation location, RegexResult arg) {
		diagram.autonumberStop();
		return CommandExecutionResult.ok();
	}
}

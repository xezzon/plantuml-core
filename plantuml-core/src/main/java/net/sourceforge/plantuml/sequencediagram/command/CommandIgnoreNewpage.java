package net.sourceforge.plantuml.sequencediagram.command;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.sequencediagram.SequenceDiagram;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandIgnoreNewpage extends SingleLineCommand2<SequenceDiagram> {

	public CommandIgnoreNewpage() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandIgnoreNewpage.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("ignore"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("newpage"), RegexLeaf.end()); //
	}

	@Override
	protected CommandExecutionResult executeArg(SequenceDiagram diagram, LineLocation location, RegexResult arg) {
		diagram.ignoreNewpage();
		return CommandExecutionResult.ok();
	}
}

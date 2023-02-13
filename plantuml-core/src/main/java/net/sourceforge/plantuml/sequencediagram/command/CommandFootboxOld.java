package net.sourceforge.plantuml.sequencediagram.command;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.sequencediagram.SequenceDiagram;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandFootboxOld extends SingleLineCommand2<SequenceDiagram> {

	public CommandFootboxOld() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandFootboxOld.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("footbox"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("TYPE", "(on|off)?"), //
				RegexLeaf.spaceZeroOrMore(), //
				RegexLeaf.end()); //
	}

	@Override
	protected CommandExecutionResult executeArg(SequenceDiagram diagram, LineLocation location, RegexResult arg) {
		final boolean footbox = arg.get("TYPE", 0).equalsIgnoreCase("on");
		diagram.setShowFootbox(footbox);
		return CommandExecutionResult.ok();
	}
}

package net.sourceforge.plantuml.sequencediagram.command;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.sequencediagram.SequenceDiagram;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandHideUnlinked extends SingleLineCommand2<SequenceDiagram> {

	public CommandHideUnlinked() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandHideUnlinked.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("HIDE", "(hide|show)"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("@?unlinked"), //
				RegexLeaf.end()); //
	}

	@Override
	protected CommandExecutionResult executeArg(SequenceDiagram diagram, LineLocation location, RegexResult arg) {
		diagram.setHideUnlinkedData(arg.get("HIDE", 0).equalsIgnoreCase("hide"));
		return CommandExecutionResult.ok();
	}

}

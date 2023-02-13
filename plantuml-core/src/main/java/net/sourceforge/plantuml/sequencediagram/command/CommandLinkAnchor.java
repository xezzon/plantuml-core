package net.sourceforge.plantuml.sequencediagram.command;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.sequencediagram.SequenceDiagram;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandLinkAnchor extends SingleLineCommand2<SequenceDiagram> {

	public CommandLinkAnchor() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandLinkAnchor.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("ANCHOR1", "\\{([%pLN_]+)\\}"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("LINK", "\\<-\\>"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("ANCHOR2", "\\{([%pLN_]+)\\}"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("MESSAGE", "(?::[%s]*(.*))?"), RegexLeaf.end());
	}

	@Override
	protected CommandExecutionResult executeArg(SequenceDiagram diagram, LineLocation location, RegexResult arg) {
		final String anchor1 = arg.get("ANCHOR1", 0);
		final String anchor2 = arg.get("ANCHOR2", 0);
		final String message = arg.get("MESSAGE", 0);
		return diagram.linkAnchor(anchor1, anchor2, message);
	}

}

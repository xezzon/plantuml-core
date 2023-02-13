package net.sourceforge.plantuml.sequencediagram.command;

import net.sourceforge.plantuml.StringUtils;
import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexOptional;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.sequencediagram.SequenceDiagram;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandAutonumberIncrement extends SingleLineCommand2<SequenceDiagram> {

	public CommandAutonumberIncrement() {
		super(getConcat());
	}

	private static RegexConcat getConcat() {
		return RegexConcat.build(CommandAutonumberIncrement.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("autonumber"), //
				RegexLeaf.spaceOneOrMore(), //
				new RegexLeaf("inc"), //
				new RegexOptional( //
						new RegexConcat( //
								RegexLeaf.spaceOneOrMore(), //
								new RegexLeaf("POS", "([A-Za-z])") //
						)), //
				RegexLeaf.spaceZeroOrMore(), RegexLeaf.end());
	}

	@Override
	protected CommandExecutionResult executeArg(SequenceDiagram diagram, LineLocation location, RegexResult arg) {
		final String position = arg.get("POS", 0);
		if (position == null) {
			diagram.getAutoNumber().incrementIntermediate();
		} else {
			final int pos = StringUtils.goLowerCase(position.charAt(0)) - 'a';
			diagram.getAutoNumber().incrementIntermediate(pos);
		}
		return CommandExecutionResult.ok();
	}

}

package net.sourceforge.plantuml.sequencediagram.command;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.sequencediagram.AbstractMessage;
import net.sourceforge.plantuml.sequencediagram.LifeEventType;
import net.sourceforge.plantuml.sequencediagram.SequenceDiagram;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandDeactivateShort extends SingleLineCommand2<SequenceDiagram> {

	public CommandDeactivateShort() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandDeactivateShort.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("TYPE", "deactivate"), //
				RegexLeaf.spaceZeroOrMore(), //
				RegexLeaf.end());
	}

	@Override
	protected CommandExecutionResult executeArg(SequenceDiagram sequenceDiagram, LineLocation location,
			RegexResult arg2) {
		final AbstractMessage message = sequenceDiagram.getActivatingMessage();
		if (message == null)
			return CommandExecutionResult.error("Nothing to deactivate.");

		final String error = sequenceDiagram.activate(message.getParticipant2(), LifeEventType.DEACTIVATE, null);
		if (error != null)
			return CommandExecutionResult.error(error);

		return CommandExecutionResult.ok();
	}

}

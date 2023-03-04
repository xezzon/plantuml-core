// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml.sequencediagram.command;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.klimt.color.NoSuchColorException;
import net.sourceforge.plantuml.regex.IRegex;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regex.RegexLeaf;
import net.sourceforge.plantuml.regex.RegexResult;
import net.sourceforge.plantuml.sequencediagram.LifeEventType;
import net.sourceforge.plantuml.sequencediagram.Participant;
import net.sourceforge.plantuml.sequencediagram.SequenceDiagram;
import net.sourceforge.plantuml.utils.LineLocation;

public class CommandActivate2 extends SingleLineCommand2<SequenceDiagram> {

	public CommandActivate2() {
		super(getRegexConcat());
	}

	static IRegex getRegexConcat() {
		return RegexConcat.build(CommandActivate2.class.getName(), RegexLeaf.start(), //
				new RegexLeaf("NAME", "([%pLN_.@]+)"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("TYPE", "(\\+\\+|--)"), //
				RegexLeaf.spaceZeroOrMore(), //
				new RegexLeaf("COLOR", "(#\\w+)?"), //
				RegexLeaf.end()); //
	}

	@Override
	protected CommandExecutionResult executeArg(SequenceDiagram diagram, LineLocation location, RegexResult arg)
			throws NoSuchColorException {
		final LifeEventType type = arg.get("TYPE", 0).equals("++") ? LifeEventType.ACTIVATE : LifeEventType.DEACTIVATE;
		final Participant p = diagram.getOrCreateParticipant(arg.get("NAME", 0));
		final String s = arg.get("COLOR", 0);
		final String error = diagram.activate(p, type,
				s == null ? null : diagram.getSkinParam().getIHtmlColorSet().getColor(s));
		if (error == null) {
			return CommandExecutionResult.ok();
		}
		return CommandExecutionResult.error(error);
	}

}
